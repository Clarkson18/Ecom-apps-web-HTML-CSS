<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.List"%>
<%@include file="fragmentos/header.jspf" %>

<%
    Object usuarioObj = session.getAttribute("usuarioLogueado");
    if (usuarioObj == null) {
        usuarioObj = session.getAttribute("usuario");
    }
    if (usuarioObj == null) {
        usuarioObj = session.getAttribute("cliente");
    }

    String nombre = "", correo = "", telefono = "", direccion = "";
    try {
        Method getNombre = usuarioObj.getClass().getMethod("getNombre");
        Method getCorreo = usuarioObj.getClass().getMethod("getCorreo");
        Method getTelefono = usuarioObj.getClass().getMethod("getTelefono");
        Method getDirecciones = usuarioObj.getClass().getMethod("getDirecciones");

        nombre = String.valueOf(getNombre.invoke(usuarioObj));
        correo = String.valueOf(getCorreo.invoke(usuarioObj));
        telefono = String.valueOf(getTelefono.invoke(usuarioObj));

        Object dirsObj = getDirecciones.invoke(usuarioObj);
        if (dirsObj instanceof List) {
            List dirs = (List) dirsObj;
            if (!dirs.isEmpty()) {
                direccion = String.valueOf(dirs.get(0));
            }
        }
    } catch (Exception e) {
    }
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Mi perfil</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/perfilCliente.css">
    </head>

    <body>
        <main class="perfil-container">
            <section class="perfil-card">
                <div class="perfil-header">
                    <div class="hs-avatar" aria-hidden="true">
                        <svg class="hs-user-icon" viewBox="0 0 24 24">
                        <path fill="currentColor"
                              d="M12 12a4 4 0 1 0-4-4a4 4 0 0 0 4 4Zm0 2c-4.42 0-8 2.01-8 4.5V20h16v-1.5c0-2.49-3.58-4.5-8-4.5Z"/>
                        </svg>
                    </div>
                    <h1>Mi Perfil</h1>
                </div>

                <form action="<%=request.getContextPath()%>/PerfilServlet" method="post">
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" name="nombre" value="<%= nombre%>" required>
                    </div>

                    <div class="form-group">
                        <label>Dirección</label>
                        <input type="text" name="direccion" value="<%= direccion%>" required>
                    </div>

                    <div class="form-group">
                        <label>Correo</label>
                        <input type="email" name="correo" value="<%= correo%>" required>
                    </div>

                    <div class="form-group">
                        <label>Teléfono</label>
                        <input type="text" name="telefono" value="<%= telefono%>" required>
                    </div>

                    <div class="button-row">
                        <a href="LogoutServlet" class="btn-secondary">Cerrar Sesión</a>
                        <button type="submit" class="btn-primary">Guardar</button>
                    </div>

                    <div class="extra-row">
                        <a href="cambiarContrasena.jsp" class="btn-link">Cambiar contraseña</a>
                    </div>
                </form>
            </section>
        </main>

        <%@include file="fragmentos/footer.jspf" %>
    </body>
</html>


