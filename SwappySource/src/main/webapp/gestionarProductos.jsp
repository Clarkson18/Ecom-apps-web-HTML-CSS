<%-- 
    Document   : gestionarProductos
    Created on : 13 dic 2025, 12:23:27 p.m.
    Author     : h
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Admin | Productos</title>
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
                <h1 class="admin-title">Gestión de productos</h1>
                <p class="admin-subtitle">Agrega productos y administra el catálogo.</p>

                <!-- Form agregar producto -->
                <div class="form-card">
                    <form action="<%= request.getContextPath()%>/AdministrativeServlet" method="post">
                        <c:choose> 
                            <c:when test="${productoEdit != null}">     
                                <input type="hidden" name="accion" value="updateProducto"/>     
                                <input type="hidden" name="idProducto" value="${productoEdit.id}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="accion" value="addProducto"/>
                            </c:otherwise>
                        </c:choose>

                        <div class="form-grid">
                            <div class="form-field">
                                <label>Nombre</label>
                                <input name="nombre" type="text" required value="${productoEdit.nombre}">
                            </div>

                            <div class="form-field">
                                <label>Descripción</label>
                                <input name="descripcionProducto" type="text" required value="${productoEdit.descripcionProducto}">
                            </div>

                            <div class="form-field">
                                <label>Precio</label>
                                <input name="precio" type="number" step="0.01" required value="${productoEdit.precio}">
                            </div>

                            <div class="form-field">
                                <label>Cantidad</label>
                                <input name="cantidadExistencia" type="number" required value="${productoEdit.cantidadExistencia}">
                            </div>

                            <div class="form-field">
                                <label>Categoría</label>
                                <select name="categoria" required>
                                    <c:forEach var="cat" items="${categorias}">
                                        <option value="${cat}" <c:if test="${productoEdit != null && cat == productoEdit.categoria}">selected</c:if>>${cat}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-field">
                            <label>Imagen</label>
                            <select name="imagen" required>
                                <option value="suplemento1.jpeg" <c:if test="${productoEdit != null && productoEdit.imagen == 'suplemento1.jpeg'}">selected</c:if>>suplemento1.jpeg</option>
                                <option value="suplemento2.jpeg" <c:if test="${productoEdit != null && productoEdit.imagen == 'suplemento2.jpeg'}">selected</c:if>>suplemento2.jpeg</option>
                                <option value="suplemento3.jpeg" <c:if test="${productoEdit != null && productoEdit.imagen == 'suplemento3.jpeg'}">selected</c:if>>suplemento3.jpeg</option>
                                <option value="gomitas.jpeg"     <c:if test="${productoEdit != null && productoEdit.imagen == 'gomitas.jpeg'}">selected</c:if>>gomitas.jpeg</option>
                                option value="gomitas1.jpeg"    <c:if test="${productoEdit != null && productoEdit.imagen == 'gomitas1.jpeg'}">selected</c:if>>gomitas1.jpeg</option>
                                <option value="gomitas2.jpeg"    <c:if test="${productoEdit != null && productoEdit.imagen == 'gomitas2.jpeg'}">selected</c:if>>gomitas2.jpeg</option>
                                </select>
                            </div>

                            <div class="form-actions">
                                <button class="small-btn" type="submit">Guardar</button>
                            <c:choose>
                                <c:when test="${productoEdit != null}">Actualizar</c:when>
                                <c:otherwise>Guardar</c:otherwise>
                            </c:choose>
                            </button>  
                            <c:if test="${productoEdit != null}">
                                <a class="small-btn" style="background:#e5e7eb;color:#111;text-decoration:none;margin-left:8px;"
                                   href="<%= request.getContextPath()%>/AdministrativeServlet?accion=getProductos">Cancelar</a>
                            </c:if>
                        </div>
                    </form>
                </div>

                <!-- Tabla productos -->
                <div class="tablaAdminWrapper">
                    <table class="tabla-admin">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th>Cantidad</th>
                                <th>Categoría</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${productos}">
                                <tr>
                                    <td>${p.id}</td>
                                    <td>${p.nombre}</td>
                                    <td>${p.precio}</td>
                                    <td>${p.cantidadExistencia}</td>
                                    <td>${p.categoria}</td>
                                    <td>
                                        <div class="row-actions">
                                            <a class="table-btn" style="background:#F2D23C;color:#111;text-decoration:none;margin-right:8px;"
                                               href="<%= request.getContextPath()%>/AdministrativeServlet?accion=editProducto&idProducto=${p.id}">
                                                Editar
                                            </a>
                                            <form action="<%= request.getContextPath()%>/AdministrativeServlet" method="post">
                                                <input type="hidden" name="accion" value="deleteProducto"/>
                                                <input type="hidden" name="idProducto" value="${p.id}"/>
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

