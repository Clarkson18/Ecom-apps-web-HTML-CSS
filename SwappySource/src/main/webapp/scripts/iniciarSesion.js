window.onload = function () {
  (function () {
    const form = document.getElementById("formLogin");
    const msg = document.getElementById("msg");

    function showMsg(text, ok) {
      msg.textContent = text;
      msg.className = "hs-msg " + (ok ? "ok" : "error");
    }

    form.addEventListener("submit", async (e) => {
      e.preventDefault();

      const correo = document.getElementById("correo").value.trim();
      const password = document.getElementById("password").value;

      try {
        const res = await fetch("resources/clientes/login", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ correo, password }),
        });

        const data = await res.json().catch(() => ({}));

        if (!res.ok || data.ok !== true) {
          showMsg(data.message || "No se pudo iniciar sesión.", false);
          return;
        }

        showMsg("Sesión iniciada. Redirigiendo...", true);
        const redirect = data.redirect || "catalogo.jsp";
        setTimeout(() => {
          window.location.href = redirect;
        }, 250);
      } catch (err) {
        showMsg("Error de conexión. Intenta de nuevo.", false);
      }
    });
  })();
};
