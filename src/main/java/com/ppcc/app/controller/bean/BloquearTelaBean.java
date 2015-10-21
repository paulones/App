/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppcc.app.controller.bean;

import com.ppcc.app.model.entity.Usuario;
import com.ppcc.app.model.jpa.controller.UsuarioJpaController;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.ppcc.app.util.Cookie;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Paulo
 */
@Named
@ViewScoped
public class BloquearTelaBean implements Serializable {

    private Usuario usuario;
    private String senha;
    private String pagina_anterior;
    private boolean senhaCorreta;

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            senha = "";
            senhaCorreta = true;
            pagina_anterior = Cookie.getCookie("pagina_anterior");
            usuario = new UsuarioJpaController().findUsuarioByCPF(Cookie.getCookie("usuario"));
        }
    }

    public void login() throws IOException {
        if (senha.equals(usuario.getSenha())) {
            Cookie.addCookie("usuario", usuario.getCpf(), 36000);
            if (pagina_anterior != null) {
                Cookie.apagarCookie("pagina_anterior");
                FacesContext.getCurrentInstance().getExternalContext().redirect(pagina_anterior);
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/home.xhtml");
            }
        } else {
            senhaCorreta = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha incorreta.", null));
        }
    }

    public void voltarAoLogin() throws IOException {
        Cookie.apagarCookie("usuario");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isSenhaCorreta() {
        return senhaCorreta;
    }

    public void setSenhaCorreta(boolean senhaCorreta) {
        this.senhaCorreta = senhaCorreta;
    }

}
