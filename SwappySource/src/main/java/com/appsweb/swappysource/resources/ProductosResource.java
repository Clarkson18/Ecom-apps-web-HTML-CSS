package com.appsweb.swappysource.resources;

import entidades.Producto;
import implementaciones.ProductoDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;
import java.util.stream.Collectors;

@Path("productos")
@Produces(MediaType.APPLICATION_JSON)
public class ProductosResource {

    private final ProductoDAO productoDAO = new ProductoDAO();

    @GET
    public Response listar(@QueryParam("search") String search,
            @QueryParam("categoria") String categoria) {

        List<Producto> productos = productoDAO.listaProductos();

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            productos = productos.stream()
                    .filter(p -> safe(p.getNombre()).toLowerCase().contains(s)
                    || safe(p.getDescripcionProducto()).toLowerCase().contains(s))
                    .collect(Collectors.toList());
        }

        if (categoria != null && !categoria.isBlank()) {
            String c = categoria.trim().toUpperCase();
            productos = productos.stream()
                    .filter(p -> safe(p.getCategoria()).toUpperCase().equals(c))
                    .collect(Collectors.toList());
        }

        List<Map<String, Object>> out = productos.stream()
                .map(this::toPublicJson)
                .collect(Collectors.toList());

        return Response.ok(out).build();
    }

    @GET
    @Path("{id}")
    public Response detalle(@PathParam("id") String id) {
        try {
            Producto p = productoDAO.obtenerProductoPorId(id);
            if (p == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(toPublicJson(p)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "ID inválido"))
                    .build();
        }
    }

    private Map<String, Object> toPublicJson(Producto p) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", p.getId() != null ? p.getId().toHexString() : null);
        m.put("nombre", safe(p.getNombre()));
        m.put("descripcion", safe(p.getDescripcionProducto()));
        m.put("precio", p.getPrecio());
        m.put("categoria", safe(p.getCategoria())); // enum -> String
        m.put("stock", p.getCantidadExistencia());
        return m;
    }

    private String safe(Object o) {
        return (o == null) ? "" : o.toString();
    }
}
