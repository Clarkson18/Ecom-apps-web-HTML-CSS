<%-- 
    Document   : panelAdministrador
    Created on : Nov 18, 2025, 10:10:40 AM
    Author     : abrilislas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Happy Source | Admin</title>
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
                <h1 class="admin-title">Administración</h1>
                <p class="admin-subtitle">Selecciona un módulo</p>

                <form action="<%= request.getContextPath()%>/AdministrativeServlet" method="get" class="botonesContainer">
                    <button class="admin-btn" type="submit" name="accion" value="getUsuarios">Gestionar usuarios</button>
                    <button class="admin-btn" type="submit" name="accion" value="getProductos">Gestionar productos</button>
                    <button class="admin-btn" type="submit" name="accion" value="getPedidos">Gestionar pedidos</button>
                    <button class="admin-btn" type="submit" name="accion" value="getResenas">Gestionar reseñas</button>
                </form>
            </div>
        </div>

    </body>
</html>


