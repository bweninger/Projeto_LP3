/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mack.controllers.impl;

import java.util.Collection;

import javax.ejb.EJB;

import br.mack.ejb.UsuarioBean;
import mack.controllers.AbstractController;

/**
 *
 * @author 31212591
 */
public class BuscaController extends AbstractController {

	@Override
    public void execute() {
        String nome = getRequest().getParameter("nome");
        Collection buscaUsuarioPorNome = getBean().buscaUsuarioPorNome(nome);
        this.setReturnPage("/Index.jsp");
        this.getRequest().setAttribute("usuarios", buscaUsuarioPorNome);        
    }

}
