function money(n) {
  return `$${Number(n || 0).toFixed(2)}`;
}

async function apiGet(url) {
  const r = await fetch(url, { credentials: "same-origin" });
  if (!r.ok) throw new Error(await r.text());
  return r.json();
}

async function apiPost(url, body) {
  const r = await fetch(url, {
    method: "POST",
    credentials: "same-origin",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body)
  });
  if (!r.ok) throw new Error(await r.text());
  return r.json();
}

function pickDeterministic(arr, key) {
  let h = 0;
  for (let i = 0; i < key.length; i++) h = (h * 31 + key.charCodeAt(i)) >>> 0;
  return arr[h % arr.length];
}

function imageFor(p) {
  const IMG_VIT = [
    "https://media.ulta.com/i/ulta/2550466?w=800&h=800&fmt=auto",
    "https://ethical-nutrition.com/cdn/shop/files/veganvitamind3supplementuk.png?v=1746003137",
    "https://www.naturewise.com/cdn/shop/files/Vegan_Vitamin_K2_-_180CT_-_PDP_-_Front.jpg?v=1754514438&width=3000"
  ];
  const IMG_PROT = [
    "https://freesoul.com/cdn/shop/articles/Protein_Reformulation_e957be15-e9ed-4b48-8afa-907731251fab.jpg?v=1752416671&width=684",
    "https://www.bulk.com/media/catalog/product/B/B/BBLE_CDPA_CHOC_Main_Image_872c.jpg",
    "https://theforestsuperfood.com/cdn/shop/files/p2_2ef262d5-ffb8-430b-a721-271dd8b9a0b5.png?v=1741693772&width=2000"
  ];

  const cat = (p.categoria || "").toUpperCase();
  const key = (p.id || p.nombre || "x");

  if (cat.includes("VITAM")) return pickDeterministic(IMG_VIT, key);
  if (cat.includes("PROTE")) return pickDeterministic(IMG_PROT, key);
  return IMG_VIT[0];
}

function tituloCategoria(cat) {
  const c = (cat || "").toUpperCase();
  if (c.includes("PROTE")) return "Proteína en polvo";
  if (c.includes("VITAM")) return "Vitaminas y probióticos";
  return cat || "";
}

function renderReviews(listEl, reviews) {
  listEl.innerHTML = "";

  if (!reviews || reviews.length === 0) {
    const p = document.createElement("p");
    p.textContent = "Aún no hay reseñas.";
    listEl.appendChild(p);
    return;
  }

  for (const r of reviews) {
    const wrap = document.createElement("p");

    const name = (r.correoUsuario || "Usuario").split("@")[0];
    const stars = "★".repeat(Number(r.calificacion || 0)) + "☆".repeat(5 - Number(r.calificacion || 0));

    wrap.innerHTML = `<strong>${name}:</strong> ${r.comentario} <span style="opacity:.7">(${stars})</span>`;

    // si viene estado (por ejemplo tu reseña pendiente), lo mostramos sutil
    if (r.estado && r.estado !== "APROBADA") {
      const s = document.createElement("span");
      s.style.marginLeft = "8px";
      s.style.fontSize = "12px";
      s.style.opacity = ".7";
      s.textContent = `[${r.estado}]`;
      wrap.appendChild(s);
    }

    listEl.appendChild(wrap);
  }
}

(async function init() {
  const params = new URLSearchParams(location.search);
  const id = params.get("id");
  if (!id) {
    alert("Producto inválido (sin id).");
    return;
  }

  // PRODUCTO 
  const p = await apiGet(`${window.API_BASE}/productos/${encodeURIComponent(id)}`);

  const elCat = document.getElementById("previewCategoria");
  const elImg = document.getElementById("previewImg");
  const elNombre = document.getElementById("previewNombre");
  const elDesc = document.getElementById("previewDescripcion");
  const elPrecio = document.getElementById("previewPrecio");
  const elStock = document.getElementById("previewStock");
  const btnComprar = document.getElementById("btnComprar");
  const btnCarrito = document.getElementById("btnAgregarCarrito");
  
    if (btnCarrito) {
        btnCarrito.addEventListener("click", async () => {
            try {
                await apiPost(`${window.API_BASE}/carrito/add`, {productoId: id, cantidad: 1});
                alert("Producto agregado al carrito.");
            } catch (e) {
                console.error(e);
                alert("No se pudo agregar al carrito. Revisa sesión/stock.");
            }
        });
    }

    if (btnComprar) {
        btnComprar.addEventListener("click", async () => {
            try {
                await apiPost(`${window.API_BASE}/carrito/add`, {productoId: id, cantidad: 1});
                location.href = `${window.CTX}/carrito.jsp`;
            } catch (e) {
                console.error(e);
                alert("No se pudo comprar ahora. Revisa sesión/stock.");
            }
        });
    }

  if (elCat) elCat.textContent = tituloCategoria(p.categoria);
  if (elImg) { elImg.src = imageFor(p); elImg.alt = p.nombre || "producto"; }
  if (elNombre) elNombre.textContent = p.nombre || "";
  if (elDesc) elDesc.textContent = p.descripcion || "";
  if (elPrecio) elPrecio.textContent = money(p.precio);
  if (elStock) elStock.textContent = String(p.stock ?? 0);

  const stock = Number(p.stock ?? 0);
  if (btnComprar) btnComprar.disabled = stock <= 0;
  if (btnCarrito) btnCarrito.disabled = stock <= 0;

  // RESEÑAS 
  const reviewsList = document.getElementById("reviewsList");
  const reviewForm = document.getElementById("reviewForm");
  const reviewLoginMsg = document.getElementById("reviewLoginMsg");
  const reviewText = document.getElementById("reviewText");
  const reviewRating = document.getElementById("reviewRating");

  async function cargarReviews() {
    const reviews = await apiGet(`${window.API_BASE}/reviews?productoId=${encodeURIComponent(id)}`);
    if (reviewsList) renderReviews(reviewsList, reviews);
  }

  // Mostrar form solo si el POST deja (si no, lo ocultamos y mostramos msg)
  // (simple y robusto: probamos publicar; pero mejor: hacemos un GET de sesión con error handling)
  if (reviewForm) reviewForm.style.display = "block";
  if (reviewLoginMsg) reviewLoginMsg.style.display = "none";

  if (reviewForm) {
    reviewForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      try {
        const comentario = (reviewText?.value || "").trim();
        const calificacion = Number(reviewRating?.value || 5);

        const created = await apiPost(`${window.API_BASE}/reviews`, {
          productoId: id,
          comentario,
          calificacion
        });

        if (reviewText) reviewText.value = "";
        await cargarReviews(); // refresca para verlo
      } catch (err) {
        console.error(err);

        // si no está logueado como cliente
        if (reviewForm) reviewForm.style.display = "none";
        if (reviewLoginMsg) reviewLoginMsg.style.display = "block";
        alert("No se pudo publicar la reseña. Asegúrate de iniciar sesión como cliente.");
      }
    });
  }

  await cargarReviews();
})().catch(err => {
  console.error(err);
  alert("No se pudo cargar el producto.");
});
