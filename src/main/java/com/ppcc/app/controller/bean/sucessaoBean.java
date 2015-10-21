/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppcc.app.controller.bean;

import com.ppcc.app.model.entity.PessoaJuridica;
import com.ppcc.app.model.entity.PessoaJuridicaSucessao;
import com.ppcc.app.model.entity.PessoaJuridicaSucessaoHistorico;
import com.ppcc.app.model.jpa.controller.PessoaJuridicaJpaController;
import com.ppcc.app.model.jpa.controller.PessoaJuridicaSucessaoHistoricoJpaController;
import com.ppcc.app.model.jpa.controller.PessoaJuridicaSucessaoJpaController;
import com.ppcc.app.model.jpa.controller.UsuarioJpaController;
import com.ppcc.app.model.jpa.controller.UtilJpaController;
import com.ppcc.app.util.Cookie;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.ppcc.app.util.GeradorLog;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Pedro
 */
@Named
@ViewScoped
public class sucessaoBean implements Serializable {

    private String succeed;
    private String sucedida;
    private String sucessora;
    private String dataDeSucessao;
    
    private PessoaJuridicaJpaController pessoaJuridicaJpaController = new PessoaJuridicaJpaController();
    private PessoaJuridicaSucessaoJpaController pessoaJuridicaSucessaoJpaController = new PessoaJuridicaSucessaoJpaController();

    private PessoaJuridicaSucessao pessoaJuridicaSucessao;
    private PessoaJuridicaSucessaoHistorico pessoaJuridicaSucessaoHistorico;


    public void init() throws IOException {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            sucedida = "";
            sucessora = "";
            succeed = "";
            dataDeSucessao = "";

            pessoaJuridicaSucessao = new PessoaJuridicaSucessao();
        }
    }

    public void suceder() throws Exception {
        if (!sucedida.equals(sucessora)) {
            PessoaJuridica pjSucedida = pessoaJuridicaJpaController.findPessoaJuridica(Integer.valueOf(sucedida));
            PessoaJuridica pjSucessora = pessoaJuridicaJpaController.findPessoaJuridica(Integer.valueOf(sucessora));
            boolean exists = true;
            pessoaJuridicaSucessao = pessoaJuridicaSucessaoJpaController.findDuplicates(pjSucedida, pjSucessora);
            if (pessoaJuridicaSucessao == null) {
                pessoaJuridicaSucessao = new PessoaJuridicaSucessao();
                exists = false;
            } else {
                prepararHistorico(pessoaJuridicaSucessao);
            }
            pessoaJuridicaSucessao.setUsuarioFk(new UsuarioJpaController().findUsuarioByCPF(Cookie.getCookie("usuario")));
            pessoaJuridicaSucessao.setPessoaJuridicaSucedidaFk(pjSucedida);
            pessoaJuridicaSucessao.setPessoaJuridicaSucessoraFk(pjSucessora);
            pessoaJuridicaSucessao.setDataDeSucessao(dataDeSucessao);
            pessoaJuridicaSucessao.setStatus('A');
            if (exists) {
                if (!pessoaJuridicaSucessao.equalsValues(pessoaJuridicaSucessaoJpaController.findDuplicates(pjSucedida, pjSucessora))) {
                    pessoaJuridicaSucessaoJpaController.edit(pessoaJuridicaSucessao);
                    pessoaJuridicaSucessaoHistorico.setDataDeModificacao(new UtilJpaController().findServerTime());
                    new PessoaJuridicaSucessaoHistoricoJpaController().create(pessoaJuridicaSucessaoHistorico);
                    GeradorLog.criar(pessoaJuridicaSucessao.getId(), "PJS", 'U');
                    succeed = "success";
                    sucedida = "";
                    sucessora = "";
                    dataDeSucessao = "";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sucessão alterada com sucesso!", null));
                } else {
                    succeed = "info";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum campo foi alterado.", null));
                }
            } else {
                pessoaJuridicaSucessaoJpaController.create(pessoaJuridicaSucessao);
                GeradorLog.criar(pessoaJuridicaSucessao.getId(), "PJS", 'C');
                succeed = "success";
                sucedida = "";
                sucessora = "";
                dataDeSucessao = "";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sucessão realizada com sucesso!", null));
            }
        } else {
            succeed = "fail";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione duas empresas diferentes.", null));
        }
    }

    public void checkSucessoes() {
        if (sucedida != null && sucessora != null) {
            if (!sucedida.equals(sucessora)) {
                PessoaJuridica pjSucedida = pessoaJuridicaJpaController.findPessoaJuridica(Integer.valueOf(sucedida));
                PessoaJuridica pjSucessora = pessoaJuridicaJpaController.findPessoaJuridica(Integer.valueOf(sucessora));
                pessoaJuridicaSucessao = pessoaJuridicaSucessaoJpaController.findDuplicates(pjSucedida, pjSucessora);
                if (pessoaJuridicaSucessao != null) {
                    if (pessoaJuridicaSucessao.getStatus().equals('I')) {
                        succeed = "warning";
                        if (pessoaJuridicaSucessao.getPessoaJuridicaSucedidaFk().equals(pjSucessora) && pessoaJuridicaSucessao.getPessoaJuridicaSucessoraFk().equals(pjSucedida)) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existe uma sucessão invertida entre as empresas selecionadas, porém desativada. Caso opte em suceder, a sucessão anterior será substituída e reativada.", null));
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Esta sucessão foi desativada. Caso opte em suceder, a mesma será reativada.", null));
                        }
                    } else if (pessoaJuridicaSucessao.getPessoaJuridicaSucedidaFk().equals(pjSucessora) && pessoaJuridicaSucessao.getPessoaJuridicaSucessoraFk().equals(pjSucedida)) {
                        succeed = "warning";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existe uma sucessão invertida entre as empresas selecionadas. Caso opte em suceder, a sucessão anterior será substituída.", null));
                    } else {
                        succeed = "";
                    }
                } else {
                    succeed = "";
                }
            } else {
                succeed = "warning";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha duas empresas diferentes.", null));
            }
        } else {
            succeed = "";
        }
    }

    private void prepararHistorico(PessoaJuridicaSucessao pessoaJuridicaSucessao) {
        pessoaJuridicaSucessaoHistorico = new PessoaJuridicaSucessaoHistorico();

        pessoaJuridicaSucessaoHistorico.setDataDeSucessao(pessoaJuridicaSucessao.getDataDeSucessao());
        pessoaJuridicaSucessaoHistorico.setUsuarioFk(pessoaJuridicaSucessao.getUsuarioFk());
        pessoaJuridicaSucessaoHistorico.setPessoaJuridicaSucedidaFk(pessoaJuridicaSucessao.getPessoaJuridicaSucedidaFk());
        pessoaJuridicaSucessaoHistorico.setPessoaJuridicaSucessoraFk(pessoaJuridicaSucessao.getPessoaJuridicaSucessoraFk());
        pessoaJuridicaSucessaoHistorico.setPessoaJuridicaSucessaoFk(pessoaJuridicaSucessao);
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getSucedida() {
        return sucedida;
    }

    public void setSucedida(String sucedida) {
        this.sucedida = sucedida;
    }

    public String getSucessora() {
        return sucessora;
    }

    public void setSucessora(String sucessora) {
        this.sucessora = sucessora;
    }

    public PessoaJuridicaSucessao getPessoaJuridicaSucessao() {
        return pessoaJuridicaSucessao;
    }

    public void setPessoaJuridicaSucessao(PessoaJuridicaSucessao pessoaJuridicaSucessao) {
        this.pessoaJuridicaSucessao = pessoaJuridicaSucessao;
    }

    public String getDataDeSucessao() {
        return dataDeSucessao;
    }

    public void setDataDeSucessao(String dataDeSucessao) {
        this.dataDeSucessao = dataDeSucessao;
    }

}
