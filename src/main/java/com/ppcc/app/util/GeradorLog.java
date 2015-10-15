/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ppcc.app.util;

import com.ppcc.app.model.entity.Log;
import com.ppcc.app.model.jpa.controller.AutorizacaoJpaController;
import com.ppcc.app.model.jpa.controller.LogJpaController;
import com.ppcc.app.model.jpa.controller.UtilJpaController;

/**
 *
 * @author paulones
 */
public class GeradorLog {
    
    
    public static void criar(Integer idFk, String tabela, char operacao) throws Exception{
        Log log = new Log();
        log.setDataDeCriacao(new UtilJpaController().findServerTime());
        log.setIdFk(idFk);
        log.setOperacao(operacao);
        log.setTabela(tabela);
        log.setInstituicaoFk(new AutorizacaoJpaController().findAutorizacaoByCPF(Cookie.getCookie("usuario")).getInstituicaoFk());
        new LogJpaController().create(log);
    }
}
