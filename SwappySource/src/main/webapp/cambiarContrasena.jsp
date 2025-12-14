<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="fragmentos/header.jspf" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Cambiar contraseña</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/styles/perfilCliente.css">
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
                    <h1>Cambiar contraseña</h1>
                </div>

                <form action="<%=request.getContextPath()%>/CambiarContrasenaServlet" method="post">
                    <input type="hidden" name="accion" value="cambiarPassword">

                    <div class="form-group">
                        <label>Contraseña actual</label>
                        <input type="password" name="passwordActual" required>
                    </div>

                    <div class="form-group">
                        <label>Nueva contraseña</label>
                        <input type="password" name="passwordNueva" required>
                    </div>

                    <div class="form-group">
                        <label>Confirmar contraseña</label>
                        <input type="password" name="confirmPassword" required>
                    </div>

                    <div class="button-row">
                        <a href="perfil.jsp" class="btn-secondary">Cancelar</a>
                        <button type="submit" class="btn-primary">Cambiar contraseña</button>
                    </div>
                </form>
            </section>
        </main>

        <%@include file="fragmentos/footer.jspf" %>
    </body>
</html>
