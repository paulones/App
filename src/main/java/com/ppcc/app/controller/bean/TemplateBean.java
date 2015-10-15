/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppcc.app.controller.bean;

import com.ppcc.app.model.entity.Autorizacao;
import com.ppcc.app.model.entity.Bem;
import com.ppcc.app.model.entity.Cidade;
import com.ppcc.app.model.entity.Citacao;
import com.ppcc.app.model.entity.Endereco;
import com.ppcc.app.model.entity.EnderecoPessoa;
import com.ppcc.app.model.entity.Estado;
import com.ppcc.app.model.entity.EstadoCivil;
import com.ppcc.app.model.entity.Executado;
import com.ppcc.app.model.entity.Funcao;
import com.ppcc.app.model.entity.Nacionalidade;
import com.ppcc.app.model.entity.Pais;
import com.ppcc.app.model.entity.PessoaFisica;
import com.ppcc.app.model.entity.PessoaFisicaFisica;
import com.ppcc.app.model.entity.PessoaFisicaJuridica;
import com.ppcc.app.model.entity.PessoaJuridica;
import com.ppcc.app.model.entity.PessoaJuridicaJuridica;
import com.ppcc.app.model.entity.PessoaJuridicaSucessao;
import com.ppcc.app.model.entity.ProcessoJudicial;
import com.ppcc.app.model.entity.Redirecionamento;
import com.ppcc.app.model.entity.SocioRedirecionamento;
import com.ppcc.app.model.entity.TipoBem;
import com.ppcc.app.model.entity.TipoEmpresarial;
import com.ppcc.app.model.entity.Usuario;
import com.ppcc.app.model.entity.VinculoSocial;
import com.ppcc.app.model.jpa.controller.AquisicaoBemJpaController;
import com.ppcc.app.model.jpa.controller.AutorizacaoJpaController;
import com.ppcc.app.model.jpa.controller.BemJpaController;
import com.ppcc.app.model.jpa.controller.CidadeJpaController;
import com.ppcc.app.model.jpa.controller.CitacaoJpaController;
import com.ppcc.app.model.jpa.controller.EnderecoJpaController;
import com.ppcc.app.model.jpa.controller.EstadoCivilJpaController;
import com.ppcc.app.model.jpa.controller.EstadoJpaController;
import com.ppcc.app.model.jpa.controller.FuncaoJpaController;
import com.ppcc.app.model.jpa.controller.NacionalidadeJpaController;
import com.ppcc.app.model.jpa.controller.PaisJpaController;
import com.ppcc.app.model.jpa.controller.PenhoraJpaController;
import com.ppcc.app.model.jpa.controller.PessoaFisicaFisicaJpaController;
import com.ppcc.app.model.jpa.controller.PessoaFisicaJpaController;
import com.ppcc.app.model.jpa.controller.PessoaFisicaJuridicaJpaController;
import com.ppcc.app.model.jpa.controller.PessoaJuridicaJpaController;
import com.ppcc.app.model.jpa.controller.PessoaJuridicaJuridicaJpaController;
import com.ppcc.app.model.jpa.controller.PessoaJuridicaSucessaoJpaController;
import com.ppcc.app.model.jpa.controller.ProcessoJudicialJpaController;
import com.ppcc.app.model.jpa.controller.RedirecionamentoJpaController;
import com.ppcc.app.model.jpa.controller.TipoBemJpaController;
import com.ppcc.app.model.jpa.controller.TipoEmpresarialJpaController;
import com.ppcc.app.model.jpa.controller.UsuarioJpaController;
import com.ppcc.app.model.jpa.controller.VinculoSocialJpaController;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.ppcc.app.util.Cookie;
import com.ppcc.app.util.GeradorLog;

/**
 *
 * @author ipti004
 */
@ManagedBean(name = "templateBean")
@ViewScoped
public class TemplateBean implements Serializable {

    private Usuario usuario;
    private EnderecoPessoa enderecoPessoaFisica;
    private EnderecoPessoa enderecoPessoaJuridica;
    private Executado executado;
    private PessoaJuridicaSucessao pessoaJuridicaSucessao;
    private Bem bem;
 
    private List<TipoEmpresarial> tipoEmpresarialList;
    private List<Pais> paisList;
    private List<Nacionalidade> nacionalidadeList;
    private List<EstadoCivil> estadoCivilList;
    private List<Estado> estadoList;
    private List<Cidade> cidadeEndList;
    private List<Cidade> cidadeEleList;
    private List<Cidade> cidadeNatList;
    private List<Funcao> funcaoList;
    private List<VinculoSocial> vinculoSocialList;
    private List<PessoaFisicaJuridica> pessoaFisicaJuridicaList;
    private List<PessoaFisicaFisica> pessoaFisicaFisicaList;
    private List<PessoaJuridicaJuridica> pessoaJuridicaJuridicaList;
    private List<Bem> bemList;
    private List<TipoBem> tipoBemList; 
    
    private AquisicaoBemJpaController aquisicaoBemJpaController = new AquisicaoBemJpaController();
    private BemJpaController bemJpaController = new BemJpaController();
    private CidadeJpaController cidadeJpaController = new CidadeJpaController();
    private CitacaoJpaController citacaoJpaController = new CitacaoJpaController();
    private EnderecoJpaController enderecoJpaController = new EnderecoJpaController();
    private EstadoJpaController estadoJpaController = new EstadoJpaController();
    private FuncaoJpaController funcaoJpaController = new FuncaoJpaController();
    private PaisJpaController paisJpaController = new PaisJpaController();
    private PenhoraJpaController penhoraJpaController = new PenhoraJpaController();
    private PessoaFisicaJpaController pessoaFisicaJpaController = new PessoaFisicaJpaController();
    private PessoaFisicaFisicaJpaController pessoaFisicaFisicaJpaController = new PessoaFisicaFisicaJpaController();
    private PessoaFisicaJuridicaJpaController pessoaFisicaJuridicaJpaController = new PessoaFisicaJuridicaJpaController();
    private PessoaJuridicaJpaController pessoaJuridicaJpaController = new PessoaJuridicaJpaController();
    private PessoaJuridicaJuridicaJpaController pessoaJuridicaJuridicaJpaController = new PessoaJuridicaJuridicaJpaController();
    private PessoaJuridicaSucessaoJpaController pessoaJuridicaSucessaoJpaController = new PessoaJuridicaSucessaoJpaController();
    private RedirecionamentoJpaController redirecionamentoJpaController = new RedirecionamentoJpaController();
    private TipoBemJpaController tipoBemJpaController = new TipoBemJpaController();
    private UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
    private AutorizacaoJpaController autorizacaoJpaController = new AutorizacaoJpaController();
    
    
    private String pfVId;
    private String pjVId;
    private String register;
    private String idfk;
    private String tabela;
    private String searchValue;
    private String sucessaoId;
    private boolean bemRepetido;

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            usuario = usuarioJpaController.findUsuarioByCPF(Cookie.getCookie("usuario")); 
 
            enderecoPessoaFisica = new EnderecoPessoa(new PessoaFisica(), new Endereco(), new ArrayList<Bem>());
            enderecoPessoaJuridica = new EnderecoPessoa(new PessoaJuridica(), new Endereco(), new ArrayList<Bem>());
            executado = new Executado();
            pessoaJuridicaSucessao = new PessoaJuridicaSucessao();
            bem = new Bem();

            cidadeEndList = new ArrayList<>();
            cidadeEleList = new ArrayList<>();
            cidadeNatList = new ArrayList<>();

            searchValue = "";
            register = "";
            idfk = "";
            tabela = "";
            bemRepetido = false;
        }
    }

    public void exibirModalNew() {
        bemList = new ArrayList<>();
        pessoaFisicaFisicaList = new ArrayList<>();
        pessoaFisicaJuridicaList = new ArrayList<>();
        pessoaJuridicaJuridicaList = new ArrayList<>();
        pfVId = "";
        pjVId = "";
        bem = new Bem();
        if (tabela.equals("PF")) {
            enderecoPessoaFisica = new EnderecoPessoa(new PessoaFisica(), new Endereco(), new ArrayList<Bem>());
            carregarFormularioModalPF();
        } else if (tabela.equals("PJ")) {
            enderecoPessoaJuridica = new EnderecoPessoa(new PessoaJuridica(), new Endereco(), new ArrayList<Bem>());
            carregarFormularioModalPJ();
        } else if (tabela.equals("BEM")) {
            bem.setTipo(idfk.substring(0, 2));
            bem.setIdFk(Integer.valueOf(idfk.substring(2)));
            bem.setStatus('A');
            tipoBemList = tipoBemJpaController.findTipoBemEntities();
        }
    }

    public void carregarFormularioModalPF() {
        paisList = paisJpaController.findPaisEntities();
        paisList.remove(paisJpaController.findBrasil());
        estadoList = estadoJpaController.findEstadoEntities();
        nacionalidadeList = new NacionalidadeJpaController().findNacionalidadeEntities();
        estadoCivilList = new EstadoCivilJpaController().findEstadoCivilEntities();
        funcaoList = funcaoJpaController.findFuncaoEntities();
        vinculoSocialList = new VinculoSocialJpaController().findVinculoSocialEntities();
        tipoBemList = tipoBemJpaController.findTipoBemEntities();
    }

    public void carregarFormularioModalPJ() {
        estadoList = estadoJpaController.findEstadoEntities();
        tipoEmpresarialList = new TipoEmpresarialJpaController().findTipoEmpresarialEntities();
        funcaoList = funcaoJpaController.findFuncaoEntities();
        tipoBemList = tipoBemJpaController.findTipoBemEntities();
    }

    public void getCidadesPeloEstado() {
        if (tabela.equals("PF")) {
            PessoaFisica pessoaFisica = (PessoaFisica) enderecoPessoaFisica.getPessoa();
            if (pessoaFisica.getEstadoFk() != null) {
                cidadeNatList = cidadeJpaController.getByStateId(pessoaFisica.getEstadoFk().getId());
            } else {
                cidadeNatList.clear();
            }
            if (enderecoPessoaFisica.getEndereco().getEstadoFk() != null) {
                cidadeEndList = cidadeJpaController.getByStateId(enderecoPessoaFisica.getEndereco().getEstadoFk().getId());
            } else {
                cidadeEndList.clear();
            }
            if (pessoaFisica.getEstadoEleitoralFk() != null) {
                cidadeEleList = cidadeJpaController.getByStateId(pessoaFisica.getEstadoEleitoralFk().getId());
            } else {
                cidadeEleList.clear();
            }
        } else if (tabela.equals("PJ")) {
            if (enderecoPessoaJuridica.getEndereco().getEstadoFk() != null) {
                cidadeEndList = cidadeJpaController.getByStateId(enderecoPessoaJuridica.getEndereco().getEstadoFk().getId());
            } else {
                cidadeEndList.clear();
            }
        }
    }

    public void adicionarBem() {
        Boolean add = false;
        if (!bemList.isEmpty()) {
            for (Bem b : bemList) {
                if (!bem.equalsValues(b)) {
                    add = true;
                }
            }
        } else {
            add = true;
        }
        if (add) {
            bemList.add(bem);
            bem.setStatus('A');
            bem = new Bem();
        }
    }

    public void removerBem(int index) {
        bemList.remove(index);
    }

    public void vincularPessoaFisica() {
        PessoaFisica pessoaFisica = pessoaFisicaJpaController.findPessoaFisica(Integer.valueOf(pfVId));
        if (tabela.equals("PF")) {
            PessoaFisicaFisica pessoaFisicaFisica = new PessoaFisicaFisica();
            pessoaFisicaFisica.setPessoaFisicaSecundariaFk(pessoaFisica);
            boolean exists = false;
            for (PessoaFisicaFisica pff : pessoaFisicaFisicaList) {
                if (pff.getPessoaFisicaSecundariaFk().getId().equals(pessoaFisica.getId())) {
                    exists = true;
                }
            }
            if (!exists) {
                pessoaFisicaFisicaList.add(pessoaFisicaFisica);
            }
        } else if (tabela.equals("PJ")) {
            PessoaFisicaJuridica pessoaFisicaJuridica = new PessoaFisicaJuridica();
            pessoaFisicaJuridica.setPessoaFisicaFk(pessoaFisica);
            boolean exists = false;
            for (PessoaFisicaJuridica pfj : pessoaFisicaJuridicaList) {
                if (pfj.getPessoaFisicaFk().getId().equals(pessoaFisica.getId())) {
                    exists = true;
                }
            }
            if (!exists) {
                pessoaFisicaJuridicaList.add(pessoaFisicaJuridica);
            }
        }

    }

    public void removerVinculoFisicoSocial(int index) {
        pessoaFisicaFisicaList.remove(index);
    }

    public void removerVinculoFisico(int index) {
        pessoaFisicaJuridicaList.remove(index);
    }

    public void vincularPessoaJuridica() {
        PessoaJuridica pessoaJuridica = pessoaJuridicaJpaController.findPessoaJuridica(Integer.valueOf(pjVId));
        if (tabela.equals("PF")) {
            PessoaFisicaJuridica pessoaFisicaJuridica = new PessoaFisicaJuridica();
            pessoaFisicaJuridica.setPessoaJuridicaFk(pessoaJuridica);
            boolean exists = false;
            for (PessoaFisicaJuridica pfj : pessoaFisicaJuridicaList) {
                if (pfj.getPessoaJuridicaFk().getCnpj().equals(pessoaJuridica.getCnpj())) {
                    exists = true;
                }
            }
            if (!exists) {
                pessoaFisicaJuridicaList.add(pessoaFisicaJuridica);
            }
        } else if (tabela.equals("PJ")) {
            PessoaJuridicaJuridica pessoaJuridicaJuridica = new PessoaJuridicaJuridica();
            pessoaJuridicaJuridica.setPessoaJuridicaSecundariaFk(pessoaJuridica);
            boolean exists = false;
            for (PessoaJuridicaJuridica pjj : pessoaJuridicaJuridicaList) {
                if (pjj.getPessoaJuridicaSecundariaFk().getId().equals(pessoaJuridica.getId())) {
                    exists = true;
                }
            }
            if (!exists) {
                pessoaJuridicaJuridicaList.add(pessoaJuridicaJuridica);
            }
        }
    }

    public void removerVinculoJuridico(int index) {
        pessoaJuridicaJuridicaList.remove(index);
    }

    public void cadastrarBem() throws Exception {
        bemRepetido = false;
        List<Bem> bemList = new ArrayList<>();
        if (bem.getTipo().equals("PF")) {
            bemList = bemJpaController.findPFBens(bem.getIdFk());
        } else if (bem.getTipo().equals("PJ")) {
            bemList = bemJpaController.findPJBens(bem.getIdFk());
        }
        for (Bem bem : bemList) {
            if (this.bem.equalsValues(bem)) {
                bemRepetido = true;
                break;
            }
        }
        if (!bemRepetido) {
            if (bem.getDataDeAquisicao() != null || bem.getDataDeTransferenciaOuExtincao() != null
                    || bem.getDescricao() != null || bem.getEndereco() != null || bem.getValor() != null
                    || bem.getTipoBemFk() != null) {
                bemJpaController.create(bem);
            }
        }
    }

    public void cadastrarPF() throws Exception {
        PessoaFisica pessoaFisica = (PessoaFisica) enderecoPessoaFisica.getPessoa();
        if (pessoaFisica.getRgOrgaoEmissor() != null) {
            pessoaFisica.setRgOrgaoEmissor(pessoaFisica.getRgOrgaoEmissor().toUpperCase());
        }
        if (pessoaFisica.getEstadoFk() != null) {
            pessoaFisica.setPaisFk(paisJpaController.findBrasil());
        }
        pessoaFisica.setStatus('A');
        pessoaFisica.setUsuarioFk(usuarioJpaController.findUsuarioByCPF(Cookie.getCookie("usuario")));
        pessoaFisica.setInstituicaoFk(autorizacaoJpaController.findAutorizacaoByCPF(Cookie.getCookie("usuario")).getInstituicaoFk());
        pessoaFisicaJpaController.create(pessoaFisica);
        Endereco endereco = enderecoPessoaFisica.getEndereco();
        endereco.setTipo("PF");
        endereco.setIdFk(pessoaFisica.getId());
        enderecoJpaController.create(endereco);
        for (PessoaFisicaJuridica pfj : pessoaFisicaJuridicaList) {
            pfj.setPessoaFisicaFk(pessoaFisica);
            pessoaFisicaJuridicaJpaController.create(pfj);
        }
        for (PessoaFisicaFisica pff : pessoaFisicaFisicaList) {
            pff.setPessoaFisicaPrimariaFk(pessoaFisica);
            pessoaFisicaFisicaJpaController.create(pff);
        }
        for (Bem b : bemList) {
            b.setTipo("PF");
            b.setIdFk(pessoaFisica.getId());
            bemJpaController.create(b);
        }
        GeradorLog.criar(pessoaFisica.getId(), "PF", 'C');
    }

    public void cadastrarPJ() throws Exception {
        PessoaJuridica pessoaJuridica = (PessoaJuridica) enderecoPessoaJuridica.getPessoa();
        pessoaJuridica.setStatus('A');
        pessoaJuridica.setUsuarioFk(usuarioJpaController.findUsuarioByCPF(Cookie.getCookie("usuario")));
        pessoaJuridica.setInstituicaoFk(autorizacaoJpaController.findAutorizacaoByCPF(Cookie.getCookie("usuario")).getInstituicaoFk());
        pessoaJuridicaJpaController.create(pessoaJuridica);
        Endereco endereco = enderecoPessoaJuridica.getEndereco();
        endereco.setTipo("PJ");
        endereco.setIdFk(pessoaJuridica.getId());
        enderecoJpaController.create(endereco);
        for (PessoaFisicaJuridica pfj : pessoaFisicaJuridicaList) {
            pfj.setPessoaJuridicaFk(pessoaJuridica);
            pessoaFisicaJuridicaJpaController.create(pfj);
        }
        for (PessoaJuridicaJuridica pjj : pessoaJuridicaJuridicaList) {
            pjj.setPessoaJuridicaPrimariaFk(pessoaJuridica);
            pessoaJuridicaJuridicaJpaController.create(pjj);
        }
        for (Bem b : bemList) {
            b.setTipo("PJ");
            b.setIdFk(pessoaJuridica.getId());
            bemJpaController.create(b);
        }
        GeradorLog.criar(pessoaJuridica.getId(), "PJ", 'C');
    }

    public void exibirModalInfo() {
        if (tabela.equals("PF")) {
            PessoaFisica pessoaFisica = pessoaFisicaJpaController.findPessoaFisica(Integer.valueOf(idfk));
            enderecoPessoaFisica = new EnderecoPessoa(pessoaFisica, enderecoJpaController.findPFAddress(pessoaFisica.getId()), bemJpaController.findPFBens(pessoaFisica.getId()));
            executado = new Executado();
            pessoaJuridicaSucessao = new PessoaJuridicaSucessao();
        } else if (tabela.equals("PJ")) {
            PessoaJuridica pessoaJuridica = pessoaJuridicaJpaController.findPessoaJuridica(Integer.valueOf(idfk));
            enderecoPessoaJuridica = new EnderecoPessoa(pessoaJuridica, enderecoJpaController.findPJAddress(pessoaJuridica.getId()), bemJpaController.findPJBens(pessoaJuridica.getId()));
            executado = new Executado();
            pessoaJuridicaSucessao = new PessoaJuridicaSucessao();
        } else if (tabela.equals("PJUD")) {
            ProcessoJudicial pjud = new ProcessoJudicialJpaController().findProcessoJudicial(Integer.valueOf(idfk));
            if (pjud.getExecutado().equals("PF")) {
                PessoaFisica pf = pessoaFisicaJpaController.findPessoaFisica(pjud.getExecutadoFk());
                executado = new Executado(pjud, new EnderecoPessoa(pf, enderecoJpaController.findPFAddress(pf.getId()), bemJpaController.findPFBens(pf.getId())), citacaoJpaController.findByPJUD(pjud.getId()), carregarSocioRedirecionamento(redirecionamentoJpaController.findByPJUD(pjud.getId())), penhoraJpaController.findByPJUD(pjud.getId()), aquisicaoBemJpaController.findByPJUD(pjud.getId()));
            } else {
                PessoaJuridica pj = pessoaJuridicaJpaController.findPessoaJuridica(pjud.getExecutadoFk());
                executado = new Executado(pjud, new EnderecoPessoa(pj, enderecoJpaController.findPFAddress(pj.getId()), bemJpaController.findPJBens(pj.getId())), citacaoJpaController.findByPJUD(pjud.getId()), carregarSocioRedirecionamento(redirecionamentoJpaController.findByPJUD(pjud.getId())), penhoraJpaController.findByPJUD(pjud.getId()), aquisicaoBemJpaController.findByPJUD(pjud.getId()));
            }
            pessoaJuridicaSucessao = new PessoaJuridicaSucessao();
        } else if (tabela.equals("PJS")) {
            pessoaJuridicaSucessao = pessoaJuridicaSucessaoJpaController.findPessoaJuridicaSucessao(Integer.valueOf(idfk));
            executado = new Executado();
        }
    }

    public int checkCitacaoVazia(List<Citacao> citacaoList, String tipo) {
        int quantidade = 0;
        if (citacaoList != null) {
            for (Citacao citacao : citacaoList) {
                quantidade = citacao.getTipoCitacao().equals(tipo) ? quantidade + 1 : quantidade;
            }
        }
        return quantidade;
    }

    public String loadSocio(String tipo, String id) {
        if (tipo != null) {
            if (tipo.equals("PF")) {
                PessoaFisica pf = pessoaFisicaJpaController.findPessoaFisica(Integer.valueOf(id));
                String cpf = (pf.getCpf() == null ? "Sem CPF" : pf.getCpf().substring(0, 3) + "." + pf.getCpf().substring(3, 6) + "." + pf.getCpf().substring(6, 9) + "-" + pf.getCpf().substring(9));
                return cpf + " - " + pf.getNome();
            } else {
                PessoaJuridica pj = pessoaJuridicaJpaController.findPessoaJuridica(Integer.valueOf(id));
                String cnpj = pj.getCnpj().substring(0, 2) + "." + pj.getCnpj().substring(2, 5) + "." + pj.getCnpj().substring(5, 8) + "/" + pj.getCnpj().substring(8, 12) + "-" + pj.getCnpj().substring(12);
                return cnpj + " - " + pj.getNome();
            }
        }
        return "-";
    }

    public List<SocioRedirecionamento> carregarSocioRedirecionamento(List<Redirecionamento> redirecionamentoList) {
        List<SocioRedirecionamento> socioRedirecionamentoList = new ArrayList<>();
        for (Redirecionamento redirecionamento : redirecionamentoList) {
            if (redirecionamento.getSocio().equals("PF")) {
                socioRedirecionamentoList.add(new SocioRedirecionamento(pessoaFisicaJpaController.findPessoaFisica(redirecionamento.getSocioFk()), redirecionamento));
            } else {
                socioRedirecionamentoList.add(new SocioRedirecionamento(pessoaJuridicaJpaController.findPessoaJuridica(redirecionamento.getSocioFk()), redirecionamento));
            }
        }
        return socioRedirecionamentoList;
    }

    public void exibirSucessao() {
        pessoaJuridicaSucessao = pessoaJuridicaSucessaoJpaController.findPessoaJuridicaSucessao(Integer.valueOf(sucessaoId));
    }

    public void search() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/procura-geral.xhtml?value=" + searchValue);
    }

    public String setInversao(String vinculo) {
        switch (vinculo) {
            case "Irmão/Irmã":
                return "Irmão/Irmã";
            case "Pai/Mãe":
                return "Filho/Filha";
            case "Tio/Tia":
                return "Sobrinho/Sobrinha";
            case "Avô/Avó":
                return "Neto/Neta";
            case "Bisavô/Bisavó":
                return "Bisneto/Bisneta";
            case "Trisavô/Trisavó":
                return "Trineto/Trineta";
            case "Sogro/Sogra":
                return "Genro/Nora";
            case "Cônjuge":
                return "Cônjuge";
            case "Filho/Filha":
                return "Pai/Mãe";
            case "Primo/Prima":
                return "Primo/Prima";
            case "Cunhado/Cunhada":
                return "Cunhado/Cunhada";
            case "Genro/Nora":
                return "Sogro/Sogra";
            case "Neto/Neta":
                return "Avô/Avó";
            case "Bisneto/Bisneta":
                return "Bisavô/Bisavó";
            case "Trineto/Trineta":
                return "Trisavô/Trisavó";
            case "Sobrinho/Sobrinha":
                return "Tio/Tia";
            case "Empregado":
                return "Empregador";
            case "Empregador":
                return "Empregado";
            case "Empregado doméstico":
                return "Empregador";
            case "Estagiário":
                return "Empregador";
            case "Enteado/Enteada":
                return "Padrasto/Madrasta";
            case "Padrasto/Madrasta":
                return "Enteado/Enteada";
            default:
                return vinculo;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Executado getExecutado() {
        return executado;
    }

    public void setExecutado(Executado executado) {
        this.executado = executado;
    }

    public String getIdfk() {
        return idfk;
    }

    public void setIdfk(String idfk) {
        this.idfk = idfk;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public PessoaJuridicaSucessao getPessoaJuridicaSucessao() {
        return pessoaJuridicaSucessao;
    }

    public void setPessoaJuridicaSucessao(PessoaJuridicaSucessao pessoaJuridicaSucessao) {
        this.pessoaJuridicaSucessao = pessoaJuridicaSucessao;
    }

    public String getSucessaoId() {
        return sucessaoId;
    }

    public void setSucessaoId(String sucessaoId) {
        this.sucessaoId = sucessaoId;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public List<TipoEmpresarial> getTipoEmpresarialList() {
        return tipoEmpresarialList;
    }

    public void setTipoEmpresarialList(List<TipoEmpresarial> tipoEmpresarialList) {
        this.tipoEmpresarialList = tipoEmpresarialList;
    }

    public List<Estado> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(List<Estado> estadoList) {
        this.estadoList = estadoList;
    }

    public List<Cidade> getCidadeEndList() {
        return cidadeEndList;
    }

    public void setCidadeEndList(List<Cidade> cidadeEndList) {
        this.cidadeEndList = cidadeEndList;
    }

    public List<Funcao> getFuncaoList() {
        return funcaoList;
    }

    public void setFuncaoList(List<Funcao> funcaoList) {
        this.funcaoList = funcaoList;
    }

    public EnderecoPessoa getEnderecoPessoaFisica() {
        return enderecoPessoaFisica;
    }

    public void setEnderecoPessoaFisica(EnderecoPessoa enderecoPessoaFisica) {
        this.enderecoPessoaFisica = enderecoPessoaFisica;
    }

    public EnderecoPessoa getEnderecoPessoaJuridica() {
        return enderecoPessoaJuridica;
    }

    public void setEnderecoPessoaJuridica(EnderecoPessoa enderecoPessoaJuridica) {
        this.enderecoPessoaJuridica = enderecoPessoaJuridica;
    }

    public List<PessoaFisicaJuridica> getPessoaFisicaJuridicaList() {
        return pessoaFisicaJuridicaList;
    }

    public void setPessoaFisicaJuridicaList(List<PessoaFisicaJuridica> pessoaFisicaJuridicaList) {
        this.pessoaFisicaJuridicaList = pessoaFisicaJuridicaList;
    }

    public String getPfVId() {
        return pfVId;
    }

    public void setPfVId(String pfVId) {
        this.pfVId = pfVId;
    }

    public String getPjVId() {
        return pjVId;
    }

    public void setPjVId(String pjVId) {
        this.pjVId = pjVId;
    }

    public List<PessoaJuridicaJuridica> getPessoaJuridicaJuridicaList() {
        return pessoaJuridicaJuridicaList;
    }

    public void setPessoaJuridicaJuridicaList(List<PessoaJuridicaJuridica> pessoaJuridicaJuridicaList) {
        this.pessoaJuridicaJuridicaList = pessoaJuridicaJuridicaList;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public List<Nacionalidade> getNacionalidadeList() {
        return nacionalidadeList;
    }

    public void setNacionalidadeList(List<Nacionalidade> nacionalidadeList) {
        this.nacionalidadeList = nacionalidadeList;
    }

    public List<EstadoCivil> getEstadoCivilList() {
        return estadoCivilList;
    }

    public void setEstadoCivilList(List<EstadoCivil> estadoCivilList) {
        this.estadoCivilList = estadoCivilList;
    }

    public List<Cidade> getCidadeEleList() {
        return cidadeEleList;
    }

    public void setCidadeEleList(List<Cidade> cidadeEleList) {
        this.cidadeEleList = cidadeEleList;
    }

    public List<Cidade> getCidadeNatList() {
        return cidadeNatList;
    }

    public void setCidadeNatList(List<Cidade> cidadeNatList) {
        this.cidadeNatList = cidadeNatList;
    }

    public List<VinculoSocial> getVinculoSocialList() {
        return vinculoSocialList;
    }

    public void setVinculoSocialList(List<VinculoSocial> vinculoSocialList) {
        this.vinculoSocialList = vinculoSocialList;
    }

    public List<PessoaFisicaFisica> getPessoaFisicaFisicaList() {
        return pessoaFisicaFisicaList;
    }

    public void setPessoaFisicaFisicaList(List<PessoaFisicaFisica> pessoaFisicaFisicaList) {
        this.pessoaFisicaFisicaList = pessoaFisicaFisicaList;
    }

    public Bem getBem() {
        return bem;
    }

    public void setBem(Bem bem) {
        this.bem = bem;
    }

    public List<Bem> getBemList() {
        return bemList;
    }

    public void setBemList(List<Bem> bemList) {
        this.bemList = bemList;
    }

    public List<TipoBem> getTipoBemList() {
        return tipoBemList;
    }

    public void setTipoBemList(List<TipoBem> tipoBemList) {
        this.tipoBemList = tipoBemList;
    }

}
