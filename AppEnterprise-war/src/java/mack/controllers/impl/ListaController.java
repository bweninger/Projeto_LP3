/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mack.controllers.impl;

/**
 *
 * @author 41381394
 */
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.mack.entities.UsuarioImpl;
import mack.controllers.AbstractController;

public class ListaController extends AbstractController {

	public void execute() {
		try {
			List<UsuarioImpl> usuarios = getBean().buscaTodosUsuarios();
			this.setReturnPage("/Index.jsp");
			this.getRequest().setAttribute("usuarios", usuarios);
		} catch (Exception ex) {
			Logger.getLogger(ListaController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
