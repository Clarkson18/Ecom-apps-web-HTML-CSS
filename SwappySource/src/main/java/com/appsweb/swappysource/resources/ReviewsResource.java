package com.appsweb.swappysource.resources;

import DTOs.UsuarioLogueadoDTO;
import Enumeradores.EstadoReview;
import Enumeradores.Rol;
import entidades.Review;
import entidades.Usuario;
import implementaciones.ReviewDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import org.bson.types.ObjectId;

import java.util.*;

@Path("reviews")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewsResource {

    private final ReviewDAO dao = new ReviewDAO();

    public static class CreateReviewRequest {

        public String productoId;
        public String comentario;
        public Integer calificacion;
    }

    @GET
    public Response listar(@QueryParam("productoId") String productoId,
            @Context HttpServletRequest request) {
        if (productoId == null || productoId.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "productoId es obligatorio"))
                    .build();
        }

        ObjectId pid;
        try {
            pid = new ObjectId(productoId);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "productoId inválido"))
                    .build();
        }

        String correoSesion = getCorreoCliente(request);
        boolean esCliente = isCliente(request);

        List<Review> aprobadas = dao.listarAprobadasPorProducto(pid);

        // Si es cliente, además ve SUS reseñas aunque estén PENDIENTES/RECHAZADAS
        if (esCliente && correoSesion != null && !correoSesion.isBlank()) {
            List<Review> mias = dao.listarPorProductoYCorreo(pid, correoSesion);
            // unir sin duplicar
            Map<String, Review> union = new LinkedHashMap<>();
            for (Review r : aprobadas) {
                union.put(r.getId().toHexString(), r);
            }
            for (Review r : mias) {
                union.put(r.getId().toHexString(), r);
            }
            aprobadas = new ArrayList<>(union.values());
        }

        // ordenar por fecha desc si existe
        aprobadas.sort((a, b) -> {
            Date fa = a.getFecha();
            Date fb = b.getFecha();
            if (fa == null && fb == null) {
                return 0;
            }
            if (fa == null) {
                return 1;
            }
            if (fb == null) {
                return -1;
            }
            return fb.compareTo(fa);
        });

        List<Map<String, Object>> out = new ArrayList<>();
        for (Review r : aprobadas) {
            out.add(toJson(r));
        }

        return Response.ok(out).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(CreateReviewRequest body,
            @Context HttpServletRequest request) {

        if (!isCliente(request)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "Solo clientes pueden publicar reseñas"))
                    .build();
        }

        String correo = getCorreoCliente(request);
        if (correo == null || correo.isBlank()) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "No hay sesión de cliente"))
                    .build();
        }

        if (body == null || body.productoId == null || body.productoId.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "productoId es obligatorio"))
                    .build();
        }

        ObjectId pid;
        try {
            pid = new ObjectId(body.productoId);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "productoId inválido"))
                    .build();
        }

        String comentario = (body.comentario == null) ? "" : body.comentario.trim();
        int cal = (body.calificacion == null) ? 0 : body.calificacion;

        if (comentario.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "El comentario es obligatorio"))
                    .build();
        }
        if (cal < 1 || cal > 5) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "La calificación debe ser 1 a 5"))
                    .build();
        }

        Review r = new Review();
        r.setId(new ObjectId());
        r.setProductoId(pid);
        r.setCorreoUsuario(correo);
        r.setComentario(comentario);
        r.setCalificacion(cal);
        r.setEstado(EstadoReview.PENDIENTE);
        r.setFecha(new Date());

        dao.agregarReview(r);

        return Response.status(Response.Status.CREATED).entity(toJson(r)).build();
    }

    private Map<String, Object> toJson(Review r) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", r.getId() != null ? r.getId().toHexString() : null);
        m.put("productoId", r.getProductoId() != null ? r.getProductoId().toHexString() : null);
        m.put("correoUsuario", r.getCorreoUsuario());
        m.put("comentario", r.getComentario());
        m.put("calificacion", r.getCalificacion());
        m.put("estado", r.getEstado() != null ? r.getEstado().name() : null);
        m.put("fecha", r.getFecha());
        return m;
    }

    //  sesión ("usuario" del LoginServlet) 
    private boolean isCliente(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        Object u = session.getAttribute("usuario");

        if (u instanceof UsuarioLogueadoDTO) {
            UsuarioLogueadoDTO dto = (UsuarioLogueadoDTO) u;
            return dto.getRol() == Rol.CLIENTE;
        }

        if (u instanceof Usuario) {
            Usuario ent = (Usuario) u;
            return ent.getRol() == Rol.CLIENTE;
        }

        Object c = session.getAttribute("cliente");
        if (c instanceof Usuario) {
            Usuario ent2 = (Usuario) c;
            return ent2.getRol() == Rol.CLIENTE;
        }
        return false;
    }

    private String getCorreoCliente(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        Object u = session.getAttribute("usuario");

        if (u instanceof UsuarioLogueadoDTO) {
            UsuarioLogueadoDTO dto = (UsuarioLogueadoDTO) u;
            return dto.getCorreo();
        }

        if (u instanceof Usuario) {
            Usuario ent = (Usuario) u;
            return ent.getCorreo();
        }

        Object c = session.getAttribute("cliente");
        if (c instanceof Usuario) {
            Usuario ent2 = (Usuario) c;
            return ent2.getCorreo();
        }

        return null;
    }
}
