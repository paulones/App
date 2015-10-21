/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppcc.app.controller.bean;

import com.ppcc.app.model.entity.RecuperarSenha;
import com.ppcc.app.model.entity.Usuario;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.EmailException;
import com.ppcc.app.util.Cookie;
import com.ppcc.app.util.EnviarEmail;
import com.ppcc.app.util.GeradorMD5;
import com.ppcc.app.model.jpa.controller.InstituicaoJpaController;
import com.ppcc.app.model.entity.Instituicao;
import com.ppcc.app.model.jpa.controller.AutorizacaoJpaController;
import com.ppcc.app.model.jpa.controller.RecuperarSenhaJpaController;
import com.ppcc.app.model.jpa.controller.UsuarioJpaController;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.ppcc.app.util.Base64Crypt;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author PRCC
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {

    private String mensagem;
    private Usuario usuario;
    private String licenca;
    private String cod;
    private RecuperarSenhaJpaController recuperarSenhaJpaController = new RecuperarSenhaJpaController();
    private UsuarioJpaController usuarioJpaController = new UsuarioJpaController();

    public void init() throws IOException {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            usuario = new Usuario();
            mensagem = "";
            licenca = "";
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            cod = request.getParameter("cod");
            if (cod != null) {
                if (recuperarSenhaJpaController.findRecuperarSenhaByCod(cod) == null) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
                } else {
                    mensagem = "passChange";
                }
            }
        }
    }

    public void login() throws IOException {
        Usuario usuarioBD = usuarioJpaController.findUsuarioByCPF(usuario.getCpf());
        if (usuarioBD != null) {
            if (usuarioBD.getSenha().equals(usuario.getSenha())) {
                if (!expirado(usuario.getCpf())) {
                    Cookie.addCookie("usuario", usuario.getCpf(), 36000);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/home.xhtml");
                } else {
                    mensagem = "licensingFail";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tempo da licença expirou, adquira outra!", null));
                }
            } else {
                mensagem = "loginFail";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha incorreta.", null));
            }
        } else {
            mensagem = "loginFail";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não existe.", null));
        }
    }

    public void recuperarSenha() throws Exception {
        try {
            cod = GeradorMD5.generate(usuario.getEmail() + "aabbccdd");
            RecuperarSenha rp = new RecuperarSenha();
            String accountActivation = "App - Ativar Conta";
            String mailtext = "Olá!\n\nObrigado pelo seu interesse em se registrar no App.\n\nPara concluir o processo, será preciso que você clique no link abaixo para ativar sua conta.\n\n";
            mailtext += "http://prcc.com.br/login.xhtml?cod=" + cod;
            mailtext += "\n\nAtenciosamente,\n\nPRCC - Gestão em TI e negócios.";
            EnviarEmail.enviar(mailtext, accountActivation, usuario.getEmail());
            rp.setId(usuarioJpaController.findUsuarioByEmail(usuario.getEmail()).getId());
            rp.setCodigo(cod);
            recuperarSenhaJpaController.create(rp);
            usuario.setEmail(null);
            mensagem = "loginSuccess";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitação enviada. Verifique seu e-mail.", null));
        } catch (EmailException e) {
            e.printStackTrace();
            mensagem = "loginFail";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocorreu um erro ao tentar enviar e-mail de recuperação de senha. Tente novamente.", null));
        }
    }

    public void alterarSenha() throws Exception {
        String senha = usuario.getSenha();
        RecuperarSenha rp = recuperarSenhaJpaController.findRecuperarSenhaByCod(cod);
        usuario = usuarioJpaController.findUsuario(rp.getUsuario().getId());
        usuario.setSenha(senha);
        usuarioJpaController.edit(usuario);
        recuperarSenhaJpaController.destroy(rp.getUsuario().getId());
        mensagem = "loginSuccess";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha alterada com sucesso!", null));
    }

    public void registrar() throws Exception {
        if (new AutorizacaoJpaController().findAutorizacaoByCPF(usuario.getCpf()) != null) {
            if (usuarioJpaController.findUsuarioByCPF(usuario.getCpf()) == null) {
                if (usuarioJpaController.findUsuarioByEmail(usuario.getEmail()) == null) {
                    usuarioJpaController.create(usuario);
                    mensagem = "loginSuccess";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário registrado com sucesso!", null));
                    usuario.setNome(null);
                    usuario.setEmail(null);
                    usuario.setCpf(null);
                } else {
                    mensagem = "registerFail";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um usuário com o e-mail \"" + usuario.getEmail() + "\" cadastrado.", null));
                    usuario.setEmail(null);
                }
            } else {
                mensagem = "registerFail";
                String cpf = String.format("%s.%s.%s-%s", usuario.getCpf().substring(0, 3), usuario.getCpf().substring(3, 6), usuario.getCpf().substring(6, 9), usuario.getCpf().substring(9));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já existe um usuário com o CPF \"" + cpf + "\" cadastrado.", null));
                usuario.setCpf(null);
            }
        } else {
            mensagem = "registerFail";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não autorizado. Verifique sua permissão de acesso.", null));
            usuario.setCpf(null);
        }
    }

    public void licenciar() {
        if (licenciar(licenca)) {
            mensagem = "loginSuccess";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Licença registrada com sucesso!", null));
        } else {
            mensagem = "licensingFail";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Licença inválida!", null));
        }
    }

    //Verifica a licença fornecida ao sistema
    public static Boolean licenciar(String crypt) {
        InstituicaoJpaController instituicaoJpaController = new InstituicaoJpaController();
        Boolean ok = false;
        try {
            //Consulta em uma persistênca a string de licença
            Base64Crypt base64Crypt = new Base64Crypt();
            String licenca = base64Crypt.decrypt(crypt);
            //System.out.println("chave: "+crypt);
            //System.out.println("licenca: "+licenca);
            if (licenca != null && licenca.length() >= 31) {
                String tipo = licenca.substring(0, 1);
                String iniData = licenca.substring(1, 9);
                String endData = licenca.substring(9, 17);
                String cnpj = licenca.substring(17, 31);
                //System.out.println("string: "+tipo +" - "+ iniData +" - "+ endData+" - "+ cnpj);
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                Date dataChave = sdf.parse(endData);
                Date dataAtual = new Date();
                if (dataAtual.before(dataChave)) {
                    Instituicao instituicao = new Instituicao();
                    instituicao = instituicaoJpaController.findInstituicaoByCNPJ(cnpj);
                    //System.out.println("instituicao: "+instituicao.getRazaoSocial());
                    if (instituicao == null) { // Cria instituição caso a mesma não exista
                        instituicao.setCnpj(cnpj);
                        instituicao.setChave(crypt);
                        instituicao.setUltimoLogin(base64Crypt.encrypt(sdf.format(dataAtual)));
                        instituicaoJpaController.create(instituicao);
                    } else {
                        instituicao.setChave(crypt);
                        instituicao.setUltimoLogin(base64Crypt.encrypt(sdf.format(dataAtual)));
                        instituicaoJpaController.edit(instituicao);
                    }
                    ok = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    //Verifica se o tempo da última licença já expirou
    public static Boolean expirado(String cpf) {
        InstituicaoJpaController instituicaoJpaController = new InstituicaoJpaController();
        Boolean ok = true;
        Instituicao instituicao = new Instituicao();
        //Consulta em uma persistênca a string de licença
        try {
            instituicao = instituicaoJpaController.findInstituicaoByCPF(cpf);
            String descriptografia;
            String ultimoLogin = "";
            if (instituicao.getChave() != null) {
                Base64Crypt base64Crypt = new Base64Crypt();
                descriptografia = base64Crypt.decrypt(instituicao.getChave());
                String endData = descriptografia.substring(9, 17);
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                Date dataChave = sdf.parse(endData);
                if (instituicao.getUltimoLogin() != null) { //Verifica se existe um último login realizado
                    ultimoLogin = base64Crypt.decrypt(instituicao.getUltimoLogin());
                    Date ultimoDateLogin = sdf.parse(ultimoLogin);
                    if (ultimoDateLogin.before(new Date())) {
                        instituicao.setUltimoLogin(base64Crypt.encrypt(sdf.format(new Date())));
                    }
                }
                //Criar regra para licença vitalicia
                if (!dataChave.before(new Date())) {
                    instituicaoJpaController.edit(instituicao);
                    ok = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }
}
