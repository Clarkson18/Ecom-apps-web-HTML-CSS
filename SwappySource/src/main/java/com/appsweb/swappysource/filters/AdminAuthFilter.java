package com.appsweb.swappysource.filters;

import DTOs.UsuarioLogueadoDTO;
import Enumeradores.Rol;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "AdminAuthFilter", urlPatterns = {
        "/panelAdministrador.jsp",
        "/administrarUsuario.jsp",
        "/gestionarPedidos.jsp",
        "/gestionarProductos.jsp",
        "/gestionarResenas.jsp",
        "/AdministrativeServlet"
})
public class AdminAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // Permitimos login y recursos públicos sin bloquear
        String path = request.getServletPath();
        if (path.equals("/LoginServlet") || path.equals("/iniciarSesion.jsp") || path.startsWith("/styles")) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = request.getSession(false);
        UsuarioLogueadoDTO user = (session == null) ? null : (UsuarioLogueadoDTO) session.getAttribute("usuario");

        if (user == null || user.getRol() != Rol.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/iniciarSesion.jsp");
            return;
        }

        chain.doFilter(req, res);
    }
}
