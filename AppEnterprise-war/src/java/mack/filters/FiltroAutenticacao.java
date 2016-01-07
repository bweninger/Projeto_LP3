package mack.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FiltroAutenticacao implements Filter {
	
	private final List<String> urlsSemSeguranca = new ArrayList<>();
	
    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.urlsSemSeguranca.add("/AppEnterpriseWeb/Login.jsp");
        this.urlsSemSeguranca.add("/AppEnterpriseWeb/cadastraUsuario.html");
        this.context.log("Filtro de autenticacao inicializado");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        this.context.log("Iniciando filtro");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::" + uri);

        HttpSession session = req.getSession(false);
        Object usuario = session != null ? session.getAttribute("usuario") : session;

        if (usuario == null && !(urlsSemSeguranca.contains(uri)) && 
                !uri.contains("LP3Rest")) {
            this.context.log("Unauthorized access request");
            res.sendRedirect("Login.jsp");
        } else {
            this.context.log("Acesso autorizado");
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        //close any resources here
    }
}
