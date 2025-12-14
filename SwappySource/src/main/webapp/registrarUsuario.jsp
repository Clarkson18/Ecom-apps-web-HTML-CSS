<%-- 
    Document   : registrarUsuario
    Created on : Nov 13, 2025, 5:01:18 PM
    Author     : abrilislas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Happy Source | Registro</title>

        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/forms.css" />
        <script src="./scripts/registrarUsuario.js"></script>

        <style>
            .alerta {
                width: min(720px, 100%);
                margin: 0 auto 14px auto;
                padding: 10px 12px;
                border-radius: 10px;
                font-weight: 600;
                font-size: 14px;
                display: none;
            }
            .alerta.ok {
                background: rgba(34, 197, 94, .14);
                border: 1px solid rgba(34, 197, 94, .35);
                color: #0f5132;
            }
            .alerta.err {
                background: rgba(239, 68, 68, .12);
                border: 1px solid rgba(239, 68, 68, .30);
                color: #842029;
            }
            .invalid {
                border-color: rgba(239, 68, 68, .65) !important;
                outline: none !important;
                box-shadow: 0 0 0 3px rgba(239, 68, 68, .10) !important;
            }
            .hint {
                width: 100%;
                margin-top: 6px;
                font-size: 13px;
                font-weight: 600;
                color: rgba(239, 68, 68, .85);
                display: none;
            }
        </style>
    </head>

    <body class="registroBody">

        <main class="contenedor-principal">
            <h2 class="titulo-formulario">Crear cuenta</h2>
            <p class="subtitulo-formulario">Ingresa tus datos</p>

            <div id="alerta" class="alerta"></div>

            <form id="registroForm" novalidate autocomplete="off">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required />

                <label for="correo">Correo:</label>
                <input type="email" id="correo" name="correo" required />

                <label for="telefono">Teléfono:</label>
                <input type="text" id="telefono" name="telefono" inputmode="numeric" required />

                <label for="direccion">Dirección:</label>
                <input type="text" id="direccion" name="direccion" required />

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required />

                <label for="confirmPassword">Confirmar contraseña:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required />
                <div id="hintPassword" class="hint">Las contraseñas no coinciden.</div>

                <button type="submit" class="boton">Registrarme</button>
            </form>

            <p class="texto-secundario">
                ¿Ya tienes una cuenta? <a href="<%=request.getContextPath()%>/iniciarSesion.jsp">Inicia sesión aquí</a>
            </p>
        </main>
    </body>
</html>


