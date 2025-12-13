<%-- 
    Document   : gestionarResenas
    Author     : h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Admin | Reseñas</title>
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
                <h1 class="admin-title">Moderación de reseñas</h1>
                <p class="admin-subtitle">Aprueba, rechaza o elimina reseñas.</p>

                <div class="tablaAdminWrapper">
                    <table class="tabla-admin">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Producto ID</th>
                                <th>Usuario</th>
                                <th>Calificación</th>
                                <th>Comentario</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="r" items="${reviews}">
                                <tr>
                                    <td>${r.id}</td>
                                    <td>${r.productoId}</td>
                                    <td>${r.correoUsuario}</td>
                                    <td>${r.calificacion}</td>
                                    <td>${r.comentario}</td>
                                    <td>${r.estado}</td>
                                    <td>
                                        <div class="row-actions">
                                            <form action="<%= request.getContextPath()%>/AdministrativeServlet" method="post">
                                                <input type="hidden" name="accion" value="updateEstadoReview"/>
                                                <input type="hidden" name="idReview" value="${r.id}"/>
                                                <select class="table-select" name="estado">
                                                    <option ${r.estado == 'PENDIENTE' ? 'selected' : ''}>PENDIENTE</option>
                                                    <option ${r.estado == 'APROBADA' ? 'selected' : ''}>APROBADA</option>
                                                    <option ${r.estado == 'RECHAZADA' ? 'selected' : ''}>RECHAZADA</option>
                                                </select>
                                                <button class="table-btn" type="submit">Actualizar</button>
                                            </form>

                                            <form action="<%= request.getContextPath()%>/AdministrativeServlet" method="post">
                                                <input type="hidden" name="accion" value="deleteReview"/>
                                                <input type="hidden" name="idReview" value="${r.id}"/>
                                                <button class="table-btn table-btn-danger" type="submit">Eliminar</button>
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



