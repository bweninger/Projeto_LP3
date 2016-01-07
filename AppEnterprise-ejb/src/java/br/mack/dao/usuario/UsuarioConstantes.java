/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mack.dao.usuario;

/**
 *
 * @author 41305388
 */
final class UsuarioConstantes {

    static public final String URL = "jdbc:derby://localhost:1527/lp3";
    static public final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static public final String USER = "lp3";
    static public final String PASSWORD = "lp3";
    static public final String USUARIO_DATASOURCE_NAME
            = "java:comp/env/jdbc/FilmesNonXADataSource";
    static public final String USUARIO_TABLE_NAME = "usuarios";
    static public final String USUARIO_ID_SEQUENCE_NAME = "usuario_id_seq";

    private UsuarioConstantes() {
    }
}
