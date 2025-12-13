<%-- 
    Document   : administrarUsuario
    Created on : Nov 11, 2025, 10:22:50 AM
    Author     : abrilislas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Admin | Usuarios</title>
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
                <h1 class="admin-title">Administración de usuarios</h1>
                <p class="admin-subtitle">Cambia roles o elimina usuarios.</p>

                <div class="tablaAdminWrapper">
                    <table class="tabla-admin">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Correo</th>
                                <th>Rol</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="u" items="${usuarios}">
                                <tr>
                                    <td>${u.nombre}</td>
                                    <td>${u.correo}</td>
                                    <td>${u.rol}</td>
                                    <td>
                                        <div class="row-actions">
                                            <form action="<%= request.getContextPath()%>/AdministrativeServlet" method="post">
                                                <input type="hidden" name="accion" value="deleteUsuario"/>
                                                <input type="hidden" name="correo" value="${u.correo}"/>
                                                <button class="table-btn table-btn-danger" type="submit">Eliminar</button>
                                            </form>

                                            <form action="<%= request.getContextPath()%>/AdministrativeServlet" method="post">
                                                <input type="hidden" name="accion" value="updateRolUsuario"/>
                                                <input type="hidden" name="correo" value="${u.correo}"/>
                                                <select class="table-select" name="rol">
                                                    <option ${u.rol == 'CLIENTE' ? 'selected' : ''}>CLIENTE</option>
                                                    <option ${u.rol == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                                                </select>
                                                <button class="table-btn" type="submit">Cambiar rol</button>
                                            </form>
                                        </div>
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



