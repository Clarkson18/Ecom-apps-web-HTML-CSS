package com.appsweb.swappysource.servlets;

import implementaciones.UsuariosDAO;
import entidades.Usuario;
import utils.PassManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "CambiarContrasenaServlet", urlPatterns = {"/CambiarContrasenaServlet"})
public class CambiarContrasenaServlet extends HttpServlet {

    private Object getUsuarioSesion(HttpSession session) {
        Object u = session.getAttribute("usuarioLogueado");
        if (u == null) {
            u = session.getAttribute("usuario");
        }
        if (u == null) {
            u = session.getAttribute("cliente");
        }
        return u;
    }

    private String getCorreo(Object u) {
        try {
            Method m = u.getClass().getMethod("getCorreo");
            Object r = m.invoke(u);
            return r != null ? r.toString() : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/iniciarSesion.jsp");
            return;
        }

        Object uSesion = getUsuarioSesion(session);
        if (uSesion == null) {
            response.sendRedirect(request.getContextPath() + "/iniciarSesion.jsp");
            return;
        }

        String correoSesion = getCorreo(uSesion);
        if (correoSesion == null || correoSesion.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/iniciarSesion.jsp");
            return;
        }
        String actual = request.getParameter("passwordActual");
        String nueva = request.getParameter("passwordNueva");
        String confirmar = request.getParameter("confirmPassword");

        if (actual == null || actual.isBlank()
                || nueva == null || nueva.isBlank()
                || confirmar == null || confirmar.isBlank()) {

            request.setAttribute("error", "Completa todos los campos.");
            request.getRequestDispatcher("/cambiarContrasena.jsp").forward(request, response);
            return;
        }

        if (!nueva.equals(confirmar)) {
            request.setAttribute("error", "La confirmación no coincide.");
            request.getRequestDispatcher("/cambiarContrasena.jsp").forward(request, response);
            return;
        }

        try {
            UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();
            Usuario usuarioBD = usuariosDAO.getUsuarioCorreo(correoSesion);

            if (usuarioBD == null) {
                request.setAttribute("error", "No se encontró tu usuario.");
                request.getRequestDispatcher("/cambiarContrasena.jsp").forward(request, response);
                return;
            }
            if (!PassManager.verificarPassword(actual, usuarioBD.getPassword())) {
                request.setAttribute("error", "La contraseña actual no es correcta.");
                request.getRequestDispatcher("/cambiarContrasena.jsp").forward(request, response);
                return;
            }
            String hashNuevo = PassManager.hashPassword(nueva);
            usuarioBD.setPassword(hashNuevo);
            usuariosDAO.actualizarUsuario(usuarioBD);
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/iniciarSesion.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Ocurrió un error al cambiar la contraseña.");
            request.getRequestDispatcher("/cambiarContrasena.jsp").forward(request, response);
        }
    }
}
