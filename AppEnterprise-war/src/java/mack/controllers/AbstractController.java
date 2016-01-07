/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mack.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import br.mack.ejb.UsuarioBean;

/**
 *
 * @author 41381394
 */

public abstract class AbstractController implements Controller {

    private HttpServletRequest request;
    protected String returnPage;
    private List<Cookie> cookies;
    private UsuarioBean bean;
    private String mensagem;

    public void init(HttpServletRequest request, UsuarioBean bean) {
        this.setRequest(request);
        this.bean = bean;
    }

    public UsuarioBean getBean() {
		return bean;
	}

	public void setReturnPage(String page) {
        returnPage = page;
    }

    public String getReturnPage() {
        return returnPage;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    protected void addCookie(Cookie cookie) {
    	if(this.cookies == null){
    		this.cookies = new ArrayList<>();
    	}
    	this.cookies.add(cookie);
    }

	public List<Cookie> getCookies() {
		return cookies;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}