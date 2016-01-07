/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mack.ejb.service;

import br.mack.ejb.PosicaoBean;
import br.mack.ejb.UsuarioBean;
import br.mack.entities.Posicao;
import br.mack.entities.Usuario;
import br.mack.entities.UsuarioImpl;
import br.mack.facade.ApiMarvelFacade;
import br.mack.json.Thumbnail;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author brunow
 */
@Stateless
@Path("/lp3/")
public class LP3RestService {

    @EJB
    private PosicaoBean posicaoBean;

    @EJB
    private UsuarioBean usuarioBean;
    
    @EJB
    private ApiMarvelFacade marvelFacade;

    @Path("/novousuario")
    @PUT
    @Consumes(MediaType.TEXT_XML)
    public void novoUsuario(String usuarioXml) {
        System.out.println(usuarioXml);
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(UsuarioImpl.class);
            Unmarshaller u = jc.createUnmarshaller();
            StringReader reader = new StringReader(usuarioXml);
            UsuarioImpl usuario = (UsuarioImpl) u.unmarshal(reader);
            usuarioBean.criaUsuario(usuario);
        } catch (JAXBException ex) {
            Logger.getLogger(LP3RestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Path("/novaposicao")
    @PUT
    @Consumes(MediaType.TEXT_XML)
    public void novaPosicao(String posicaoXml) {
        System.out.println(posicaoXml);
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(Posicao.class);
            Unmarshaller u = jc.createUnmarshaller();
            StringReader reader = new StringReader(posicaoXml);
            Posicao posicao = (Posicao) u.unmarshal(reader);
            posicaoBean.incluirPosicao(posicao);
        } catch (JAXBException ex) {
            Logger.getLogger(LP3RestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("/posicoes/{login}")
    @Produces(value = {"application/xml"})
    public List<Posicao> listaPosicoes(@PathParam("login") final String login) {
        return posicaoBean.buscarPorUsuario(login);
    }
    
    @GET
    @Path("/avatar/{login}")
    public String obterAvatarUsuario(@PathParam("login") final String login) {
        Integer idMarvel = usuarioBean.obterIdMarvelPorLogin(login);
        try {
            Thumbnail tn =  this.marvelFacade.obterUrlImagem(idMarvel);
            return tn.getPath() + "." + tn.getExtension();
        } catch (IOException ex) {
            Logger.getLogger(LP3RestService.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
