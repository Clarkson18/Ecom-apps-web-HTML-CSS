<%-- 
    Document   : iniciarSesion
    Created on : 30 nov 2025, 15:05:48
    Author     : vv094, Abril Islas
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title>Happy Source | Iniciar sesión</title>
        <link rel="stylesheet" href="./styles/forms.css">
    </head>

    <body class="iniciarSesion">

        <div class="contenedor-principal">
            <div class="contenido">
                <h2>Iniciar sesión</h2>
                <h4>Ingresa tus credenciales</h4>

                <div id="msg" style="display:none; margin: 10px 0; padding: 10px 12px; border-radius: 10px; font-weight: 700;"></div>

                <form id="formLogin">
                    <label for="correo-electronico">Correo: </label>
                    <input id="correo-electronico" name="correo" class="correo-electronico" type="text" placeholder="correo@dominio.com" required autocomplete="off"/>

                    <label for="passwordUsuario">Contraseña: </label>
                    <input id="passwordUsuario" name="password" type="password" required autocomplete="off"/>

                    <a href="cambiar_password">¿Has olvidado tu contraseña?</a>

                    <button type="submit" class="botones">Iniciar sesión</button>
                </form>

                <h4>¿No tienes una cuenta? <a href="registrarUsuario.jsp">Crea una aquí</a></h4>
            </div>
        </div>

        <script>
            (() => {
                const form = document.getElementById("formLogin");
                const msg = document.getElementById("msg");

                const showMsg = (text, ok) => {
                    msg.style.display = "block";
                    msg.textContent = text;
                    msg.style.background = ok ? "#E2FFE2" : "#FFE2E2";
                    msg.style.border = ok ? "1px solid #6CB987" : "1px solid #D90346";
                    msg.style.color = "#111";
                };

                form.addEventListener("submit", async (e) => {
                    e.preventDefault();

                    const correo = document.getElementById("correo-electronico").value.trim();
                    const password = document.getElementById("passwordUsuario").value;

                    if (!correo || !password) {
                        showMsg("Correo y contraseña son obligatorios.", false);
                        return;
                    }

                    try {
                        const res = await fetch("resources/clientes/login", {
                            method: "POST",
                            headers: {"Content-Type": "application/json"},
                            body: JSON.stringify({correo, password})
                        });

                        const data = await res.json().catch(() => ({}));

                        if (!res.ok || data.ok !== true) {
                            showMsg(data.message || "No se pudo iniciar sesión.", false);
                            return;
                        }

                        showMsg("Sesión iniciada. Redirigiendo...", true);
                        setTimeout(() => window.location.href = data.redirect, 450);

                    } catch (err) {
                        showMsg("Error de conexión. Intenta de nuevo.", false);
                    }
                });
            })();
        </script>

    </body>
</html>