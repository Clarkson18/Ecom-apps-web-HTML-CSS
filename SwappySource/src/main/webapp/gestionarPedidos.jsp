<%-- 
    Document   : gestionarPedidos
    Description: Vista principal para la gestión de pedidos del administrador
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Admin | Pedidos</title>
        <link rel="stylesheet" href="styles/administradorStyles.css">
    </head>
    <body>

        <nav class="header">
            <h2 id="logo">
                <a href="panelAdministrador.jsp" class="mainLink">Happy Source</a>
                <span class="adminTag">Admin panel</span>
            </h2>
            <div class="headerActions">
                <a class="logoutBtn" href="<%= request.getContextPath()%>/LogoutServlet">Cerrar sesión</a>
            </div>
        </nav>

        <div class="admin-container">
            <div class="admin-card">
                <h1 class="admin-title">Gestión de pedidos</h1>
                <p class="admin-subtitle">Administra los pedidos realizados por los clientes.</p>

                <div class="tablaAdminWrapper">
                    <table class="tabla-admin">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Usuario</th>
                                <th>Fecha</th>
                                <th>Total</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${pedidos}">
                                <tr>
                                    <td>${p.id}</td>
                                    <td>${p.usuarioCorreo}</td>
                                    <td>${p.fecha}</td>
                                    <td>$${p.total}</td>
                                    <td>${p.estado}</td>
                                    <td>
                                        <form action="<%= request.getContextPath()%>/AdministrativeServlet" method="post">
                                            <input type="hidden" name="accion" value="updateEstadoPedido"/>
                                            <input type="hidden" name="idPedido" value="${p.id}"/>
                                            <select class="table-select" name="estado">
                                                <option ${p.estado == 'PENDIENTE' ? 'selected' : ''}>PENDIENTE</option>
                                                <option ${p.estado == 'ENVIADO' ? 'selected' : ''}>ENVIADO</option>
                                                <option ${p.estado == 'ENTREGADO' ? 'selected' : ''}>ENTREGADO</option>
                                            </select>
                                            <button class="table-btn" type="submit">Actualizar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <a class="adminBack" href="panelAdministrador.jsp">← Volver</a>
            </div>
        </div>

    </body>
</html>

