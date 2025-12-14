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

        <script>
            (function () {
                const form = document.getElementById("registroForm");
                const alerta = document.getElementById("alerta");
                const hintPassword = document.getElementById("hintPassword");

                const nombre = document.getElementById("nombre");
                const correo = document.getElementById("correo");
                const telefono = document.getElementById("telefono");
                const direccion = document.getElementById("direccion");
                const password = document.getElementById("password");
                const confirmPassword = document.getElementById("confirmPassword");

                function showAlert(text, ok) {
                    alerta.textContent = text;
                    alerta.classList.remove("ok", "err");
                    alerta.classList.add(ok ? "ok" : "err");
                    alerta.style.display = "block";
                }

                function clearAlert() {
                    alerta.style.display = "none";
                    alerta.textContent = "";
                    alerta.classList.remove("ok", "err");
                }

                function setInvalid(el, isInvalid) {
                    if (isInvalid)
                        el.classList.add("invalid");
                    else
                        el.classList.remove("invalid");
                }

                function normalizePhone(value) {
                    return (value || "").replace(/[^\d]/g, "");
                }

                function validatePasswords() {
                    const p1 = password.value || "";
                    const p2 = confirmPassword.value || "";
                    const ok = p1.length > 0 && p1 === p2;
                    hintPassword.style.display = ok ? "none" : "block";
                    setInvalid(confirmPassword, !ok);
                    return ok;
                }

                confirmPassword.addEventListener("input", validatePasswords);
                password.addEventListener("input", () => {
                    if (confirmPassword.value.length > 0)
                        validatePasswords();
                });

                telefono.addEventListener("input", () => {
                    telefono.value = normalizePhone(telefono.value);
                });

                form.addEventListener("submit", async function (e) {
                    e.preventDefault();
                    clearAlert();

                    const vNombre = (nombre.value || "").trim();
                    const vCorreo = (correo.value || "").trim();
                    const vTelefono = normalizePhone(telefono.value);
                    const vDireccion = (direccion.value || "").trim();
                    const vPassword = password.value || "";
                    const vConfirm = confirmPassword.value || "";

                    setInvalid(nombre, vNombre.length === 0);
                    setInvalid(correo, vCorreo.length === 0);
                    setInvalid(telefono, vTelefono.length === 0);
                    setInvalid(direccion, vDireccion.length === 0);
                    setInvalid(password, vPassword.length === 0);

                    if (!vNombre || !vCorreo || !vTelefono || !vDireccion || !vPassword) {
                        showAlert("Completa todos los campos.", false);
                        return;
                    }

                    if (!validatePasswords()) {
                        showAlert("Las contraseñas no coinciden.", false);
                        return;
                    }

                    const payload = {
                        nombre: vNombre,
                        correo: vCorreo,
                        telefono: vTelefono,
                        direccion: vDireccion,
                        password: vPassword,
                        confirmPassword: vConfirm
                    };

                    try {
                        const r = await fetch("<%=request.getContextPath()%>/resources/clientes/registro", {
                            method: "POST",
                            headers: {"Content-Type": "application/json"},
                            body: JSON.stringify(payload)
                        });

                        const data = await r.json().catch(() => ({}));

                        if (r.ok && data && data.ok) {
                            showAlert(data.message || "Cuenta creada. Ahora inicia sesión.", true);
                            form.reset();
                            hintPassword.style.display = "none";
                            setTimeout(() => {
                                window.location.href = "<%=request.getContextPath()%>/iniciarSesion.jsp";
                            }, 800);
                            return;
                        }

                        showAlert((data && data.message) ? data.message : "No se pudo crear la cuenta.", false);
                    } catch (err) {
                        showAlert("Error de red. Intenta de nuevo.", false);
                    }
                });
            })();
        </script>

    </body>
</html>


