/*
 * Esta classe eh uma implementação de UsuarioDAO que usa o JPA ao invés do JDBC puro.
 */
package br.mack.dao.usuario;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.mack.dao.GenericDAO;
import br.mack.entities.UsuarioImpl;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 31212591
 */
@Stateless
public class UsuarioDAO extends GenericDAO<UsuarioImpl, Long> {

    /*
     * Esse atributo injeta o EntityManagerFactory para a Persistence Unit
     * criada.
     */
    @PersistenceContext(unitName = "Atividade3_PU")
    private EntityManager em;

    public UsuarioDAO() {
        super(UsuarioImpl.class);
    }

    public Collection buscaUsuarioPorNome(String nome) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT u from UsuarioImpl u ");
        jpql.append("WHERE lower(u.nome) = :nome OR lower(u.sobrenome) = :nome");
        TypedQuery<UsuarioImpl> q = em.createQuery(jpql.toString(), UsuarioImpl.class);
        q.setParameter("nome", nome.toLowerCase());
        List rs = q.getResultList();
        return rs;
    }

    public List<UsuarioImpl> buscaTodosUsuarios() {
        return findAll();
    }

    public void close() {
        this.em.close();
    }

    public boolean isClosed() {
        return !this.em.isOpen();
    }

    public boolean autenticar(String nome, String senha) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u FROM UsuarioImpl u ");
        sb.append("WHERE u.nome = :nome and u.senha = :senha");
        Query q = em.createQuery(sb.toString());
        q.setParameter("nome", nome);
        q.setParameter("senha", senha);

        try {
            UsuarioImpl ui = (UsuarioImpl) q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Integer obterIdMarvelPorLogin(String usuario){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u.idMarvel FROM UsuarioImpl u ");
        sb.append("WHERE u.login = :login");
        Query q = em.createQuery(sb.toString());
        q.setParameter("login", usuario);
        return (Integer) q.getSingleResult();
    }

}
