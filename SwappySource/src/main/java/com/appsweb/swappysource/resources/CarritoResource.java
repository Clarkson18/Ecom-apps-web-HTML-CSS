package com.appsweb.swappysource.resources;

import DTOs.UsuarioLogueadoDTO;
import Enumeradores.Rol;
import entidades.Producto;
import entidades.Usuario;
import implementaciones.ProductoDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;

@Path("carrito")
@Produces(MediaType.APPLICATION_JSON)
public class CarritoResource {

    private final ProductoDAO productoDAO = new ProductoDAO();

    // DTOs request 
    public static class AddReq {
        public String productoId;
        public Integer cantidad;
    }

    public static class UpdateReq {
        public String productoId;
        public Integer cantidad;
    }

    public static class RemoveReq {
        public String productoId;
    }

    // Helpers de sesión 
    private boolean isCliente(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;

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
        if (session == null) return null;

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

    @SuppressWarnings("unchecked")
    private Map<String, Integer> getCarritoMap(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        Object obj = session.getAttribute("carrito");
        if (obj instanceof Map) {
            return (Map<String, Integer>) obj;
        }

        Map<String, Integer> carrito = new LinkedHashMap<>();
        session.setAttribute("carrito", carrito);
        return carrito;
    }

    // ENDPOINTS 

    @GET
    public Response verCarrito(@Context HttpServletRequest request) {
        if (!isCliente(request)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "Solo clientes pueden ver el carrito"))
                    .build();
        }

        Map<String, Integer> carrito = getCarritoMap(request);

        // Armamos salida enriquecida: producto + cantidad + subtotal
        List<Map<String, Object>> items = new ArrayList<>();
        double total = 0;

        for (Map.Entry<String, Integer> e : carrito.entrySet()) {
            String pid = e.getKey();
            int qty = e.getValue();

            Producto p = productoDAO.obtenerProductoPorId(pid);
            if (p == null) continue; // producto ya no existe, lo ignoramos

            double subtotal = p.getPrecio() * qty;
            total += subtotal;

            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", p.getId() != null ? p.getId().toHexString() : pid);
            item.put("nombre", safe(p.getNombre()));
            item.put("descripcion", safe(p.getDescripcionProducto()));
            item.put("precio", p.getPrecio());
            item.put("categoria", safe(p.getCategoria()));
            item.put("stock", p.getCantidadExistencia());
            item.put("cantidad", qty);
            item.put("subtotal", subtotal);

            items.add(item);
        }

        Map<String, Object> out = new LinkedHashMap<>();
        out.put("correo", getCorreoCliente(request));
        out.put("items", items);
        out.put("total", total);

        return Response.ok(out).build();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(AddReq body, @Context HttpServletRequest request) {
        if (!isCliente(request)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "Solo clientes pueden agregar al carrito"))
                    .build();
        }

        if (body == null || body.productoId == null || body.productoId.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "productoId es obligatorio"))
                    .build();
        }

        int qty = (body.cantidad == null) ? 1 : body.cantidad;
        if (qty <= 0) qty = 1;

        Producto p = productoDAO.obtenerProductoPorId(body.productoId);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Producto no encontrado"))
                    .build();
        }

        int stock = p.getCantidadExistencia();
        if (stock <= 0) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("error", "Producto sin stock"))
                    .build();
        }

        Map<String, Integer> carrito = getCarritoMap(request);
        int actual = carrito.getOrDefault(body.productoId, 0);
        int nuevo = actual + qty;

        if (nuevo > stock) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("error", "No hay stock suficiente", "stock", stock, "cantidadActual", actual))
                    .build();
        }

        carrito.put(body.productoId, nuevo);

        return Response.ok(Map.of(
                "ok", true,
                "productoId", body.productoId,
                "cantidad", nuevo
        )).build();
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(UpdateReq body, @Context HttpServletRequest request) {
        if (!isCliente(request)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "Solo clientes pueden modificar el carrito"))
                    .build();
        }

        if (body == null || body.productoId == null || body.productoId.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "productoId es obligatorio"))
                    .build();
        }

        int qty = (body.cantidad == null) ? 1 : body.cantidad;

        Map<String, Integer> carrito = getCarritoMap(request);

        if (qty <= 0) {
            carrito.remove(body.productoId);
            return Response.ok(Map.of("ok", true, "removed", true)).build();
        }

        Producto p = productoDAO.obtenerProductoPorId(body.productoId);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Producto no encontrado"))
                    .build();
        }

        int stock = p.getCantidadExistencia();
        if (qty > stock) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("error", "No hay stock suficiente", "stock", stock))
                    .build();
        }

        carrito.put(body.productoId, qty);

        return Response.ok(Map.of(
                "ok", true,
                "productoId", body.productoId,
                "cantidad", qty
        )).build();
    }

    @POST
    @Path("remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remove(RemoveReq body, @Context HttpServletRequest request) {
        if (!isCliente(request)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", "Solo clientes pueden modificar el carrito"))
                    .build();
        }

        if (body == null || body.productoId == null || body.productoId.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "productoId es obligatorio"))
                    .build();
        }

        Map<String, Integer> carrito = getCarritoMap(request);
        carrito.remove(body.productoId);

        return Response.ok(Map.of("ok", true)).build();
    }

    private String safe(Object o) {
        return (o == null) ? "" : o.toString();
    }
}
