package com.appsweb.swappysource.resources;

import Enumeradores.Rol;
import entidades.Usuario;
import implementaciones.UsuariosDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import utils.PassManager;

@Path("clientes")
@Produces(MediaType.APPLICATION_JSON)
public class ClientesResource {

    private static final String SESSION_CLIENTE = "cliente";

    public static class RegistroRequest {
        public String nombre;
        public String correo;
        public String telefono;
        public String direccion;
        public String password;
        public String confirmPassword;
    }

    public static class LoginRequest {
        public String correo;
        public String password;
    }

    public static class ApiResponse {
        public boolean ok;
        public String message;

        public ApiResponse() {}
        public ApiResponse(boolean ok, String message) {
            this.ok = ok;
            this.message = message;
        }
    }

    public static class ClienteResponse {
        public String id;
        public String nombre;
        public String correo;
        public String telefono;
        public List<String> direcciones;
        public String rol;
    }

    private String safe(String s) {
        return (s == null) ? "" : s.trim();
    }

    private ClienteResponse toClienteResponse(Usuario u) {
        ClienteResponse r = new ClienteResponse();
        r.id = (u.getId() != null) ? u.getId().toHexString() : null;
        r.nombre = u.getNombre();
        r.correo = u.getCorreo();
        r.telefono = u.getTelefono();
        r.direcciones = u.getDirecciones();
        r.rol = (u.getRol() != null) ? u.getRol().name() : null;
        return r;
    }

    private boolean passwordsMatch(String raw, String stored) {
        if (raw == null || stored == null) return false;

        if (stored.contains(":")) {
            try {
                return PassManager.verificarPassword(raw, stored);
            } catch (Exception e) {
                return false;
            }
        }
        return stored.equals(raw);
    }

    @POST
    @Path("registro")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarCliente(RegistroRequest req) {

        if (req == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse(false, "Solicitud inválida."))
                    .build();
        }

        String nombre = safe(req.nombre);
        String correo = safe(req.correo).toLowerCase();
        String telefono = safe(req.telefono);
        String direccion = safe(req.direccion);

        String password = (req.password == null) ? "" : req.password;
        String confirm = (req.confirmPassword == null) ? "" : req.confirmPassword;

        if (nombre.isBlank() || correo.isBlank() || password.isBlank() || confirm.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse(false, "Completa los campos obligatorios."))
                    .build();
        }

        if (!password.equals(confirm)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse(false, "Las contraseñas no coinciden."))
                    .build();
        }

        Usuario existente = UsuariosDAO.getInstance().getUsuarioCorreo(correo);
        if (existente != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ApiResponse(false, "Ya existe una cuenta con ese correo."))
                    .build();
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setCorreo(correo);
        nuevo.setTelefono(telefono);

        List<String> direcciones = new ArrayList<>();
        if (!direccion.isBlank()) direcciones.add(direccion);
        nuevo.setDirecciones(direcciones);

        nuevo.setRol(Rol.CLIENTE);

        // llegA password al DAO (DAO decide si lo hashea o no)
        nuevo.setPassword(password);

        UsuariosDAO.getInstance().registrarUsuario(nuevo);

        return Response.ok(new ApiResponse(true, "Cuenta creada. Ahora inicia sesión.")).build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginCliente(LoginRequest req, @Context HttpServletRequest servletRequest) {

        if (req == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse(false, "Solicitud inválida."))
                    .build();
        }

        String correo = safe(req.correo).toLowerCase();
        String password = (req.password == null) ? "" : req.password;

        if (correo.isBlank() || password.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse(false, "Correo y contraseña son obligatorios."))
                    .build();
        }

        Usuario u = UsuariosDAO.getInstance().getUsuarioCorreo(correo);
        if (u == null || !passwordsMatch(password, u.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiResponse(false, "Credenciales incorrectas."))
                    .build();
        }

        if (u.getRol() != Rol.CLIENTE) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(new ApiResponse(false, "Este acceso es solo para clientes."))
                    .build();
        }

        HttpSession session = servletRequest.getSession(true);
        session.setAttribute(SESSION_CLIENTE, u);

        return Response.ok(toClienteResponse(u)).build();
    }

    @POST
    @Path("logout")
    public Response logoutCliente(@Context HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession(false);
        if (session != null) {
            session.removeAttribute(SESSION_CLIENTE);
        }
        return Response.ok(new ApiResponse(true, "Sesión cerrada.")).build();
    }

    @GET
    @Path("me")
    public Response me(@Context HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession(false);
        if (session == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiResponse(false, "No hay sesión activa."))
                    .build();
        }

        Object obj = session.getAttribute(SESSION_CLIENTE);
        if (!(obj instanceof Usuario)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiResponse(false, "No hay sesión activa."))
                    .build();
        }

        Usuario u = (Usuario) obj;
        return Response.ok(toClienteResponse(u)).build();
    }
}
