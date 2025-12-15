package com.appsweb.swappysource.servlets;

import BusinessObjects.PedidoBO;
import Enumeradores.Categoria;
import Enumeradores.EstadoEnvio;
import Enumeradores.Rol;
import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import implementaciones.Persistencia;
import implementaciones.UsuariosDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AdministrativeServlet", urlPatterns = {"/AdministrativeServlet"})
public class AdministrativeServlet extends HttpServlet {

    private final Persistencia persistencia = new Persistencia();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendRedirect(request.getContextPath() + "/panelAdministrador.jsp");
            return;
        }

        switch (accion) {
            case "getUsuarios": {
                List<Usuario> usuarios = UsuariosDAO.getInstance().consultarUsuarios();
                request.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("/administrarUsuario.jsp").forward(request, response);
                break;
            }
            case "getPedidos": {
                List<Pedido> pedidos = PedidoBO.getInstance().consultarPedidos();
                request.setAttribute("pedidos", pedidos);
                request.getRequestDispatcher("/gestionarPedidos.jsp").forward(request, response);
                break;
            }
            case "getProductos": {
                List<Producto> productos = persistencia.listaProductos();
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("/gestionarProductos.jsp").forward(request, response);
                break;
            }
            case "getResenas": {
                List<entidades.Review> reviews = new implementaciones.ReviewDAO().listarReviews();
                request.setAttribute("reviews", reviews);
                request.getRequestDispatcher("/gestionarResenas.jsp").forward(request, response);
                break;
            }

            default:
                response.sendRedirect(request.getContextPath() + "/panelAdministrador.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendRedirect(request.getContextPath() + "/panelAdministrador.jsp");
            return;
        }

        switch (accion) {

            // Usuarios 
            case "deleteUsuario": {
                String correo = request.getParameter("correo");
                Usuario u = new Usuario();
                u.setCorreo(correo);
                UsuariosDAO.getInstance().eliminarUsuario(u);
                response.sendRedirect(request.getContextPath() + "/AdministrativeServlet?accion=getUsuarios");
                break;
            }

            case "updateRolUsuario": {
                String correo = request.getParameter("correo");
                String rol = request.getParameter("rol");

                Usuario existente = UsuariosDAO.getInstance().getUsuarioCorreo(correo);
                existente.setRol(Rol.valueOf(rol));
                UsuariosDAO.getInstance().actualizarUsuario(existente);

                response.sendRedirect(request.getContextPath() + "/AdministrativeServlet?accion=getUsuarios");
                break;
            }

            // Productos 
            case "addProducto": {
                Producto p = new Producto();
                p.setId(new ObjectId());
                p.setNombre(request.getParameter("nombre"));
                p.setDescripcionProducto(request.getParameter("descripcion"));

                p.setPrecio(Double.parseDouble(request.getParameter("precio")));
                p.setCantidadExistencia(Integer.parseInt(request.getParameter("cantidad")));
                p.setCategoria(Categoria.valueOf(request.getParameter("categoria")));

                persistencia.agregarProducto(p);

                response.sendRedirect(request.getContextPath() + "/AdministrativeServlet?accion=getProductos");
                break;
            }

            case "deleteProducto": {
                String idProducto = request.getParameter("idProducto");

                Producto p = new Producto();
                p.setId(new ObjectId(idProducto));
                persistencia.eliminarProducto(p);

                response.sendRedirect(request.getContextPath() + "/AdministrativeServlet?accion=getProductos");
                break;
            }

            // Pedidos 
            case "updateEstadoPedido": {
                String idPedido = request.getParameter("idPedido");
                String estado = request.getParameter("estado");

                PedidoBO.getInstance().actualizarEstadoPedido(idPedido, EstadoEnvio.valueOf(estado));

                response.sendRedirect(request.getContextPath() + "/AdministrativeServlet?accion=getPedidos");
                break;
            }
            case "updateEstadoReview": {
                String idReview = request.getParameter("idReview");
                String estado = request.getParameter("estado");

                new implementaciones.ReviewDAO().actualizarEstadoReview(
                        idReview,
                        Enumeradores.EstadoReview.valueOf(estado)
                );

                response.sendRedirect(request.getContextPath() + "/AdministrativeServlet?accion=getResenas");
                break;
            }

            case "deleteReview": {
                String idReview = request.getParameter("idReview");
                new implementaciones.ReviewDAO().eliminarReview(idReview);

                response.sendRedirect(request.getContextPath() + "/AdministrativeServlet?accion=getResenas");
                break;
            }

            default:
                response.sendRedirect(request.getContextPath() + "/panelAdministrador.jsp");
        }
    }
}
