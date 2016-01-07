package mack.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
//    private final String userID = "admin";
//    private final String password = "senha";
    
 

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // get request parameters for userID and password

//        UsuarioDAO dao = UsuarioDAOFactory.getUsuarioDAO();
//
//        String usuarioRequest = request.getParameter("usuario");
//        String senhaRequest = request.getParameter("senha");
//
//        if (dao.autenticar(usuarioRequest, senhaRequest)) {
//            HttpSession session = request.getSession();
//            session.setAttribute("usuario", usuarioRequest);
//            //setting session to expiry in 30 mins
//            session.setMaxInactiveInterval(30 * 60);
//            Cookie userName = new Cookie("usuario", usuarioRequest);
//            userName.setMaxAge(30 * 60);
//            response.addCookie(userName);
//            response.sendRedirect("index.html");
//        } else {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
//            PrintWriter out = response.getWriter();
//            out.println("<font color=red>Usuario ou senha incorretos.</font>");
//            rd.include(request, response);
//        }
    }
}
