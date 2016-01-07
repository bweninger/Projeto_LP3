/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mack.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
/**
 *
 * @author 41381394
 */
import javax.servlet.http.HttpServletRequest;

import br.mack.ejb.UsuarioBean;

public interface Controller {

    public void execute();

    public void init(HttpServletRequest request, UsuarioBean bean);

    public String getReturnPage();
    
    public List<Cookie> getCookies();
    
    public String getMensagem();
}
