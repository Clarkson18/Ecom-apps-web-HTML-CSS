package com.appsweb.swappysource.servlets;

import BusinessObjects.AutenticacionBO;
import DTOs.UsuarioLogueadoDTO;
import Enumeradores.Rol;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo-electronico");
        String password = request.getParameter("passwordUsuario");

        UsuarioLogueadoDTO user = new AutenticacionBO().iniciarSesion(correo, password);

        if (user == null) {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("/iniciarSesion.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("usuario", user);

        if (user.getRol() == Rol.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/panelAdministrador.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/iniciarSesion.jsp");
    }
}
