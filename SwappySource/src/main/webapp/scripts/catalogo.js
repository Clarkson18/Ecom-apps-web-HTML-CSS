/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function money(n) {
    return `$${Number(n).toFixed(2)}`;
}

async function apiGet(url) {
    const r = await fetch(url, {credentials: "same-origin"});
    if (!r.ok)
        throw new Error(await r.text());
    return r.json();
}

function debounce(fn, ms = 350) {
    let t;
    return (...args) => {
        clearTimeout(t);
        t = setTimeout(() => fn(...args), ms);
    };
}

function renderCatalogo(root, productos) {
    const grupos = new Map();
    for (const p of productos) {
        const c = (p.categoria && p.categoria.trim()) ? p.categoria : "OTROS";
        if (!grupos.has(c))
            grupos.set(c, []);
        grupos.get(c).push(p);
    }

    root.innerHTML = "";

    for (const [cat, items] of grupos.entries()) {
        const section = document.createElement("section");
        section.className = "categoria";

        const h2 = document.createElement("h2");
        h2.textContent = cat;
        section.appendChild(h2);

        const div = document.createElement("div");
        div.className = "productos";

        for (const p of items) {
            const card = document.createElement("div");
            card.className = "producto";

            const a = document.createElement("a");
            a.className = "displayProducto";
            a.href = `previewProducto.jsp?id=${encodeURIComponent(p.id)}`;

            const img = document.createElement("img");
            img.alt = p.nombre || "producto";
            img.src = window.imageFor ? window.imageFor(p) : "";
            a.appendChild(img);

            const h3 = document.createElement("h3");
            h3.textContent = p.nombre || "Sin nombre";

            const precio = document.createElement("p");
            precio.className = "precio";
            precio.textContent = money(p.precio);

            const disp = document.createElement("p");
            disp.className = (p.stock > 0) ? "disponible" : "agotado";
            disp.textContent = (p.stock > 0) ? "Disponible" : "Agotado";

            card.appendChild(a);
            card.appendChild(h3);
            card.appendChild(precio);
            card.appendChild(disp);
            div.appendChild(card);
        }

        section.appendChild(div);
        root.appendChild(section);
    }
}

(async function init() {
    const root = document.getElementById("catalogoRoot");
    const productos = await apiGet(`${window.API_BASE}/productos`);
    const searchInput = document.getElementById("searchInput");
    const categoriaSelect = document.getElementById("categoriaSelect");
    const btnLimpiar = document.getElementById("btnLimpiar");
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

    function pickDeterministic(arr, key) {
        let h = 0;
        for (let i = 0; i < key.length; i++)
            h = (h * 31 + key.charCodeAt(i)) >>> 0;
        return arr[h % arr.length];
    }

    function imageFor(p) {
        const cat = (p.categoria || "").toUpperCase();
        const key = (p.id || p.nombre || "x");

        if (cat.includes("VITAM"))
            return pickDeterministic(IMG_VIT, key);
        if (cat.includes("PROTE"))
            return pickDeterministic(IMG_PROT, key);

        return IMG_VIT[0]; // fallback
    }
    window.imageFor = imageFor;
    
    async function cargarCatalogo() {
        const search = (searchInput?.value || "").trim();
        const categoria = (categoriaSelect?.value || "").trim();

        const params = new URLSearchParams();
        if (search) params.set("search", search);
        if (categoria) params.set("categoria", categoria);

        const url = `${window.API_BASE}/productos${params.toString() ? "?" + params.toString() : ""}`;
        const productos = await apiGet(url);

        renderCatalogo(root, productos);
    }
    const recargarDebounced = debounce(() => {
        cargarCatalogo().catch(err => {
            console.error(err);
            alert("No se pudo cargar el catálogo.");
        });
    }, 350);
    if (searchInput) searchInput.addEventListener("input", recargarDebounced);
    if (categoriaSelect) categoriaSelect.addEventListener("change", recargarDebounced);

    if (btnLimpiar) {
        btnLimpiar.addEventListener("click", () => {
            if (searchInput) searchInput.value = "";
            if (categoriaSelect) categoriaSelect.value = "";
            cargarCatalogo().catch(err => {
                console.error(err);
                alert("No se pudo cargar el catálogo.");
            });
        });
    }
    await cargarCatalogo();
})().catch(err => {
    console.error(err);
    alert("No se pudo cargar el catálogo.");
});


