function money(n) {
    return `$${Number(n || 0).toFixed(2)}`;
}

async function apiGet(url) {
    const r = await fetch(url, {credentials: "same-origin"});
    if (!r.ok)
        throw new Error(await r.text());
    return r.json();
}

async function apiPost(url, body) {
    const r = await fetch(url, {
        method: "POST",
        credentials: "same-origin",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body)
    });
    if (!r.ok)
        throw new Error(await r.text());
    return r.json();
}

function imageFor(item) {
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

    const cat = String(item.categoria || "").toUpperCase();
    const key = String(item.id || item.nombre || "x");

    function pick(arr, k) {
        let h = 0;
        for (let i = 0; i < k.length; i++)
            h = (h * 31 + k.charCodeAt(i)) >>> 0;
        return arr[h % arr.length];
    }

    if (cat.includes("VITAM"))
        return pick(IMG_VIT, key);
    if (cat.includes("PROTE"))
        return pick(IMG_PROT, key);
    return IMG_VIT[0];
}

function renderItems(container, items) {
    container.innerHTML = "";

    if (!items || items.length === 0) {
        container.innerHTML = `<p style="padding:18px; opacity:.75;">Tu carrito está vacío.</p>`;
        return;
    }

    for (const it of items) {
        const article = document.createElement("article");
        article.className = "item";

        const stock = Number(it.stock ?? 0);
        const qty = Number(it.cantidad ?? 1);

        article.innerHTML = `
      <div class="row">
        <div class="pic">
          <img src="${imageFor(it)}" alt="${it.nombre || "Producto"}">
        </div>

        <div>
          <h2 class="name">
            ${it.nombre || "Sin nombre"}
            <span class="avail">${stock > 0 ? "Disponible" : "Agotado"}</span>
          </h2>

          <div class="meta">
            <div class="qty" aria-label="Cantidad">
              <button type="button" class="btnMinus" data-id="${it.id}" aria-label="Restar">−</button>
              <span class="qtyVal" data-id="${it.id}">${qty}</span>
              <button type="button" class="btnPlus" data-id="${it.id}" aria-label="Sumar">+</button>
            </div>
            <a class="link-sim" href="previewProducto.jsp?id=${encodeURIComponent(it.id)}">Ver producto</a>
          </div>

          <div class="submeta">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#111">
              <path d="M3 9l1-5h16l1 5"/><path d="M3 9h18v10H3z"/><path d="M7 9v10M17 9v10"/>
            </svg>
            <span>Stock disponible: ${stock}</span>
          </div>
        </div>

        <div class="price">
          <svg class="trash btnTrash" data-id="${it.id}" viewBox="0 0 24 24" fill="none" stroke="#111" stroke-width="1.7">
            <path d="M3 6h18M8 6V4h8v2M6 6l1 14h10l1-14"/>
            <path d="M10 10v6M14 10v6"/>
          </svg>
          <div class="amount">${money(it.subtotal)}</div>
        </div>
      </div>
    `;

        container.appendChild(article);
    }
}

function actualizarResumen(data) {
    const items = data.items || [];
    const subtotal = Number(data.total || 0);

    const envio = items.length > 0 ? 120 : 0;
    const iva = items.length > 0 ? 120 : 0;
    const totalFinal = subtotal + envio + iva;

    const subtotalCount = document.getElementById("subtotalCount");
    const subtotalMonto = document.getElementById("subtotalMonto");
    const envioMonto = document.getElementById("envioMonto");
    const ivaMonto = document.getElementById("ivaMonto");
    const totalMonto = document.getElementById("totalMonto");

    if (subtotalCount)
        subtotalCount.textContent = String(items.length);
    if (subtotalMonto)
        subtotalMonto.textContent = money(subtotal);
    if (envioMonto)
        envioMonto.textContent = money(envio);
    if (ivaMonto)
        ivaMonto.textContent = money(iva);
    if (totalMonto)
        totalMonto.textContent = money(totalFinal);
}

async function cargarCarrito() {
    return apiGet(`${window.API_BASE}/carrito`);
}

async function updateCantidad(productoId, cantidad) {
    return apiPost(`${window.API_BASE}/carrito/update`, {productoId, cantidad});
}

async function removeItem(productoId) {
    return apiPost(`${window.API_BASE}/carrito/remove`, {productoId});
}

(async function init() {
    const cont = document.getElementById("carritoItems");
    const btnPagar = document.getElementById("btnPagar");

    async function refrescar() {
        const data = await cargarCarrito();
        renderItems(cont, data.items);
        actualizarResumen(data);
    }

    document.addEventListener("click", async (e) => {
        const plus = e.target.closest(".btnPlus");
        const minus = e.target.closest(".btnMinus");
        const trash = e.target.closest(".btnTrash");

        try {
            if (plus) {
                const id = plus.getAttribute("data-id");
                const span = document.querySelector(`.qtyVal[data-id="${CSS.escape(id)}"]`);
                const actual = Number(span?.textContent || 1);
                await updateCantidad(id, actual + 1);
                await refrescar();
            }

            if (minus) {
                const id = minus.getAttribute("data-id");
                const span = document.querySelector(`.qtyVal[data-id="${CSS.escape(id)}"]`);
                const actual = Number(span?.textContent || 1);
                await updateCantidad(id, actual - 1);
                await refrescar();
            }

            if (trash) {
                const id = trash.getAttribute("data-id");
                await removeItem(id);
                await refrescar();
            }
        } catch (err) {
            console.error(err);
            alert("No se pudo actualizar el carrito (stock o sesión).");
        }
    });

    if (btnPagar) {
        btnPagar.addEventListener("click", () => {
            alert("Pago aún no implementado.");
        });
    }

    await refrescar();
})().catch(err => {
    console.error(err);
    alert("No se pudo cargar el carrito.");
});
