package br.mack.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.mack.dao.usuario.UsuarioDAO;
import br.mack.ejb.interceptor.LogInterceptor;
import br.mack.entities.UsuarioImpl;

@Stateless (name = "UsuarioBean")
@LocalBean
@Interceptors(value={LogInterceptor.class})
public class UsuarioBean {

	@EJB
	private UsuarioDAO usuarioDao;

	public Collection buscaUsuarioPorNome(String nome) {
		return this.usuarioDao.buscaUsuarioPorNome(nome);
	}

	public List<UsuarioImpl> buscaTodosUsuarios() {
		return this.usuarioDao.findAll();
	}

	public boolean autenticar(String usuario, String senha) {
		return usuarioDao.autenticar(usuario, senha);
	}
	
	public void criaUsuario(UsuarioImpl usuario){
		this.usuarioDao.create(usuario);
	}
        
        public Integer obterIdMarvelPorLogin(String login) {
            return this.usuarioDao.obterIdMarvelPorLogin(login);
        }
}
