<%-- 
    Document   : iniciarSesion
    Created on : 30 nov 2025, 15:05:48
    Author     : vv094, Abril Islas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <title>Happy Source | Iniciar sesión</title>
        <link rel="icon" type="image/png" sizes="32x32" href="./assets/logoHS.png">
        <link rel="stylesheet" href="./styles/styles_login.css">
        <script src="./scripts/iniciarSesion.js"></script>
    </head>

    <body>

        <header class="hs-topbar">
            <a class="brand" href="index.jsp">Happy Source</a>
            <div class="spacer"></div>
            <nav class="links">
                <a href="gestionarPedidos.jsp">Mis Pedidos</a>
                <a href="index.jsp">Inicio</a>
            </nav>
        </header>

        <main class="hs-login-wrap">
            <section class="hs-login-card">
                <div class="hs-login-header">
                    <h1>Iniciar sesión</h1>
                </div>
                <div class="hs-divider"></div>

                <div class="hs-login-content">

                    <!-- Icono usuario -->
                    <svg class="hs-user-icon" viewBox="0 0 24 24" aria-hidden="true">
                    <path fill="currentColor"
                          d="M12 12a4 4 0 1 0-4-4a4 4 0 0 0 4 4Zm0 2c-4.42 0-8 2.01-8 4.5V20h16v-1.5c0-2.49-3.58-4.5-8-4.5Z"/>
                    </svg>

                    <div id="msg" class="hs-msg"></div>

                    <!-- Form -->
                    <form id="formLogin" class="hs-form" autocomplete="off">
                        <div class="hs-row">
                            <label for="correo">Correo</label>
                            <input id="correo" name="correo" type="email" required>
                        </div>

                        <div class="hs-row">
                            <label for="password">Contraseña</label>
                            <input id="password" name="password" type="password" required>
                        </div>

                        <div class="hs-actions">
                            <button class="hs-btn" type="submit">Iniciar Sesión</button>
                        </div>

                        <div class="hs-links" style="margin-top: 10px; font-weight: 700; font-size: 13px;">
                            ¿No tienes una cuenta? <a href="registrarUsuario.jsp" style="color:#1f7a3f; font-weight: 900; text-decoration:none;">Regístrate</a>
                        </div>

                </div>
            </section>
        </main>
    </body>
</html>
