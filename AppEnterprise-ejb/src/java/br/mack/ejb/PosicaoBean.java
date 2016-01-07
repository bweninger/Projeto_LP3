/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mack.ejb;

import br.mack.dao.posicao.PosicaoDAO;
import br.mack.entities.Posicao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author brunow
 */
@Stateless
public class PosicaoBean {
    
    @EJB
    private PosicaoDAO posicaoDao;
    
    public List<Posicao> buscarPorUsuario(String loginUsuario) {
        return this.posicaoDao.buscarPorUsuario(loginUsuario);
    }
    
    public void incluirPosicao(Posicao p) {
        this.posicaoDao.create(p);
    }
    
}
