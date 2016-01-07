/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mack.dao.posicao;

import br.mack.dao.GenericDAO;
import br.mack.entities.Posicao;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author brunow
 */
@Stateless
@LocalBean
public class PosicaoDAO extends GenericDAO<Posicao, Long> {

    @PersistenceContext(unitName = "Atividade3_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PosicaoDAO() {
        super(Posicao.class);
    }

    public List<Posicao> buscarPorUsuario(String loginUsuario) {
        Query query = em.createQuery("FROM Posicao p where p.login = :login");
        query.setParameter("login", loginUsuario);
        List<Posicao> list = query.getResultList();
        return list;
    }

}
