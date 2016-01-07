package br.mack.dao.usuario;

import java.sql.*;
import java.util.List;
import javax.sql.*;

import org.apache.commons.logging.*;

import br.mack.dao.exception.DAORuntimeException;
import br.mack.entities.Usuario;
import br.mack.entities.UsuarioImpl;

public final class UsuarioUtil {

    static final private Log log = LogFactory.getLog(UsuarioUtil.class);

    static public int getUniqueUsuarioId(final Connection conn)
            throws java.sql.SQLException {
        int id;
        Statement stmtSelect = null;
        ResultSet rs = null;
        StringBuilder sbSelect = new StringBuilder();
        sbSelect.append("SELECT currentvalue FROM SYS.SYSSEQUENCES WHERE SEQUENCENAME = ''");
        sbSelect.append(UsuarioConstantes.USUARIO_ID_SEQUENCE_NAME.toUpperCase());
        sbSelect.append("'");
        try {
            stmtSelect = conn.createStatement();
            log.info("Executando a query: " + sbSelect.toString());
            rs = stmtSelect.executeQuery(sbSelect.toString());
            if (rs.next()) {
                log.info("OK");
            } else {
                log.info("NOK");
            }
            long aux = rs.getLong(1);
            id = (int) aux;
            id++;
        } finally {
            UsuarioUtil.closeStatement(stmtSelect);
            UsuarioUtil.closeResultSet(rs);
        }
        return id;
    }

    static public void setupUsuarioTable() throws SQLException {
        Statement stmtCreateTable = null;
        Statement stmtDropTable = null;
        Statement stmtCreateSeq = null;
        Statement stmtDropSeq = null;
        Connection conn = null;
        try {
            conn = UsuarioUtil.getConnection();
            conn.setAutoCommit(true);

            log.debug(
                    conn.getMetaData().getDriverName()
                    + " - "
                    + conn.getMetaData().getDriverVersion());
            StringBuilder sbDropTable = new StringBuilder();
            sbDropTable.append("DROP TABLE ");
            sbDropTable.append(UsuarioConstantes.USUARIO_TABLE_NAME);
            stmtDropTable = conn.createStatement();
            try {
                stmtDropTable.execute(sbDropTable.toString());
            } catch (SQLException ignored) {
            }
            StringBuilder sbDropSeq = new StringBuilder();
            sbDropSeq.append("DROP SEQUENCE ");
            sbDropSeq.append(UsuarioConstantes.USUARIO_ID_SEQUENCE_NAME);
            sbDropSeq.append(" RESTRICT");
            stmtDropSeq = conn.createStatement();
            log.info("DROP Sequence statement: "
                    + sbDropSeq.toString());
            try {
                stmtDropSeq.execute(sbDropSeq.toString());
                log.info("Sequence drop success.");
            } catch (SQLException ignored) {
            }
            StringBuilder sbCreateSeq = new StringBuilder();
            sbCreateSeq.append("CREATE SEQUENCE ");
            sbCreateSeq.append(UsuarioConstantes.USUARIO_ID_SEQUENCE_NAME);
            sbCreateSeq.append(" START WITH 1 INCREMENT BY 1 NO MAXVALUE");
            stmtCreateSeq = conn.createStatement();
            log.info("CREATE Sequence statement: " + sbCreateSeq.toString());
            stmtCreateSeq.execute(sbCreateSeq.toString());
            log.info("CREATE Sequence statement executed. ");
            StringBuilder sbCreateTable = new StringBuilder();
            sbCreateTable.append("CREATE TABLE ");
            sbCreateTable.append(UsuarioConstantes.USUARIO_TABLE_NAME);
            sbCreateTable.append(" ( ");
            sbCreateTable.append(" usuario_id INTEGER, ");
            sbCreateTable.append(" nome VARCHAR(15), ");
            sbCreateTable.append(" sobrenome VARCHAR(15) ");
            sbCreateTable.append(" ) ");
            stmtCreateTable = conn.createStatement();
            log.info("CREATE TABLE statement: " + sbCreateTable.toString());
            stmtCreateTable.execute(sbCreateTable.toString());
            log.info("Statement executed: " + sbCreateTable.toString());
        } finally {
            UsuarioUtil.closeStatement(stmtDropTable);
            UsuarioUtil.closeStatement(stmtDropSeq);
            UsuarioUtil.closeStatement(stmtCreateTable);
            UsuarioUtil.closeStatement(stmtCreateSeq);
            UsuarioUtil.closeJDBCConnection(conn);
        }
    }

    private UsuarioUtil() {
        // this constructor is intentionally private
    }

    static public Connection getConnection() {
        Connection conn = null;
        DataSource ds = null;
        try {
            Class.forName(UsuarioConstantes.DRIVER).newInstance();

            conn = DriverManager.getConnection(UsuarioConstantes.URL,
                    UsuarioConstantes.USER,
                    UsuarioConstantes.PASSWORD);
        } catch (ClassNotFoundException ex) {
            throw new DAORuntimeException(ex);
        } catch (InstantiationException e) {
            throw new DAORuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new DAORuntimeException(e);
        } catch (SQLException e) {
            throw new DAORuntimeException(e);
        }
        return conn;
    }

    public static void closeJDBCConnection(final Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                log.error(conn, ex);
            }
        }
    }

    public static void closeStatement(final Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                log.error(stmt, ex);
            }
        }
    }

    public static void closeResultSet(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                log.error(rs, ex);
            }
        }
    }

    static public List<Usuario> makeUsuarioObjectsFromResultSet(final ResultSet rs)
            throws java.sql.SQLException {
        List<Usuario> result = new java.util.ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String sobrenome = rs.getString("sobrenome");
            Usuario u = new UsuarioImpl(id, nome, sobrenome);
            result.add(u);
        }
        return result;
    }
}
