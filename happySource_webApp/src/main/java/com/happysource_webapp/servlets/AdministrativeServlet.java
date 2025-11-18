/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.happysource_webapp.servlets;

import BusinessObject.UsuarioBO;
import BusinessObjects.PedidoBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author abrilislas
 */
@WebServlet(name = "AdministrativeServlet", urlPatterns = {"/AdministrativeServlet"})
public class AdministrativeServlet extends HttpServlet {

    private UsuarioBO usuarioBO = new UsuarioBO();
    private PedidoBO pedidoBO = new PedidoBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendRedirect("Views/admin/dashboard.jsp");
            return;
        }

        switch (accion) {

            case "getUsuarios":
                request.setAttribute("usuarios", usuarioBO.consultarUsuarios());
                request.getRequestDispatcher("Views/admin/consultarUsuarios.jsp")
                       .forward(request, response);
                break;

            case "updateUsuario":
                request.getRequestDispatcher("Views/admin/actualizarUsuario.jsp")
                       .forward(request, response);
                break;

            case "deleteUsuario":
                request.getRequestDispatcher("Views/admin/eliminarUsuario.jsp")
                       .forward(request, response);
                break;

            case "getPedidos":
                request.setAttribute("pedidos", pedidoBO.consultarPedidos());
                request.getRequestDispatcher("Views/admin/pedidos.jsp")
                       .forward(request, response);
                break;

            default:
                response.sendRedirect("Views/admin/dashboard.jsp");
        }
    }
}
