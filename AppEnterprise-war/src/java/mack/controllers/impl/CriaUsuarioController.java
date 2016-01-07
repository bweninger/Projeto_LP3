/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mack.controllers.impl;


import br.mack.entities.UsuarioImpl;
import mack.controllers.AbstractController;
import br.mack.util.HashUtil;

/**
 *
 * @author Lucas
 */
public class CriaUsuarioController extends AbstractController {

	@Override
	public void execute() {

		String nome = getRequest().getParameter("nome");
		String sobrenome = getRequest().getParameter("sobrenome");
		String senha = getRequest().getParameter("senha");
		String senha2 = getRequest().getParameter("senha2");

		if (senha == null || senha2 == null) {
			throw new IllegalArgumentException("FAVOR INFORMAR AS SENHAS");
		}
		String hash1 = HashUtil.senhaToHash(senha);
		String hash2 = HashUtil.senhaToHash(senha2);

		if (!hash1.equals(hash2)) {
			throw new IllegalArgumentException("SENHAS INCOMPATIVEIS");
		}
		
		

		UsuarioImpl u = new UsuarioImpl();
		u.setNome(nome);
		u.setSobrenome(sobrenome);
		u.setSenha(hash1.toString());
		this.getBean().criaUsuario(u);
		// TODO alterar para uma pagina de sucesso
		this.setReturnPage("/Login.jsp");
	}
}
