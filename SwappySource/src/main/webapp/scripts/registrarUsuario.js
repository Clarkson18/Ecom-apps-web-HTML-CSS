window.onload = function () {
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
      if (isInvalid) el.classList.add("invalid");
      else el.classList.remove("invalid");
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
      if (confirmPassword.value.length > 0) validatePasswords();
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
        confirmPassword: vConfirm,
      };

      try {
        const r = await fetch(
          "resources/clientes/registro",
          {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload),
          }
        );

        const data = await r.json().catch(() => ({}));

        if (r.ok && data && data.ok) {
          showAlert(
            data.message || "Cuenta creada. Ahora inicia sesión.",
            true
          );
          form.reset();
          hintPassword.style.display = "none";
          setTimeout(() => {
            window.location.href =
              "iniciarSesion.jsp";
          }, 800);
          return;
        }

        showAlert(
          data && data.message ? data.message : "No se pudo crear la cuenta.",
          false
        );
      } catch (err) {
        showAlert("Error de red. Intenta de nuevo.", false);
      }
    });
  })();
};
