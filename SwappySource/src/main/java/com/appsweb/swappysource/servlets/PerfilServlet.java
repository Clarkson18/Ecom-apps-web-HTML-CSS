package com.appsweb.swappysource.servlets;

import DTOs.UsuarioLogueadoDTO;
import Enumeradores.Rol;
import entidades.Usuario;
import implementaciones.UsuariosDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PerfilServlet", urlPatterns = {"/PerfilServlet"})
public class PerfilServlet extends HttpServlet {

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

    private Rol getRol(Object u) {
        if (u instanceof UsuarioLogueadoDTO) {
            return ((UsuarioLogueadoDTO) u).getRol();
        }
        if (u instanceof Usuario) {
            return ((Usuario) u).getRol();
        }
        return null;
    }

    private String getCorreo(Object u) {
        if (u instanceof UsuarioLogueadoDTO) {
            return ((UsuarioLogueadoDTO) u).getCorreo();
        }
        if (u instanceof Usuario) {
            return ((Usuario) u).getCorreo();
        }
        return null;
    }

    private String getNombre(Object u) {
        if (u instanceof UsuarioLogueadoDTO) {
            return ((UsuarioLogueadoDTO) u).getNombre();
        }
        if (u instanceof Usuario) {
            return ((Usuario) u).getNombre();
        }
        return null;
    }

    private String getTelefono(Object u) {
        if (u instanceof UsuarioLogueadoDTO) {
            return ((UsuarioLogueadoDTO) u).getTelefono();
        }
        if (u instanceof Usuario) {
            return ((Usuario) u).getTelefono();
        }
        return null;
    }

    private String getDireccion(Object u) {
        if (u instanceof UsuarioLogueadoDTO) {
            List<String> dirs = ((UsuarioLogueadoDTO) u).getDirecciones();
            return (dirs != null && !dirs.isEmpty()) ? dirs.get(0) : "";
        }
        if (u instanceof Usuario) {
            List<String> dirs = ((Usuario) u).getDirecciones();
            return (dirs != null && !dirs.isEmpty()) ? dirs.get(0) : "";
        }
        return "";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

        Rol rol = getRol(uSesion);
        if (rol == Rol.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/panelAdministrador.jsp");
            return;
        }

        request.setAttribute("nombre", getNombre(uSesion));
        request.setAttribute("correo", getCorreo(uSesion));
        request.setAttribute("telefono", getTelefono(uSesion));
        request.setAttribute("direccion", getDireccion(uSesion));

        request.getRequestDispatcher("/perfil.jsp").forward(request, response);
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

        Rol rol = getRol(uSesion);
        if (rol == Rol.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/panelAdministrador.jsp");
            return;
        }

        String correoSesion = getCorreo(uSesion);
        if (correoSesion == null || correoSesion.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/iniciarSesion.jsp");
            return;
        }

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        if (nombre == null || nombre.isBlank()
                || correo == null || correo.isBlank()
                || telefono == null || telefono.isBlank()
                || direccion == null || direccion.isBlank()) {

            request.setAttribute("error", "Completa todos los campos.");
            request.setAttribute("nombre", nombre);
            request.setAttribute("correo", correo);
            request.setAttribute("telefono", telefono);
            request.setAttribute("direccion", direccion);
            request.getRequestDispatcher("/perfil.jsp").forward(request, response);
            return;
        }

        try {
            UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();

            Usuario usuarioBD = usuariosDAO.getUsuarioCorreo(correoSesion);
            if (usuarioBD == null) {
                request.setAttribute("error", "No se encontró tu usuario en la base de datos.");
                request.getRequestDispatcher("/perfil.jsp").forward(request, response);
                return;
            }

            if (!correoSesion.equalsIgnoreCase(correo)) {
                Usuario existente = usuariosDAO.getUsuarioCorreo(correo);
                if (existente != null && existente.getId() != null
                        && usuarioBD.getId() != null
                        && !existente.getId().equals(usuarioBD.getId())) {

                    request.setAttribute("error", "Ese correo ya está registrado.");
                    request.getRequestDispatcher("/perfil.jsp").forward(request, response);
                    return;
                }
            }

            usuarioBD.setNombre(nombre);
            usuarioBD.setCorreo(correo);
            usuarioBD.setTelefono(telefono);

            List<String> direcciones = new ArrayList<>();
            direcciones.add(direccion);
            usuarioBD.setDirecciones(direcciones);

            Usuario actualizado = usuariosDAO.actualizarUsuario(usuarioBD);

            // refrescar sesión sin casts raros
            if (uSesion instanceof UsuarioLogueadoDTO) {
                UsuarioLogueadoDTO dto = new UsuarioLogueadoDTO(
                        actualizado.getNombre(),
                        actualizado.getCorreo(),
                        (actualizado.getId() != null ? actualizado.getId().toHexString() : null),
                        actualizado.getRol(),
                        actualizado.getDirecciones(),
                        actualizado.getTelefono()
                );
                session.setAttribute("usuarioLogueado", dto);
                session.setAttribute("usuario", dto);
                session.setAttribute("cliente", dto);
            } else if (uSesion instanceof Usuario) {
                session.setAttribute("usuario", actualizado);
                session.setAttribute("cliente", actualizado);
            }

            request.setAttribute("mensaje", "Tus datos se actualizaron correctamente.");
            request.setAttribute("nombre", actualizado.getNombre());
            request.setAttribute("correo", actualizado.getCorreo());
            request.setAttribute("telefono", actualizado.getTelefono());
            request.setAttribute("direccion", (actualizado.getDirecciones() != null && !actualizado.getDirecciones().isEmpty())
                    ? actualizado.getDirecciones().get(0) : "");

            request.getRequestDispatcher("/perfil.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Ocurrió un error al actualizar tu perfil.");
            request.getRequestDispatcher("/perfil.jsp").forward(request, response);
        }
    }
}
