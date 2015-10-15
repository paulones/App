/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppcc.app.controller.bean;

import com.ppcc.app.model.entity.AquisicaoBem;
import com.ppcc.app.model.entity.AquisicaoBemHistorico;
import com.ppcc.app.model.entity.Bem;
import com.ppcc.app.model.entity.Citacao;
import com.ppcc.app.model.entity.CitacaoHistorico;
import com.ppcc.app.model.entity.EnderecoPessoa;
import com.ppcc.app.model.entity.Executado;
import com.ppcc.app.model.entity.ExecutadoHistorico;
import com.ppcc.app.model.entity.Instituicao;
import com.ppcc.app.model.entity.Penhora;
import com.ppcc.app.model.entity.PenhoraHistorico;
import com.ppcc.app.model.entity.PessoaFisica;
import com.ppcc.app.model.entity.PessoaFisicaJuridica;
import com.ppcc.app.model.entity.PessoaJuridica;
import com.ppcc.app.model.entity.PessoaJuridicaJuridica;
import com.ppcc.app.model.entity.ProcessoJudicial;
import com.ppcc.app.model.entity.ProcessoJudicialHistorico;
import com.ppcc.app.model.entity.Procurador;
import com.ppcc.app.model.entity.Redirecionamento;
import com.ppcc.app.model.entity.RedirecionamentoHistorico;
import com.ppcc.app.model.entity.Situacao;
import com.ppcc.app.model.entity.SocioRedirecionamento;
import com.ppcc.app.model.entity.SocioRedirecionamentoHistorico;
import com.ppcc.app.model.entity.TipoPenhora;
import com.ppcc.app.model.entity.TipoProcesso;
import com.ppcc.app.model.entity.TipoRecurso;
import com.ppcc.app.model.entity.VinculoProcessual;
import com.ppcc.app.model.entity.VinculoProcessualHistorico;
import com.ppcc.app.model.jpa.controller.AquisicaoBemHistoricoJpaController;
import com.ppcc.app.model.jpa.controller.AquisicaoBemJpaController;
import com.ppcc.app.model.jpa.controller.AutorizacaoJpaController;
import com.ppcc.app.model.jpa.controller.BemJpaController;
import com.ppcc.app.model.jpa.controller.CitacaoHistoricoJpaController;
import com.ppcc.app.model.jpa.controller.CitacaoJpaController;
import com.ppcc.app.model.jpa.controller.EnderecoJpaController;
import com.ppcc.app.model.jpa.controller.PenhoraHistoricoJpaController;
import com.ppcc.app.model.jpa.controller.PenhoraJpaController;
import com.ppcc.app.model.jpa.controller.PessoaFisicaJpaController;
import com.ppcc.app.model.jpa.controller.PessoaFisicaJuridicaJpaController;
import com.ppcc.app.model.jpa.controller.PessoaJuridicaJpaController;
import com.ppcc.app.model.jpa.controller.PessoaJuridicaJuridicaJpaController;
import com.ppcc.app.model.jpa.controller.ProcessoJudicialHistoricoJpaController;
import com.ppcc.app.model.jpa.controller.ProcessoJudicialJpaController;
import com.ppcc.app.model.jpa.controller.ProcuradorJpaController;
import com.ppcc.app.model.jpa.controller.RedirecionamentoHistoricoJpaController;
import com.ppcc.app.model.jpa.controller.RedirecionamentoJpaController;
import com.ppcc.app.model.jpa.controller.SituacaoJpaController;
import com.ppcc.app.model.jpa.controller.TipoPenhoraJpaController;
import com.ppcc.app.model.jpa.controller.TipoProcessoJpaController;
import com.ppcc.app.model.jpa.controller.TipoRecursoJpaController;
import com.ppcc.app.model.jpa.controller.UsuarioJpaController;
import com.ppcc.app.model.jpa.controller.UtilJpaController;
import com.ppcc.app.model.jpa.controller.VinculoProcessualHistoricoJpaController;
import com.ppcc.app.model.jpa.controller.VinculoProcessualJpaController;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import com.ppcc.app.util.Cookie;
import com.ppcc.app.util.GeradorLog;
import com.ppcc.app.util.MetodosConvencionais;

/**
 *
 * @author paulones
 */
@ViewScoped
@ManagedBean(name = "processoJudicialBean")
public class ProcessoJudicialBean implements Serializable {

    private ProcessoJudicial processoJudicial;
    private Executado executado;
    private EnderecoPessoa enderecoPessoaFisica;
    private EnderecoPessoa enderecoPessoaJuridica;
    private EnderecoPessoa enderecoPessoaModalFisica;
    private EnderecoPessoa enderecoPessoaModalJuridica;
    private VinculoProcessual vinculoProcessual;
    private ProcessoJudicial oldProcessoJudicial;
    private ProcessoJudicialHistorico processoJudicialHistorico;
    private ExecutadoHistorico executadoHistorico;
    private TipoPenhora tipoPenhora;

    private List<TipoRecurso> tipoDeRecursoList;
    private List<TipoProcesso> tipoDoProcessoList;
    private List<VinculoProcessual> vinculoProcessualList;
    private List<VinculoProcessualHistorico> vinculoProcessualHistoricoList;
    private List<ExecutadoHistorico> executadoHistoricoList;
    private List<Situacao> situacaoList;
    private List<Procurador> procuradorList;
    private List<ProcessoJudicial> executadoProcessoJudicialList;
    private List<Citacao> arList;
    private List<Citacao> oficialList;
    private List<Citacao> editalList;
    private List<Citacao> enderecoSocioList;
    private List<Object> socioList;
    private List<SocioRedirecionamento> socioRedirecionamentoList;
    private List<TipoPenhora> tipoPenhoraList;
    private List<Citacao> oldCitacaoList;
    private List<SocioRedirecionamento> oldSocioRedirecionamentoList;
    private List<CitacaoHistorico> citacaoHistoricoList;
    private List<RedirecionamentoHistorico> redirecionamentoHistoricoList;
    private List<Penhora> penhoraList;
    private List<PenhoraHistorico> penhoraHistoricoList;
    private List<Penhora> oldPenhoraList;
    private List<Bem> bemList;
    private List<List<AquisicaoBem>> listOfAquisicaoBemList;
    private List<AquisicaoBem> oldAquisicaoBemList;
    private List<AquisicaoBemHistorico> aquisicaoBemHistoricoList;
    private List<List<AquisicaoBemHistorico>> listOfAquisicaoBemHistoricoList;
    
    private AquisicaoBemJpaController aquisicaoBemJpaController = new AquisicaoBemJpaController();
    private AquisicaoBemHistoricoJpaController aquisicaoBemHistoricoJpaController = new AquisicaoBemHistoricoJpaController();
    private BemJpaController bemJpaController = new BemJpaController();
    private CitacaoJpaController citacaoJpaController = new CitacaoJpaController();
    private CitacaoHistoricoJpaController citacaoHistoricoJpaController = new CitacaoHistoricoJpaController();
    private EnderecoJpaController enderecoJpaController = new EnderecoJpaController();
    private PenhoraJpaController penhoraJpaController = new PenhoraJpaController();
    private PenhoraHistoricoJpaController penhoraHistoricoJpaController = new PenhoraHistoricoJpaController();
    private PessoaFisicaJpaController pessoaFisicaJpaController = new PessoaFisicaJpaController();
    private PessoaJuridicaJpaController pessoaJuridicaJpaController = new PessoaJuridicaJpaController();
    private ProcessoJudicialJpaController processoJudicialJpaController = new ProcessoJudicialJpaController();
    private ProcessoJudicialHistoricoJpaController processoJudicialHistoricoJpaController = new ProcessoJudicialHistoricoJpaController();
    private RedirecionamentoJpaController redirecionamentoJpaController = new RedirecionamentoJpaController();
    private RedirecionamentoHistoricoJpaController redirecionamentoHistoricoJpaController = new RedirecionamentoHistoricoJpaController();
    private TipoPenhoraJpaController tipoPenhora1JpaController = new TipoPenhoraJpaController();
    private UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
    private VinculoProcessualJpaController vinculoProcessualJpaController = new VinculoProcessualJpaController();
    
    
    
    private Integer vinculos;
    private String executadoPF;
    private String executadoPJ;
    private String register;
    private String redirect;
    private String pjudId;
    private boolean edit;
    private String history;
    private Integer ars;
    private Integer oficiais;
    private Integer editais;
    private Integer enderecosSocios;
    private Character redirecionamento;
    private boolean initialLoad;

    public void init() throws IOException {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            boolean isRegisterPage = FacesContext.getCurrentInstance().getViewRoot().getViewId().lastIndexOf("cadastrar") > -1;
            boolean isSearchPage = FacesContext.getCurrentInstance().getViewRoot().getViewId().lastIndexOf("consultar") > -1;

            executado = new Executado();
            processoJudicial = new ProcessoJudicial();
            enderecoPessoaFisica = new EnderecoPessoa();
            enderecoPessoaJuridica = new EnderecoPessoa();
            enderecoPessoaModalFisica = new EnderecoPessoa();
            oldProcessoJudicial = new ProcessoJudicial();
            tipoPenhora = new TipoPenhora();

            vinculos = 0;
            ars = 0;
            oficiais = 0;
            editais = 0;
            enderecosSocios = 0;
            executadoPF = null;
            executadoPJ = null;
            register = "";
            redirect = Cookie.getCookie("FacesMessage");
            Cookie.apagarCookie("FacesMessage");

            vinculoProcessualList = new ArrayList<>();
            arList = new ArrayList<>();
            oficialList = new ArrayList<>();
            editalList = new ArrayList<>();
            enderecoSocioList = new ArrayList<>();
            socioList = new ArrayList<>();
            socioRedirecionamentoList = new ArrayList<>();
            penhoraList = new ArrayList<>();
            listOfAquisicaoBemList = new ArrayList<>();

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (isRegisterPage) {
                /*
                 Tela cadastro.xhtml. Se houver "id" na url, entra na condição de alteração.
                 Caso contrário, apenas carrega o formulário
                 */
                if (request.getParameter("id") == null) {   // Novo
                    edit = false;
                    carregarFormulario();
                } else {                                    // Alteração
                    try {
                        Integer id = Integer.valueOf(request.getParameter("id"));
                        processoJudicial = processoJudicialJpaController.findProcessoJudicial(id);
                        if (processoJudicial == null) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrar.xhtml");
                        } else {
                            edit = true;
                            for (VinculoProcessual vinculoProcessual : processoJudicial.getVinculoProcessualCollection()) {
                                vinculoProcessualList.add(vinculoProcessual);
                            }
                            List<Citacao> citacaoList = citacaoJpaController.findByPJUD(id);
                            for (Citacao citacao : citacaoList) {
                                if (citacao.getTipoCitacao().equals("AR")) {
                                    arList.add(citacao);
                                    ars++;
                                } else if (citacao.getTipoCitacao().equals("OJ")) {
                                    oficialList.add(citacao);
                                    oficiais++;
                                } else if (citacao.getTipoCitacao().equals("ED")) {
                                    editalList.add(citacao);
                                    editais++;
                                } else if (citacao.getTipoCitacao().equals("ES")) {
                                    if (citacao.getSocio() != null && citacao.getSocioFk() != null) {
                                        citacao.setSocioTipo(citacao.getSocio() + citacao.getSocioFk());
                                    }
                                    enderecoSocioList.add(citacao);
                                    enderecosSocios++;
                                }
                            }
                            socioRedirecionamentoList = carregarSocioRedirecionamento(redirecionamentoJpaController.findByPJUD(id));
                            initialLoad = !socioRedirecionamentoList.isEmpty();
                            oldSocioRedirecionamentoList = new ArrayList<>();
                            oldSocioRedirecionamentoList = carregarSocioRedirecionamento(redirecionamentoJpaController.findByPJUD(id));
                            
                            vinculos = vinculoProcessualList.size();
                            if (processoJudicial.getExecutado().equals("PF")) {
                                executadoPF = String.valueOf(processoJudicial.getExecutadoFk());
                                listarSocios("PF");
                            } else {
                                executadoPJ = String.valueOf(processoJudicial.getExecutadoFk());
                                listarSocios("PJ");
                            }
                            refreshListaDeBens(null);

                            oldCitacaoList = citacaoJpaController.findByPJUD(id);
                            for (Citacao citacao : oldCitacaoList) {
                                if (citacao.getTipoCitacao().equals("ES")) {
                                    if (citacao.getSocio() != null && citacao.getSocioFk() != null) {
                                        citacao.setSocioTipo(citacao.getSocio() + citacao.getSocioFk());
                                    }
                                }
                            }
                            oldPenhoraList = penhoraJpaController.findByPJUD(id);
                            for (Penhora penhora : oldPenhoraList) {
                                if (penhora.getSocio() != null && penhora.getSocioFk() != null) {
                                    penhora.setSocioTipo(penhora.getSocio() + penhora.getSocioFk());
                                }
                            }
                            List<AquisicaoBem> aquisicaoBemList = aquisicaoBemJpaController.findByPJUD(id);
                            int i = 0;
                            List<AquisicaoBem> aquisicaoBemListTemp = new ArrayList<>();
                            while (i < aquisicaoBemList.size()) {
                                aquisicaoBemListTemp.add(aquisicaoBemList.get(i));
                                if ((i + 1) % 4 == 0) {
                                    listOfAquisicaoBemList.add(aquisicaoBemListTemp);
                                    aquisicaoBemListTemp = new ArrayList<>();
                                }
                                i++;
                            }
                            oldAquisicaoBemList = aquisicaoBemJpaController.findByPJUD(id);
                            oldProcessoJudicial = processoJudicialJpaController.findProcessoJudicial(id);

                            prepararHistorico(processoJudicial, citacaoList, socioRedirecionamentoList, oldPenhoraList, aquisicaoBemList);

                            carregarFormulario();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrar.xhtml");
                    }
                }
            }
        }
    }

    public void carregarHistorico(String idStr, String tipo) {
        Integer id = Integer.valueOf(idStr);
        processoJudicial = processoJudicialJpaController.findProcessoJudicial(id);
        executadoHistoricoList = new ArrayList<>();
        EnderecoPessoa enderecoPessoa = new EnderecoPessoa();
        // Inserção do registro atual
        if (processoJudicial.getExecutado().equals("PF")) {
            PessoaFisica pessoaFisica = pessoaFisicaJpaController.findPessoaFisica(processoJudicial.getExecutadoFk());
            enderecoPessoa = new EnderecoPessoa(pessoaFisica, enderecoJpaController.findPFAddress(pessoaFisica.getId()), bemJpaController.findPFBens(pessoaFisica.getId()));
        } else if (processoJudicial.getExecutado().equals("PJ")) {
            PessoaJuridica pessoaJuridica = pessoaJuridicaJpaController.findPessoaJuridica(processoJudicial.getExecutadoFk());
            enderecoPessoa = new EnderecoPessoa(pessoaJuridica, enderecoJpaController.findPJAddress(pessoaJuridica.getId()), bemJpaController.findPJBens(pessoaJuridica.getId()));
        }
        executadoHistorico = prepararRegistroAtual(processoJudicial, enderecoPessoa, citacaoJpaController.findByPJUD(processoJudicial.getId()), carregarSocioRedirecionamento(redirecionamentoJpaController.findByPJUD(processoJudicial.getId())), penhoraJpaController.findByPJUD(processoJudicial.getId()), aquisicaoBemJpaController.findByPJUD(processoJudicial.getId()));
        executadoHistoricoList.add(executadoHistorico);
        // Inserção dos históricos
        List<ProcessoJudicialHistorico> processoJudicialHistoricoList = processoJudicialHistoricoJpaController.findAllByPJUD(id);
        for (ProcessoJudicialHistorico pjh : processoJudicialHistoricoList) {
            if (pjh.getExecutado().equals("PF")) {
                PessoaFisica pessoaFisica = pessoaFisicaJpaController.findPessoaFisica(pjh.getExecutadoFk());
                enderecoPessoa = new EnderecoPessoa(pessoaFisica, enderecoJpaController.findPFAddress(pessoaFisica.getId()), bemJpaController.findPFBens(pessoaFisica.getId()));
                executadoHistorico = new ExecutadoHistorico(pjh, enderecoPessoa, null, citacaoHistoricoJpaController.findByPJUD(pjh.getId()), carregarSocioRedirecionamentoHistorico(redirecionamentoHistoricoJpaController.findByPJUD(pjh.getId())), penhoraHistoricoJpaController.findByPJUD(pjh.getId()), aquisicaoBemHistoricoJpaController.findByPJUD(pjh.getId()));
            } else if (pjh.getExecutado().equals("PJ")) {
                PessoaJuridica pessoaJuridica = pessoaJuridicaJpaController.findPessoaJuridica(pjh.getExecutadoFk());
                enderecoPessoa = new EnderecoPessoa(pessoaJuridica, enderecoJpaController.findPJAddress(pessoaJuridica.getId()), bemJpaController.findPJBens(pessoaJuridica.getId()));
                executadoHistorico = new ExecutadoHistorico(pjh, null, enderecoPessoa, citacaoHistoricoJpaController.findByPJUD(pjh.getId()), carregarSocioRedirecionamentoHistorico(redirecionamentoHistoricoJpaController.findByPJUD(pjh.getId())), penhoraHistoricoJpaController.findByPJUD(pjh.getId()), aquisicaoBemHistoricoJpaController.findByPJUD(pjh.getId()));
            }
            executadoHistoricoList.add(executadoHistorico);
        }
        history = tipo;
    }

    private void carregarFormulario() { // Carregar listas do formulário
        tipoDeRecursoList = new TipoRecursoJpaController().findTipoRecursoEntities();
        tipoDoProcessoList = new TipoProcessoJpaController().findTipoProcessoEntities();
        situacaoList = new SituacaoJpaController().findSituacaoEntities();
        procuradorList = new ProcuradorJpaController().findProcuradorEntities();
    }

    public void adicionarVinculosProcessuais() {
        if (vinculos > vinculoProcessualList.size()) {
            vinculos = vinculoProcessualList.isEmpty() ? vinculos : vinculos - vinculoProcessualList.size();
            for (int i = 0; i < vinculos; i++) {
                vinculoProcessual = new VinculoProcessual();
                vinculoProcessualList.add(vinculoProcessual);
            }
        } else if (vinculos < vinculoProcessualList.size()) {
            while (vinculoProcessualList.size() > vinculos) {
                vinculoProcessualList.remove(vinculoProcessualList.size() - 1);
            }
        }
    }

    public void distribuirCitacoesPorListas(String tipo) {
        if (tipo.equals("AR")) {
            arList = adicionarCitacoes(arList, ars, tipo);
        } else if (tipo.equals("OJ")) {
            oficialList = adicionarCitacoes(oficialList, oficiais, tipo);
        } else if (tipo.equals("ED")) {
            editalList = adicionarCitacoes(editalList, editais, tipo);
        } else if (tipo.equals("ES")) {
            enderecoSocioList = adicionarCitacoes(enderecoSocioList, enderecosSocios, tipo);
        }
    }

    public void adicionarPenhora() {
        if (tipoPenhora != null) {
            Penhora penhora = new Penhora();
            penhora.setTipoPenhoraFk(tipoPenhora);
            if (edit) {
                penhora.setProcessoJudicialFk(processoJudicial);
            }
            penhoraList.add(penhora);
        }
    }

    public void removerPenhora(int index) {
        penhoraList.remove(index);
        checkAquisicoes(null);
    }

    public List<Citacao> adicionarCitacoes(List<Citacao> citacaoList, Integer quantidade, String tipo) {
        if (quantidade > citacaoList.size()) {
            quantidade = citacaoList.isEmpty() ? quantidade : quantidade - citacaoList.size();
            for (int i = 0; i < quantidade; i++) {
                Citacao citacao = new Citacao();
                citacao.setTipoCitacao(tipo);
                citacao.setCitado('N');
                citacaoList.add(citacao);
            }
        } else if (quantidade < citacaoList.size()) {
            while (citacaoList.size() > quantidade) {
                citacaoList.remove(citacaoList.size() - 1);
            }
        }
        return citacaoList;
    }

    public void refreshListaDeBens(Integer index) {
        if (index == null) {
            if (executadoPJ != null) {
                bemList = bemJpaController.findPJBens(Integer.valueOf(executadoPJ));
            } else if (executadoPF != null) {
                bemList = bemJpaController.findPFBens(Integer.valueOf(executadoPF));
            }
            for (Penhora penhora : penhoraList) {
                if (penhora.getSocioTipo() != null) {
                    String tabela = penhora.getSocioTipo().substring(0, 2);
                    Integer id = Integer.valueOf(penhora.getSocioTipo().substring(2));
                    if (tabela.equals("PF")) {
                        penhora.setBemList(bemJpaController.findPFBens(id));
                    } else if (tabela.equals("PJ")) {
                        penhora.setBemList(bemJpaController.findPJBens(id));
                    }
                }
            }
        } else if (index != null) {
            if (penhoraList.get(index).getSocioTipo() != null) {
                String tabela = penhoraList.get(index).getSocioTipo().substring(0, 2);
                Integer id = Integer.valueOf(penhoraList.get(index).getSocioTipo().substring(2));
                if (tabela.equals("PF")) {
                    penhoraList.get(index).setBemList(bemJpaController.findPFBens(id));
                } else if (tabela.equals("PJ")) {
                    penhoraList.get(index).setBemList(bemJpaController.findPJBens(id));
                }
            }
        }
    }

    public void checkAquisicoes(Bem bem) {
        Iterator<List<AquisicaoBem>> it = listOfAquisicaoBemList.iterator();
        boolean add = true;
        while (it.hasNext()) {
            boolean exists = false;
            List<AquisicaoBem> aquisicaoBemList = it.next();
            for (Penhora penhora : penhoraList) {
                if (aquisicaoBemList.get(0).getBemFk().equalsValues(penhora.getBemFk())) {
                    exists = true;
                }
            }
            if (!exists) {
                it.remove();
            }
            if (aquisicaoBemList.get(0).getBemFk().equalsValues(bem)) {
                add = false;
            }
        }
        List<AquisicaoBem> aquisicaoBemList = new ArrayList<>();
        if (bem != null) {
            if (add) {
                int i = 0;
                while (i < 4) {
                    AquisicaoBem aquisicaoBem = new AquisicaoBem();
                    aquisicaoBem.setBemFk(bem);
                    if (edit) {
                        aquisicaoBem.setProcessoJudicialFk(processoJudicial);
                    }
                    if (i == 0) {
                        aquisicaoBem.setTipo("HP");
                    } else if (i == 1) {
                        aquisicaoBem.setTipo("AD");
                    } else if (i == 2) {
                        aquisicaoBem.setTipo("RE");
                    } else {
                        aquisicaoBem.setTipo("LE");
                    }
                    aquisicaoBemList.add(aquisicaoBem);
                    i++;
                }
                listOfAquisicaoBemList.add(aquisicaoBemList);
            }
        }
    }

    public void listarSocios(String tipo) {
        socioList = new ArrayList<>();
        if (!initialLoad) {
            socioRedirecionamentoList = new ArrayList<>();
            oldSocioRedirecionamentoList = new ArrayList<>();
        }
        if (tipo.equals("PJ") && executadoPJ != null) {
            bemList = bemJpaController.findPJBens(Integer.valueOf(executadoPJ));
            List<PessoaFisicaJuridica> pfjList = new PessoaFisicaJuridicaJpaController().findAllByPJ(Integer.valueOf(executadoPJ));
            for (PessoaFisicaJuridica pfj : pfjList) {
                socioList.add(pfj.getPessoaFisicaFk());
                if (!initialLoad) {
                    SocioRedirecionamento socioRedirecionamento = new SocioRedirecionamento((PessoaFisica) pfj.getPessoaFisicaFk(), new Redirecionamento());
                    socioRedirecionamentoList.add(socioRedirecionamento);
                    SocioRedirecionamento oldSocioRedirecionamento = new SocioRedirecionamento((PessoaFisica) pfj.getPessoaFisicaFk(), new Redirecionamento());
                    oldSocioRedirecionamentoList.add(oldSocioRedirecionamento);
                }
            }
            List<PessoaJuridicaJuridica> pjjList = new PessoaJuridicaJuridicaJpaController().findAllByPJAOrPJB(Integer.valueOf(executadoPJ));
            for (PessoaJuridicaJuridica pjj : pjjList) {
                if (pjj.getPessoaJuridicaPrimariaFk().getId().equals(Integer.valueOf(executadoPJ))) {
                    socioList.add(pjj.getPessoaJuridicaSecundariaFk());
                    if (!initialLoad) {
                        SocioRedirecionamento socioRedirecionamento = new SocioRedirecionamento((PessoaJuridica) pjj.getPessoaJuridicaSecundariaFk(), new Redirecionamento());
                        socioRedirecionamentoList.add(socioRedirecionamento);
                        SocioRedirecionamento oldSocioRedirecionamento = new SocioRedirecionamento((PessoaJuridica) pjj.getPessoaJuridicaSecundariaFk(), new Redirecionamento());
                        oldSocioRedirecionamentoList.add(oldSocioRedirecionamento);
                    }
                }
            }
        } else if (tipo.equals("PF") && executadoPF != null) {
            bemList = bemJpaController.findPFBens(Integer.valueOf(executadoPF));
            enderecosSocios = 0;
            enderecoSocioList = new ArrayList<>();
        }
        if (edit) {
            penhoraList = penhoraJpaController.findByPJUD(processoJudicial.getId());
            for (Penhora penhora : penhoraList) {
                if (penhora.getSocio() != null && penhora.getSocioFk() != null) {
                    penhora.setSocioTipo(penhora.getSocio() + penhora.getSocioFk());
                }
            }
        }
        if (!socioList.isEmpty()) {
            tipoPenhoraList = tipoPenhora1JpaController.findTipoPenhoraEntities();
        } else {
            redirecionamento = null;
            tipoPenhoraList = tipoPenhora1JpaController.findPenhorasSemSocio();
            Iterator<Penhora> i = penhoraList.iterator();
            while (i.hasNext()) {
                Penhora penhora = i.next();
                if (penhora.getTipoPenhoraFk().getTipo().contains("Sócio")) {
                    i.remove();
                }
            }
        }
        initialLoad = false;
    }

    public void exibirExecutado() {
        if (processoJudicial.getExecutado().equals("PF")) {
            PessoaFisica pessoaFisica = pessoaFisicaJpaController.findPessoaFisica(Integer.valueOf(executadoPF));
            enderecoPessoaFisica = new EnderecoPessoa(pessoaFisica, enderecoJpaController.findPFAddress(pessoaFisica.getId()), bemJpaController.findPFBens(pessoaFisica.getId()));
            executadoProcessoJudicialList = processoJudicialJpaController.findByExecutado(executadoPF, "PF");
        } else if (processoJudicial.getExecutado().equals("PJ")) {
            PessoaJuridica pessoaJuridica = pessoaJuridicaJpaController.findPessoaJuridica(Integer.valueOf(executadoPJ));
            enderecoPessoaJuridica = new EnderecoPessoa(pessoaJuridica, enderecoJpaController.findPJAddress(pessoaJuridica.getId()), bemJpaController.findPJBens(pessoaJuridica.getId()));
            executadoProcessoJudicialList = processoJudicialJpaController.findByExecutado(executadoPJ, "PJ");
        }
    }

    public void exibirInfo() {
        processoJudicial = processoJudicialJpaController.findProcessoJudicial(Integer.valueOf(pjudId));
        for (VinculoProcessual vp : processoJudicial.getVinculoProcessualCollection()) {
            ProcessoJudicial processoJudicialVinculado = processoJudicialJpaController.findByProcessNumber(vp.getProcesso());
            vp.setProcessoJudicialTransientId((processoJudicialVinculado != null) ? processoJudicialVinculado.getId() : null);
        }
        if (processoJudicial.getExecutado().equals("PF")) {
            PessoaFisica pessoaFisica = pessoaFisicaJpaController.findPessoaFisica(processoJudicial.getExecutadoFk());
            enderecoPessoaFisica = new EnderecoPessoa(pessoaFisica, enderecoJpaController.findPFAddress(pessoaFisica.getId()), bemJpaController.findPFBens(pessoaFisica.getId()));
            executado = new Executado(processoJudicial, enderecoPessoaFisica, citacaoJpaController.findByPJUD(processoJudicial.getId()), carregarSocioRedirecionamento(redirecionamentoJpaController.findByPJUD(processoJudicial.getId())), penhoraJpaController.findByPJUD(processoJudicial.getId()), aquisicaoBemJpaController.findByPJUD(processoJudicial.getId()));
        } else {
            PessoaJuridica pessoaJuridica = pessoaJuridicaJpaController.findPessoaJuridica(processoJudicial.getExecutadoFk());
            enderecoPessoaJuridica = new EnderecoPessoa(pessoaJuridica, enderecoJpaController.findPJAddress(pessoaJuridica.getId()), bemJpaController.findPJBens(pessoaJuridica.getId()));
            executado = new Executado(processoJudicial, enderecoPessoaJuridica, citacaoJpaController.findByPJUD(processoJudicial.getId()), carregarSocioRedirecionamento(redirecionamentoJpaController.findByPJUD(processoJudicial.getId())), penhoraJpaController.findByPJUD(processoJudicial.getId()), aquisicaoBemJpaController.findByPJUD(processoJudicial.getId()));
        }
    }

    public int checkCitacaoVazia(List<Object> citacaoList, String tipo) {
        int quantidade = 0;
        for (Object citacao : citacaoList) {
            if (citacao instanceof Citacao) {
                Citacao c = (Citacao) citacao;
                quantidade = c.getTipoCitacao().equals(tipo) ? quantidade + 1 : quantidade;
            } else {
                CitacaoHistorico c = (CitacaoHistorico) citacao;
                quantidade = c.getTipoCitacao().equals(tipo) ? quantidade + 1 : quantidade;
            }
        }
        return quantidade;
    }

    public List<SocioRedirecionamento> carregarSocioRedirecionamento(List<Redirecionamento> redirecionamentoList) {
        redirecionamento = null;
        List<SocioRedirecionamento> socioRedirecionamentoList = new ArrayList<>();
        for (Redirecionamento redirecionamento : redirecionamentoList) {
            this.redirecionamento = redirecionamento.getRedirecionado();
            if (redirecionamento.getSocio().equals("PF")) {
                socioRedirecionamentoList.add(new SocioRedirecionamento(pessoaFisicaJpaController.findPessoaFisica(redirecionamento.getSocioFk()), redirecionamento));
            } else {
                socioRedirecionamentoList.add(new SocioRedirecionamento(pessoaJuridicaJpaController.findPessoaJuridica(redirecionamento.getSocioFk()), redirecionamento));
            }
        }
        return socioRedirecionamentoList;
    }

    public List<SocioRedirecionamentoHistorico> carregarSocioRedirecionamentoHistorico(List<RedirecionamentoHistorico> redirecionamentoHistoricoList) {
        List<SocioRedirecionamentoHistorico> socioRedirecionamentoHistoricoList = new ArrayList<>();
        for (RedirecionamentoHistorico redirecionamentoHistorico : redirecionamentoHistoricoList) {
            if (redirecionamentoHistorico.getSocio().equals("PF")) {
                socioRedirecionamentoHistoricoList.add(new SocioRedirecionamentoHistorico(pessoaFisicaJpaController.findPessoaFisica(redirecionamentoHistorico.getSocioFk()), redirecionamentoHistorico));
            } else {
                socioRedirecionamentoHistoricoList.add(new SocioRedirecionamentoHistorico(pessoaJuridicaJpaController.findPessoaJuridica(redirecionamentoHistorico.getSocioFk()), redirecionamentoHistorico));
            }
        }
        return socioRedirecionamentoHistoricoList;
    }

    public void removerProcessoJudicial() throws Exception {
        processoJudicial = processoJudicialJpaController.findProcessoJudicial(Integer.valueOf(pjudId));
        processoJudicial.setStatus('I');
        processoJudicialJpaController.edit(processoJudicial);
        GeradorLog.criar(processoJudicial.getId(), "PJUD", 'D');
        redirect = "";
        register = "success";
    }

    public void evitarAvisosIndevidosNoForm() throws IOException {
        register = "none";
        FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrar.xhtml");
    }

    public void cadastrar() throws IOException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
        boolean error = false;
        ProcessoJudicial pjudDBCDA = processoJudicialJpaController.findByCDA(processoJudicial);
        ProcessoJudicial pjudDBProcess = processoJudicialJpaController.findByProcessNumber(processoJudicial.getNumeroDoProcesso());
        processoJudicial.setExecutadoFk(executadoPF != null ? Integer.valueOf(executadoPF) : Integer.valueOf(executadoPJ));
        List<Citacao> citacaoList = new ArrayList<>();
        citacaoList.addAll(arList);
        citacaoList.addAll(oficialList);
        citacaoList.addAll(editalList);
        citacaoList.addAll(enderecoSocioList);
        if (!edit) {
            /*  
             Cadastrar novo Processo Judicial
             */
            if (pjudDBCDA == null && pjudDBProcess == null) { // Processo novo
                processoJudicial.setUsuarioFk(usuarioJpaController.findUsuarioByCPF(Cookie.getCookie("usuario")));
                processoJudicial.setInstituicaoFk(new AutorizacaoJpaController().findAutorizacaoByCPF(Cookie.getCookie("usuario")).getInstituicaoFk());
                processoJudicial.setStatus('A');
                if (processoJudicial.getValorAtualizado() == null && processoJudicial.getValorDaCausa() != null) {
                    processoJudicial.setValorAtualizado(processoJudicial.getValorDaCausa());
                }
                processoJudicialJpaController.create(processoJudicial);
                for (VinculoProcessual vinculoProcessual : vinculoProcessualList) {
                    vinculoProcessual.setProcessoJudicialFk(processoJudicial);
                    vinculoProcessualJpaController.create(vinculoProcessual);
                }
                for (Citacao c : citacaoList) {
                    if (c.getSocioTipo() != null) {
                        c.setSocio(c.getSocioTipo().substring(0, 2));
                        c.setSocioFk(Integer.valueOf(c.getSocioTipo().substring(2)));
                    }
                    c.setProcessoJudicialFk(processoJudicial);
                    citacaoJpaController.create(c);
                }
                for (SocioRedirecionamento sr : socioRedirecionamentoList) {
                    if (redirecionamento != null) {
                        sr.getRedirecionamento().setProcessoJudicialFk(processoJudicial);
                        sr.getRedirecionamento().setRedirecionado(redirecionamento);
                        if (sr.getPessoa() instanceof PessoaFisica) {
                            PessoaFisica pessoaFisica = (PessoaFisica) sr.getPessoa();
                            sr.getRedirecionamento().setSocioFk(pessoaFisica.getId());
                            sr.getRedirecionamento().setSocio("PF");
                        } else {
                            PessoaJuridica pessoaJuridica = (PessoaJuridica) sr.getPessoa();
                            sr.getRedirecionamento().setSocioFk(pessoaJuridica.getId());
                            sr.getRedirecionamento().setSocio("PJ");
                        }
                        redirecionamentoJpaController.create(sr.getRedirecionamento());
                    }
                }
                for (Penhora penhora : penhoraList) {
                    if (penhora.getSocioTipo() != null) {
                        penhora.setSocioFk(Integer.valueOf(penhora.getSocioTipo().substring(2)));
                        penhora.setSocio(penhora.getSocioTipo().substring(0, 2));
                    }
                    penhora.setProcessoJudicialFk(processoJudicial);
                    penhoraJpaController.create(penhora);
                }
                for (List<AquisicaoBem> aquisicaoBemList : listOfAquisicaoBemList) {
                    for (AquisicaoBem aquisicaoBem : aquisicaoBemList) {
                        aquisicaoBem.setProcessoJudicialFk(processoJudicial);
                        aquisicaoBemJpaController.create(aquisicaoBem);
                    }
                }
                register = "success";
                GeradorLog.criar(processoJudicial.getId(), "PJUD", 'C');
                processoJudicial = new ProcessoJudicial();
                vinculoProcessual = new VinculoProcessual();
                vinculos = 0;
                socioRedirecionamentoList = new ArrayList<>();
                socioList = new ArrayList<>();
                arList = new ArrayList<>();
                oficialList = new ArrayList<>();
                editalList = new ArrayList<>();
                enderecoSocioList = new ArrayList<>();
                listOfAquisicaoBemList = new ArrayList<>();
                penhoraList = new ArrayList<>();
                tipoPenhora = new TipoPenhora();
                ars = 0;
                oficiais = 0;
                editais = 0;
                enderecosSocios = 0;
                executadoPF = "";
                executadoPJ = "";
                redirecionamento = null;
            } else { // CDA ou Processo já cadastrado
                error = true;
            }
        } else {
            /*  
             Alterar Pessoa Física existente
             */
            if ((pjudDBCDA == null || processoJudicial.equals(pjudDBCDA)) && (pjudDBProcess == null || processoJudicial.equals(pjudDBProcess))) {
                if (redirecionamento != null) {
                    for (SocioRedirecionamento sr : socioRedirecionamentoList) {
                        sr.getRedirecionamento().setRedirecionado(redirecionamento);
                    }
                }
                List<AquisicaoBem> aquisicaoBemListTemp = new ArrayList<>();
                for (List<AquisicaoBem> aquisicaoBemList : listOfAquisicaoBemList) {
                    aquisicaoBemListTemp.addAll(aquisicaoBemList);
                }
                boolean identicalVP = MetodosConvencionais.checarListasIguais("VinculoProcessual", vinculoProcessualList, (List<VinculoProcessual>) oldProcessoJudicial.getVinculoProcessualCollection());
                boolean identicalRedirecionamento = MetodosConvencionais.checarListasIguais("SocioRedirecionamento", socioRedirecionamentoList, oldSocioRedirecionamentoList);
                boolean identicalCitacao = MetodosConvencionais.checarListasIguais("Citacao", citacaoList, oldCitacaoList);
                boolean identicalPenhora = MetodosConvencionais.checarListasIguais("Penhora", penhoraList, oldPenhoraList);
                boolean identicalAquisicao = MetodosConvencionais.checarListasIguais("AquisicaoBem", aquisicaoBemListTemp, oldAquisicaoBemList);
                if (oldProcessoJudicial.equalsValues(processoJudicial)
                        && identicalVP && identicalCitacao && identicalRedirecionamento && identicalPenhora && identicalAquisicao) {
                    Cookie.addCookie("FacesMessage", "fail", 10);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("consultar.xhtml");
                } else {
                    Timestamp timestamp = new UtilJpaController().findServerTime();
                    processoJudicial.setUsuarioFk(usuarioJpaController.findUsuarioByCPF(Cookie.getCookie("usuario")));
                    if (processoJudicial.getValorAtualizado() == null && processoJudicial.getValorDaCausa() != null) {
                        processoJudicial.setValorAtualizado(processoJudicial.getValorDaCausa());
                    }
                    processoJudicialJpaController.edit(processoJudicial);
                    processoJudicialHistorico.setDataDeModificacao(timestamp);
                    processoJudicialHistoricoJpaController.create(processoJudicialHistorico);
                    vinculoProcessualJpaController.destroyByPJUD(processoJudicial.getId());
                    for (VinculoProcessual vinculoProcessual : vinculoProcessualList) {
                        vinculoProcessual.setProcessoJudicialFk(processoJudicial);
                        vinculoProcessualJpaController.create(vinculoProcessual);
                    }
                    citacaoJpaController.destroyByPJUD(processoJudicial.getId());
                    for (Citacao citacao : citacaoList) {
                        if (citacao.getSocioTipo() != null) {
                            citacao.setSocio(citacao.getSocioTipo().substring(0, 2));
                            citacao.setSocioFk(Integer.valueOf(citacao.getSocioTipo().substring(2)));
                        }
                        citacao.setProcessoJudicialFk(processoJudicial);
                        citacaoJpaController.create(citacao);
                    }
                    redirecionamentoJpaController.destroyByPJUD(processoJudicial.getId());
                    for (SocioRedirecionamento sr : socioRedirecionamentoList) {
                        if (redirecionamento != null) {
                            sr.getRedirecionamento().setProcessoJudicialFk(processoJudicial);
                            if (sr.getPessoa() instanceof PessoaFisica) {
                                PessoaFisica pessoaFisica = (PessoaFisica) sr.getPessoa();
                                sr.getRedirecionamento().setSocioFk(pessoaFisica.getId());
                                sr.getRedirecionamento().setSocio("PF");
                            } else {
                                PessoaJuridica pessoaJuridica = (PessoaJuridica) sr.getPessoa();
                                sr.getRedirecionamento().setSocioFk(pessoaJuridica.getId());
                                sr.getRedirecionamento().setSocio("PJ");
                            }
                            redirecionamentoJpaController.create(sr.getRedirecionamento());
                        }
                    }
                    penhoraJpaController.destroyByPJUD(processoJudicial.getId());
                    for (Penhora penhora : penhoraList) {
                        penhoraJpaController.create(penhora);
                    }
                    aquisicaoBemJpaController.destroyByPJUD(processoJudicial.getId());
                    for (AquisicaoBem aquisicaoBem : aquisicaoBemListTemp) {
                        aquisicaoBemJpaController.create(aquisicaoBem);
                    }

                    for (AquisicaoBemHistorico abh : aquisicaoBemHistoricoList) {
                        abh.setProcessoJudicialHistoricoFk(processoJudicialHistorico);
                        aquisicaoBemHistoricoJpaController.create(abh);
                    }
                    for (VinculoProcessualHistorico vph : vinculoProcessualHistoricoList) {
                        vph.setProcessoJudicialHistoricoFk(processoJudicialHistorico);
                        new VinculoProcessualHistoricoJpaController().create(vph);
                    }
                    for (CitacaoHistorico ch : citacaoHistoricoList) {
                        ch.setProcessoJudicialHistoricoFk(processoJudicialHistorico);
                        citacaoHistoricoJpaController.create(ch);
                    }
                    for (RedirecionamentoHistorico rh : redirecionamentoHistoricoList) {
                        rh.setProcessoJudicialHistoricoFk(processoJudicialHistorico);
                        redirecionamentoHistoricoJpaController.create(rh);
                    }
                    for (PenhoraHistorico ph : penhoraHistoricoList) {
                        ph.setProcessoJudicialHistoricoFk(processoJudicialHistorico);
                        penhoraHistoricoJpaController.create(ph);
                    }
                    GeradorLog.criar(processoJudicial.getId(), "PJUD", 'U');
                    Cookie.addCookie("FacesMessage", "success", 10);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("consultar.xhtml");
                }
            } else { // CDA ou Processo já cadastrado
                error = true;
            }
        }
        if (error) { // Exibição dinâmica de erros
            register = "fail";
            String message = "";
            if ((pjudDBProcess != null && pjudDBCDA == null) || (edit && processoJudicial.equals(pjudDBCDA))) {
                message += "Já existe um Processo Judicial cadastrado com o número " + pjudDBProcess.getNumeroDoProcesso();
                message += "\nNº da CDA: " + pjudDBProcess.getNumeroDaCda();
                message += prepararMensagemDeErro(pjudDBProcess);
            } else if ((pjudDBCDA != null && pjudDBProcess == null) || (edit && processoJudicial.equals(pjudDBProcess))) {
                message += "Já existe um Processo Judicial cadastrado com CDA de número " + pjudDBCDA.getNumeroDaCda();
                message += "\nNº do Processo: " + pjudDBCDA.getNumeroDoProcesso();
                message += prepararMensagemDeErro(pjudDBCDA);
            } else if (pjudDBCDA.equals(pjudDBProcess)) {
                message += "Já existe um Processo Judicial cadastrado com o número " + pjudDBProcess.getNumeroDoProcesso() + " e CDA de número " + pjudDBProcess.getNumeroDaCda();
                message += prepararMensagemDeErro(pjudDBProcess);
            } else {
                message += "Já existem Processos Judiciais cadastrados com número " + processoJudicial.getNumeroDoProcesso() + " e CDA de número " + processoJudicial.getNumeroDaCda();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    private String prepararMensagemDeErro(ProcessoJudicial processoJudicial) {
        String mensagem = "\nComarca: " + processoJudicial.getComarca();
        if (processoJudicial.getExecutado().equals("PF")) {
            PessoaFisica pf = pessoaFisicaJpaController.findPessoaFisica(processoJudicial.getExecutadoFk());
            mensagem += "\nExecutado: " + pf.getNome();
            mensagem += "\nCPF: " + (pf.getCpf() != null ? pf.getCpf().substring(0, 3) + "." + pf.getCpf().substring(3, 6) + "." + pf.getCpf().substring(6, 9) + "-" + pf.getCpf().substring(9) : "-");
        } else {
            PessoaJuridica pj = pessoaJuridicaJpaController.findPessoaJuridica(processoJudicial.getExecutadoFk());
            mensagem += "\nExecutado: " + pj.getNome();
            mensagem += "\nCNPJ: " + pj.getCnpj().substring(0, 3) + "." + pj.getCnpj().substring(3, 6) + "." + pj.getCnpj().substring(6, 9) + "/" + pj.getCnpj().substring(9, 13) + "-" + pj.getCnpj().substring(13);
        }
        return mensagem;
    }

    public void prepararHistorico(ProcessoJudicial processoJudicial, List<Citacao> citacaoList, List<SocioRedirecionamento> socioRedirecionamentoList, List<Penhora> penhoraList, List<AquisicaoBem> aquisicaoBemList) {
        /*
         Montar com.ppcc.app.model.entitys dos históricos de alteração 
         */
        processoJudicialHistorico = new ProcessoJudicialHistorico();
        vinculoProcessualHistoricoList = new ArrayList<>();
        citacaoHistoricoList = new ArrayList<>();
        redirecionamentoHistoricoList = new ArrayList<>();
        penhoraHistoricoList = new ArrayList<>();
        aquisicaoBemHistoricoList = new ArrayList<>();

        processoJudicialHistorico.setComarca(processoJudicial.getComarca());
        processoJudicialHistorico.setDataDeInscricao(processoJudicial.getDataDeInscricao());
        processoJudicialHistorico.setDespachoInicial(processoJudicial.getDespachoInicial());
        processoJudicialHistorico.setDespachoInicialDataDoAto(processoJudicial.getDespachoInicialDataDoAto());
        processoJudicialHistorico.setDiscriminacaoDoCreditoImposto(processoJudicial.getDiscriminacaoDoCreditoImposto());
        processoJudicialHistorico.setDiscriminacaoDoCreditoMulta(processoJudicial.getDiscriminacaoDoCreditoMulta());
        processoJudicialHistorico.setDistribuicao(processoJudicial.getDistribuicao());
        processoJudicialHistorico.setDistribuicaoDataDoAto(processoJudicial.getDistribuicaoDataDoAto());
        processoJudicialHistorico.setExecutado(processoJudicial.getExecutado());
        processoJudicialHistorico.setExecutadoFk(processoJudicial.getExecutadoFk());
        processoJudicialHistorico.setFatosGeradores(processoJudicial.getFatosGeradores());
        processoJudicialHistorico.setFonteDaArrecadacao(processoJudicial.getFonteDaArrecadacao());
        processoJudicialHistorico.setFundamentacao(processoJudicial.getFundamentacao());
        processoJudicialHistorico.setGrupoDeEspecializacao(processoJudicial.getGrupoDeEspecializacao());
        processoJudicialHistorico.setNotificacaoAdministrativa(processoJudicial.getNotificacaoAdministrativa());
        processoJudicialHistorico.setNotificacaoAdministrativaDataDoAto(processoJudicial.getNotificacaoAdministrativaDataDoAto());
        processoJudicialHistorico.setNumeroDaCda(processoJudicial.getNumeroDaCda());
        processoJudicialHistorico.setNumeroDoProcesso(processoJudicial.getNumeroDoProcesso());
        processoJudicialHistorico.setNumeroDoProcessoAnterior(processoJudicial.getNumeroDoProcessoAnterior());
        processoJudicialHistorico.setOutrasInformacoesAtoProcessual(processoJudicial.getOutrasInformacoesAtoProcessual());
        processoJudicialHistorico.setOutrasInformacoesExecutado(processoJudicial.getOutrasInformacoesExecutado());
        processoJudicialHistorico.setOutrasInformacoesProcesso(processoJudicial.getOutrasInformacoesProcesso());
        processoJudicialHistorico.setProcessoJudicialFk(processoJudicial);
        processoJudicialHistorico.setProcuradorFk(processoJudicial.getProcuradorFk());
        processoJudicialHistorico.setRecurso(processoJudicial.getRecurso());
        processoJudicialHistorico.setSituacaoFk(processoJudicial.getSituacaoFk());
        processoJudicialHistorico.setTipoDeRecursoFk(processoJudicial.getTipoDeRecursoFk());
        processoJudicialHistorico.setUsuarioFk(processoJudicial.getUsuarioFk());
        processoJudicialHistorico.setValorArrecadado(processoJudicial.getValorArrecadado());
        processoJudicialHistorico.setValorAtualizado(processoJudicial.getValorAtualizado());
        processoJudicialHistorico.setValorDaCausa(processoJudicial.getValorDaCausa());
        processoJudicialHistorico.setVara(processoJudicial.getVara());
        processoJudicialHistorico.setVaraAnterior(processoJudicial.getVaraAnterior());

        for (VinculoProcessual vinculoProcessual : (List<VinculoProcessual>) processoJudicial.getVinculoProcessualCollection()) {
            VinculoProcessualHistorico vinculoProcessualHistorico = new VinculoProcessualHistorico();
            vinculoProcessualHistorico.setProcesso(vinculoProcessual.getProcesso());
            vinculoProcessualHistorico.setTipoDeProcessoFk(vinculoProcessual.getTipoDeProcessoFk());
            vinculoProcessualHistoricoList.add(vinculoProcessualHistorico);
        }

        for (Citacao citacao : citacaoList) {
            CitacaoHistorico ch = new CitacaoHistorico();
            ch.setCitado(citacao.getCitado());
            ch.setDataDaCitacao(citacao.getDataDaCitacao());
            ch.setEndereco(citacao.getEndereco());
            ch.setMotivo(citacao.getMotivo());
            ch.setSocio(citacao.getSocio());
            ch.setSocioFk(citacao.getSocioFk());
            ch.setTipoCitacao(citacao.getTipoCitacao());
            citacaoHistoricoList.add(ch);
        }

        for (SocioRedirecionamento socioRedirecionamento : socioRedirecionamentoList) {
            RedirecionamentoHistorico rh = new RedirecionamentoHistorico();
            rh.setDataDeRedirecionamento(socioRedirecionamento.getRedirecionamento().getDataDeRedirecionamento());
            rh.setRedirecionado(socioRedirecionamento.getRedirecionamento().getRedirecionado());
            rh.setSocio(socioRedirecionamento.getRedirecionamento().getSocio());
            rh.setSocioFk(socioRedirecionamento.getRedirecionamento().getSocioFk());
            redirecionamentoHistoricoList.add(rh);
        }

        for (Penhora penhora : penhoraList) {
            PenhoraHistorico ph = new PenhoraHistorico();
            ph.setBemFk(penhora.getBemFk());
            ph.setDataDaPenhora(penhora.getDataDaPenhora());
            ph.setEndereco(penhora.getEndereco());
            ph.setSituacao(penhora.getSituacao());
            ph.setSocio(penhora.getSocio());
            ph.setSocioFk(penhora.getSocioFk());
            ph.setMotivo(penhora.getMotivo());
            ph.setTipoPenhoraFk(penhora.getTipoPenhoraFk());
            ph.setValor(penhora.getValor());
            penhoraHistoricoList.add(ph);
        }

        for (AquisicaoBem aquisicaoBem : aquisicaoBemList) {
            AquisicaoBemHistorico abh = new AquisicaoBemHistorico();
            abh.setBemFk(aquisicaoBem.getBemFk());
            abh.setDataDaAquisicao(aquisicaoBem.getDataDaAquisicao());
            abh.setExito(aquisicaoBem.getExito());
            abh.setMotivo(aquisicaoBem.getMotivo());
            abh.setTipo(aquisicaoBem.getTipo());
            abh.setValor(aquisicaoBem.getValor());
            aquisicaoBemHistoricoList.add(abh);
        }
    }

    private ExecutadoHistorico prepararRegistroAtual(ProcessoJudicial processoJudicial, EnderecoPessoa enderecoPessoa, List<Citacao> citacaoList, List<SocioRedirecionamento> socioRedirecionamentoList, List<Penhora> penhoraList, List<AquisicaoBem> aquisicaoBemList) {
        /*
         Montar registro atual como uma com.ppcc.app.model.entity de histórico para facilitar o ui:repeat do form
         */
        ExecutadoHistorico executadoHistorico = new ExecutadoHistorico();
        processoJudicialHistorico = new ProcessoJudicialHistorico();
        vinculoProcessualHistoricoList = new ArrayList<>();
        citacaoHistoricoList = new ArrayList<>();
        List<SocioRedirecionamentoHistorico> socioRedirecionamentoHistoricoList = new ArrayList<>();
        penhoraHistoricoList = new ArrayList<>();
        aquisicaoBemHistoricoList = new ArrayList<>();

        processoJudicialHistorico.setComarca(processoJudicial.getComarca());
        processoJudicialHistorico.setDataDeInscricao(processoJudicial.getDataDeInscricao());
        processoJudicialHistorico.setDespachoInicial(processoJudicial.getDespachoInicial());
        processoJudicialHistorico.setDespachoInicialDataDoAto(processoJudicial.getDespachoInicialDataDoAto());
        processoJudicialHistorico.setDiscriminacaoDoCreditoImposto(processoJudicial.getDiscriminacaoDoCreditoImposto());
        processoJudicialHistorico.setDiscriminacaoDoCreditoMulta(processoJudicial.getDiscriminacaoDoCreditoMulta());
        processoJudicialHistorico.setDistribuicao(processoJudicial.getDistribuicao());
        processoJudicialHistorico.setDistribuicaoDataDoAto(processoJudicial.getDistribuicaoDataDoAto());
        processoJudicialHistorico.setExecutado(processoJudicial.getExecutado());
        processoJudicialHistorico.setExecutadoFk(processoJudicial.getExecutadoFk());
        processoJudicialHistorico.setFatosGeradores(processoJudicial.getFatosGeradores());
        processoJudicialHistorico.setFonteDaArrecadacao(processoJudicial.getFonteDaArrecadacao());
        processoJudicialHistorico.setFundamentacao(processoJudicial.getFundamentacao());
        processoJudicialHistorico.setGrupoDeEspecializacao(processoJudicial.getGrupoDeEspecializacao());
        processoJudicialHistorico.setNotificacaoAdministrativa(processoJudicial.getNotificacaoAdministrativa());
        processoJudicialHistorico.setNotificacaoAdministrativaDataDoAto(processoJudicial.getNotificacaoAdministrativaDataDoAto());
        processoJudicialHistorico.setNumeroDaCda(processoJudicial.getNumeroDaCda());
        processoJudicialHistorico.setNumeroDoProcesso(processoJudicial.getNumeroDoProcesso());
        processoJudicialHistorico.setNumeroDoProcessoAnterior(processoJudicial.getNumeroDoProcessoAnterior());
        processoJudicialHistorico.setOutrasInformacoesAtoProcessual(processoJudicial.getOutrasInformacoesAtoProcessual());
        processoJudicialHistorico.setOutrasInformacoesExecutado(processoJudicial.getOutrasInformacoesExecutado());
        processoJudicialHistorico.setOutrasInformacoesProcesso(processoJudicial.getOutrasInformacoesProcesso());
        processoJudicialHistorico.setProcessoJudicialFk(processoJudicial);
        processoJudicialHistorico.setProcuradorFk(processoJudicial.getProcuradorFk());
        processoJudicialHistorico.setRecurso(processoJudicial.getRecurso());
        processoJudicialHistorico.setSituacaoFk(processoJudicial.getSituacaoFk());
        processoJudicialHistorico.setTipoDeRecursoFk(processoJudicial.getTipoDeRecursoFk());
        processoJudicialHistorico.setUsuarioFk(processoJudicial.getUsuarioFk());
        processoJudicialHistorico.setValorArrecadado(processoJudicial.getValorArrecadado());
        processoJudicialHistorico.setValorAtualizado(processoJudicial.getValorAtualizado());
        processoJudicialHistorico.setValorDaCausa(processoJudicial.getValorDaCausa());
        processoJudicialHistorico.setVara(processoJudicial.getVara());
        processoJudicialHistorico.setVaraAnterior(processoJudicial.getVaraAnterior());

        for (VinculoProcessual vinculoProcessual : (List<VinculoProcessual>) processoJudicial.getVinculoProcessualCollection()) {
            VinculoProcessualHistorico vinculoProcessualHistorico = new VinculoProcessualHistorico();
            vinculoProcessualHistorico.setProcesso(vinculoProcessual.getProcesso());
            vinculoProcessualHistorico.setTipoDeProcessoFk(vinculoProcessual.getTipoDeProcessoFk());
            vinculoProcessualHistoricoList.add(vinculoProcessualHistorico);
        }
        processoJudicialHistorico.setVinculoProcessualHistoricoCollection(vinculoProcessualHistoricoList);

        for (Citacao citacao : citacaoList) {
            CitacaoHistorico ch = new CitacaoHistorico();
            ch.setCitado(citacao.getCitado());
            ch.setDataDaCitacao(citacao.getDataDaCitacao());
            ch.setEndereco(citacao.getEndereco());
            ch.setMotivo(citacao.getMotivo());
            ch.setSocio(citacao.getSocio());
            ch.setSocioFk(citacao.getSocioFk());
            ch.setTipoCitacao(citacao.getTipoCitacao());
            citacaoHistoricoList.add(ch);
        }

        for (SocioRedirecionamento socioRedirecionamento : socioRedirecionamentoList) {
            RedirecionamentoHistorico rh = new RedirecionamentoHistorico();
            rh.setDataDeRedirecionamento(socioRedirecionamento.getRedirecionamento().getDataDeRedirecionamento());
            rh.setRedirecionado(socioRedirecionamento.getRedirecionamento().getRedirecionado());
            rh.setSocio(socioRedirecionamento.getRedirecionamento().getSocio());
            rh.setSocioFk(socioRedirecionamento.getRedirecionamento().getSocioFk());
            socioRedirecionamentoHistoricoList.add(new SocioRedirecionamentoHistorico(socioRedirecionamento.getPessoa(), rh));
        }

        for (Penhora penhora : penhoraList) {
            PenhoraHistorico ph = new PenhoraHistorico();
            ph.setBemFk(penhora.getBemFk());
            ph.setDataDaPenhora(penhora.getDataDaPenhora());
            ph.setEndereco(penhora.getEndereco());
            ph.setSituacao(penhora.getSituacao());
            ph.setSocio(penhora.getSocio());
            ph.setSocioFk(penhora.getSocioFk());
            ph.setMotivo(penhora.getMotivo());
            ph.setTipoPenhoraFk(penhora.getTipoPenhoraFk());
            ph.setValor(penhora.getValor());
            penhoraHistoricoList.add(ph);
        }

        for (AquisicaoBem aquisicaoBem : aquisicaoBemList) {
            AquisicaoBemHistorico abh = new AquisicaoBemHistorico();
            abh.setBemFk(aquisicaoBem.getBemFk());
            abh.setDataDaAquisicao(aquisicaoBem.getDataDaAquisicao());
            abh.setExito(aquisicaoBem.getExito());
            abh.setMotivo(aquisicaoBem.getMotivo());
            abh.setTipo(aquisicaoBem.getTipo());
            abh.setValor(aquisicaoBem.getValor());
            aquisicaoBemHistoricoList.add(abh);
        }

        executadoHistorico.setProcessoJudicialHistorico(processoJudicialHistorico);
        if (processoJudicial.getExecutado().equals("PF")) {
            executadoHistorico.setEnderecoPessoaFisica(enderecoPessoa);
        } else {
            executadoHistorico.setEnderecoPessoaJuridica(enderecoPessoa);
        }
        listOfAquisicaoBemHistoricoList = new ArrayList<>();
        int i = 0;
        List<AquisicaoBemHistorico> aquisicaoBemHistoricoListTemp = new ArrayList<>();
        while (i < aquisicaoBemHistoricoList.size()) {
            aquisicaoBemHistoricoListTemp.add(aquisicaoBemHistoricoList.get(i));
            if ((i + 1) % 4 == 0) {
                listOfAquisicaoBemHistoricoList.add(aquisicaoBemHistoricoListTemp);
                aquisicaoBemHistoricoListTemp = new ArrayList<>();
            }
            i++;
        }
        executadoHistorico.setListOfAquisicaoBemHistoricoList(listOfAquisicaoBemHistoricoList);
        executadoHistorico.setPenhoraHistoricoList(penhoraHistoricoList);
        executadoHistorico.setCitacaoHistoricoList(citacaoHistoricoList);
        executadoHistorico.setSocioRedirecionamentoHistoricoList(socioRedirecionamentoHistoricoList);
        return executadoHistorico;
    }

    public ProcessoJudicial getProcessoJudicial() {
        return processoJudicial;
    }

    public void setProcessoJudicial(ProcessoJudicial processoJudicial) {
        this.processoJudicial = processoJudicial;
    }

    public Executado getExecutado() {
        return executado;
    }

    public void setExecutado(Executado executado) {
        this.executado = executado;
    }

    public List<VinculoProcessual> getVinculoProcessualList() {
        return vinculoProcessualList;
    }

    public void setVinculoProcessualList(List<VinculoProcessual> vinculoProcessualList) {
        this.vinculoProcessualList = vinculoProcessualList;
    }

    public List<TipoRecurso> getTipoDeRecursoList() {
        return tipoDeRecursoList;
    }

    public void setTipoDeRecursoList(List<TipoRecurso> tipoDeRecursoList) {
        this.tipoDeRecursoList = tipoDeRecursoList;
    }

    public Integer getVinculos() {
        return vinculos;
    }

    public void setVinculos(Integer vinculos) {
        this.vinculos = vinculos;
    }

    public List<TipoProcesso> getTipoDoProcessoList() {
        return tipoDoProcessoList;
    }

    public void setTipoDoProcessoList(List<TipoProcesso> tipoDoProcessoList) {
        this.tipoDoProcessoList = tipoDoProcessoList;
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

    public EnderecoPessoa getEnderecoPessoaModalFisica() {
        return enderecoPessoaModalFisica;
    }

    public void setEnderecoPessoaModalFisica(EnderecoPessoa enderecoPessoaModalFisica) {
        this.enderecoPessoaModalFisica = enderecoPessoaModalFisica;
    }

    public EnderecoPessoa getEnderecoPessoaModalJuridica() {
        return enderecoPessoaModalJuridica;
    }

    public void setEnderecoPessoaModalJuridica(EnderecoPessoa enderecoPessoaModalJuridica) {
        this.enderecoPessoaModalJuridica = enderecoPessoaModalJuridica;
    }

    public String getExecutadoPF() {
        return executadoPF;
    }

    public void setExecutadoPF(String executadoPF) {
        this.executadoPF = executadoPF;
    }

    public String getExecutadoPJ() {
        return executadoPJ;
    }

    public void setExecutadoPJ(String executadoPJ) {
        this.executadoPJ = executadoPJ;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getPjudId() {
        return pjudId;
    }

    public void setPjudId(String pjudId) {
        this.pjudId = pjudId;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public List<ExecutadoHistorico> getExecutadoHistoricoList() {
        return executadoHistoricoList;
    }

    public void setExecutadoHistoricoList(List<ExecutadoHistorico> executadoHistoricoList) {
        this.executadoHistoricoList = executadoHistoricoList;
    }

    public List<Situacao> getSituacaoList() {
        return situacaoList;
    }

    public void setSituacaoList(List<Situacao> situacaoList) {
        this.situacaoList = situacaoList;
    }

    public List<Procurador> getProcuradorList() {
        return procuradorList;
    }

    public void setProcuradorList(List<Procurador> procuradorList) {
        this.procuradorList = procuradorList;
    }

    public List<ProcessoJudicial> getExecutadoProcessoJudicialList() {
        return executadoProcessoJudicialList;
    }

    public void setExecutadoProcessoJudicialList(List<ProcessoJudicial> executadoProcessoJudicialList) {
        this.executadoProcessoJudicialList = executadoProcessoJudicialList;
    }

    public List<Citacao> getArList() {
        return arList;
    }

    public void setArList(List<Citacao> arList) {
        this.arList = arList;
    }

    public List<Citacao> getOficialList() {
        return oficialList;
    }

    public void setOficialList(List<Citacao> oficialList) {
        this.oficialList = oficialList;
    }

    public List<Citacao> getEditalList() {
        return editalList;
    }

    public void setEditalList(List<Citacao> editalList) {
        this.editalList = editalList;
    }

    public List<Citacao> getEnderecoSocioList() {
        return enderecoSocioList;
    }

    public void setEnderecoSocioList(List<Citacao> enderecoSocioList) {
        this.enderecoSocioList = enderecoSocioList;
    }

    public Integer getArs() {
        return ars;
    }

    public void setArs(Integer ars) {
        this.ars = ars;
    }

    public Integer getOficiais() {
        return oficiais;
    }

    public void setOficiais(Integer oficiais) {
        this.oficiais = oficiais;
    }

    public Integer getEditais() {
        return editais;
    }

    public void setEditais(Integer editais) {
        this.editais = editais;
    }

    public Integer getEnderecosSocios() {
        return enderecosSocios;
    }

    public void setEnderecosSocios(Integer enderecosSocios) {
        this.enderecosSocios = enderecosSocios;
    }

    public Character getRedirecionamento() {
        return redirecionamento;
    }

    public void setRedirecionamento(Character redirecionamento) {
        this.redirecionamento = redirecionamento;
    }

    public List<SocioRedirecionamento> getSocioRedirecionamentoList() {
        return socioRedirecionamentoList;
    }

    public void setSocioRedirecionamentoList(List<SocioRedirecionamento> socioRedirecionamentoList) {
        this.socioRedirecionamentoList = socioRedirecionamentoList;
    }

    public List<TipoPenhora> getTipoPenhoraList() {
        return tipoPenhoraList;
    }

    public void setTipoPenhoraList(List<TipoPenhora> tipoPenhoraList) {
        this.tipoPenhoraList = tipoPenhoraList;
    }

    public TipoPenhora getTipoPenhora() {
        return tipoPenhora;
    }

    public void setTipoPenhora(TipoPenhora tipoPenhora) {
        this.tipoPenhora = tipoPenhora;
    }

    public List<Object> getSocioList() {
        return socioList;
    }

    public void setSocioList(List<Object> socioList) {
        this.socioList = socioList;
    }

    public List<Penhora> getPenhoraList() {
        return penhoraList;
    }

    public void setPenhoraList(List<Penhora> penhoraList) {
        this.penhoraList = penhoraList;
    }

    public List<Bem> getBemList() {
        return bemList;
    }

    public void setBemList(List<Bem> bemList) {
        this.bemList = bemList;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public List<List<AquisicaoBem>> getListOfAquisicaoBemList() {
        return listOfAquisicaoBemList;
    }

    public void setListOfAquisicaoBemList(List<List<AquisicaoBem>> listOfAquisicaoBemList) {
        this.listOfAquisicaoBemList = listOfAquisicaoBemList;
    }

}
