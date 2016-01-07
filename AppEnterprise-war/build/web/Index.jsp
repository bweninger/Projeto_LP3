<%@page import="br.mack.entities.UsuarioImpl"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Usuarios!</h1>
        <%
            List<UsuarioImpl> usuarios
                    = (List<UsuarioImpl>) request.getAttribute("usuarios");

        %>

        <%if (usuarios.size() > 0) { %>
        <table>
            <% for (UsuarioImpl u : usuarios) {%>
            <tr>
            <td><%=u.getNome()%></td>
            <td><%=u.getSobrenome()%></td> <br/>
            <td>Acessar 
                <a href="/AppEnterpriseWeb/mapa.jsp?login=<%=u.getLogin()%>">mapa </a>
            de posi&ccedil;&otilde;es: </td>
        </tr>
        <%}%>
    </table>
    <%}%>

</body>
</html>
