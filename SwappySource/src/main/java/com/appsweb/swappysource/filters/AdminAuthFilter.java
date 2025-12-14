package com.appsweb.swappysource.filters;

import DTOs.UsuarioLogueadoDTO;
import Enumeradores.Rol;
import entidades.Usuario;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "AdminAuthFilter",
        urlPatterns = {
            "/panelAdministrador.jsp",
            "/administrarUsuario.jsp",
            "/gestionarProductos.jsp",
            "/gestionarPedidos.jsp",
            "/gestionarResenas.jsp"
        }
)
public class AdminAuthFilter implements Filter {

    private boolean esAdmin(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof UsuarioLogueadoDTO) {
            UsuarioLogueadoDTO dto = (UsuarioLogueadoDTO) obj;
            return dto.getRol() == Rol.ADMIN;
        }

        if (obj instanceof Usuario) {
            Usuario u = (Usuario) obj;
            return u.getRol() == Rol.ADMIN;
        }

        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect(req.getContextPath() + "/iniciarSesion.jsp");
            return;
        }

        Object admin = session.getAttribute("admin");
        Object usuario = session.getAttribute("usuario");
        Object usuarioLogueado = session.getAttribute("usuarioLogueado");

        if (esAdmin(admin) || esAdmin(usuario) || esAdmin(usuarioLogueado)) {
            chain.doFilter(request, response);
            return;
        }

        res.sendRedirect(req.getContextPath() + "/iniciarSesion.jsp");
    }
}
