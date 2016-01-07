package mack.controllers.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import mack.controllers.AbstractController;
import br.mack.util.HashUtil;

public class LoginController extends AbstractController {

	@Override
	public void execute() {

		String usuarioRequest = getRequest().getParameter("usuario");
		String senhaRequest =  getRequest().getParameter("senha");

		String hexString = HashUtil.senhaToHash(senhaRequest);

		if (getBean().autenticar(usuarioRequest, hexString)) {
			HttpSession session =  getRequest().getSession();
			session.setAttribute("usuario", usuarioRequest);
			// setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30 * 60);
			Cookie userName = new Cookie("usuario", usuarioRequest);
			userName.setMaxAge(30 * 60);
			addCookie(userName);
			setReturnPage("/index.html");
		} else {
			setMensagem("Usuario ou senha invalido");
			setReturnPage("/Login.jsp");
		}
	}

}
