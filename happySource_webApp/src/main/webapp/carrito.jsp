<%-- 
    Document   : carrito
    Created on : Nov 11, 2025, 10:43:27 AM
    Author     : abrilislas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Happy Source | Carrito de Compras</title>
<link rel="stylesheet" href="../styles/styles.css">
<link rel="icon" type="image/png" sizes="32x32" href="../assets/logoHS.png">
<style>
  :root{
    --brand:#16a34a; --header:#EAF9E6; --header-border:#CDEFC5;
    --panel:#F1F2F4; --panel-soft:#F6F7F8;
    --text:#0b0f10; --muted:#6b7280; --ok:#28a745;
    --price:#3B82F6; --accent:#F2D23C; /* botón amarillo */
    --container: 1180px; --radius: 18px;
    --space-2:.5rem; --space-3:.75rem; --space-4:1rem; --space-5:1.25rem; --space-6:1.5rem; --space-8:2rem;
  }
  *{box-sizing:border-box} html,body{margin:0;padding:0}
  body{font-family:system-ui,-apple-system,"Segoe UI",Roboto,Arial; color:var(--text); background:#fff; line-height:1.45}

  /* Header */
  .header{position:sticky;top:0;background:var(--header); border-bottom:1px solid var(--header-border); z-index:20}
  .bar{width:min(var(--container),100% - 2rem); margin-inline:auto; display:flex; align-items:center; justify-content:space-between; padding:.8rem 0}
  .brand{font-weight:800; color:#166534; letter-spacing:-.02em}
  .nav{display:flex; gap:1.25rem}
  .nav a{color:var(--text); text-decoration:none; font-size:.95rem}

  /* Layout */
  .wrap{width:min(var(--container),100% - 2rem); margin-inline:auto}
  .title{font-size:2rem; margin:1.25rem 0 .75rem}
  .rule{height:1px; background:#E5E7EB}
  .grid{display:grid; grid-template-columns: 1fr 360px; gap:var(--space-6); margin-top:var(--space-6)}
  @media (max-width: 1024px){ .grid{grid-template-columns: 1fr} }

  /* Lista de productos */
  .item{padding:var(--space-6) 0; border-bottom:1px solid #E5E7EB}
  .row{display:grid; grid-template-columns: 140px 1fr 72px; gap:var(--space-6); align-items:flex-start}
  .pic{width:140px; aspect-ratio: 1/1; border-radius:12px; overflow:hidden; background:#fff}
  .pic img{width:100%; height:100%; object-fit:cover}
  .name{font-size:1.35rem; margin:0 0 .25rem 0}
  .avail{color:#28a745; font-weight:700; font-size:.95rem; margin-left:.5rem}
  .meta{display:flex; align-items:center; gap:1rem; margin:.9rem 0}
  .qty{display:inline-flex; align-items:center; gap:.6rem; border:1px solid #E1E3E6; border-radius:999px; padding:.35rem .6rem; background:#fff}
  .qty button{border:0; background:#fff; cursor:pointer; font-weight:700}
  .link-sim{color:#166534; text-decoration:none; font-size:.95rem}
  .submeta{display:flex; align-items:center; gap:.5rem; color:#374151; font-size:.95rem}
  .truck{width:22px; height:22px}
  .calendar{margin:.75rem 0 0 38px; color:#111; font-weight:700}

  .price{display:flex; align-items:center; justify-content:flex-end; gap:.6rem}
  .trash{width:22px; height:22px; cursor:pointer}
  .amount{color:var(--price); font-weight:800}

  /* Panel resumen */
  .summary{background:#fff; border:1px solid #E5E7EB; border-radius:16px; box-shadow:0 6px 18px rgba(0,0,0,.06); padding:var(--space-6); position:sticky; top:84px;}
  .sum-title{font-size:1.1rem; font-weight:800; margin:0 0 var(--space-4)}
  .promo{display:grid; grid-template-columns: 1fr 110px; gap:.6rem; margin-bottom:var(--space-6)}
  .promo input{border:1.6px solid #C9CDD3; border-radius:999px; padding:.6rem .9rem}
  .promo button{border:0; border-radius:999px; background:#EFEFEF; padding:.6rem .9rem; cursor:pointer}
  .line{display:flex; justify-content:space-between; align-items:center; padding:.45rem 0; color:#111}
  .pay{margin-top:var(--space-5)}
  .pay button{width:100%; border:0; border-radius:999px; padding:.9rem 1.25rem; background:var(--accent); font-weight:800; cursor:pointer}

  .box{background:var(--panel-soft); border:1px solid #E5E7EB; border-radius:16px; padding:var(--space-5); margin-top:var(--space-6)}
  .box .sum-title{margin-bottom:.6rem}
</style>
</head>
<body>
  <!-- Header -->
    <%@include file="fragmentos/header.jspf" %> 

  <main class="wrap">
    <h1 class="title">Carrito de Compras</h1>
    <div class="rule"></div>

    <section class="grid">
      <!-- Izquierda: lista de productos -->
      <div>
        <!-- Item 1 -->
        <article class="item">
          <div class="row">
            <div class="pic">
              <img src="../assets/suplemento3.jpeg" alt="Cápsulas de fibra Love Wellness">
            </div>

            <div>
              <h2 class="name">Cápsulas de fibra Love Wellness
                <span class="avail">Disponible</span>
              </h2>

              <div class="meta">
                <div class="qty" aria-label="Cantidad">
                  <button type="button" aria-label="Restar">−</button>
                  <span>1</span>
                  <button type="button" aria-label="Sumar">+</button>
                </div>
                <a class="link-sim" href="#">Ver Más Productos Similares</a>
              </div>

              <div class="submeta">
                <!-- icono tienda -->
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#111"><path d="M3 9l1-5h16l1 5"/><path d="M3 9h18v10H3z"/><path d="M7 9v10M17 9v10"/></svg>
                <span>Tienda física: No disponible</span>
              </div>

              <div class="calendar">
                <svg class="truck" viewBox="0 0 24 24" fill="none" stroke="#111" stroke-width="1.5">
                  <path d="M3 7h11v7H3zM14 9h4l3 3v2h-7z"/>
                  <circle cx="7" cy="16" r="2"/><circle cx="17" cy="16" r="2"/>
                </svg>
                Obtenlo el día: <strong>24 de octubre</strong>
              </div>
            </div>

            <div class="price">
              <!-- icono basura -->
              <svg class="trash" viewBox="0 0 24 24" fill="none" stroke="#111" stroke-width="1.7">
                <path d="M3 6h18M8 6V4h8v2M6 6l1 14h10l1-14"/>
                <path d="M10 10v6M14 10v6"/>
              </svg>
              <div class="amount">$350.98</div>
            </div>
          </div>
        </article>

        <!-- Item 2 -->
        <article class="item">
          <div class="row">
            <div class="pic">
              <img src="../assets/suplemento1.jpeg" alt="Multivitamínico Love Wellness">
            </div>

            <div>
              <h2 class="name">Multivitamínico Love Wellness
                <span class="avail">Disponible</span>
              </h2>

              <div class="meta">
                <div class="qty" aria-label="Cantidad">
                  <button type="button" aria-label="Restar">−</button>
                  <span>1</span>
                  <button type="button" aria-label="Sumar">+</button>
                </div>
                <a class="link-sim" href="#">Ver Más Productos Similares</a>
              </div>

              <div class="submeta">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#111"><path d="M3 9l1-5h16l1 5"/><path d="M3 9h18v10H3z"/><path d="M7 9v10M17 9v10"/></svg>
                <span>Tienda física: disponible</span>
              </div>

              <div class="calendar">
                <svg class="truck" viewBox="0 0 24 24" fill="none" stroke="#111" stroke-width="1.5">
                  <path d="M3 7h11v7H3zM14 9h4l3 3v2h-7z"/>
                  <circle cx="7" cy="16" r="2"/><circle cx="17" cy="16" r="2"/>
                </svg>
                Obtenlo el día: <strong>22 de octubre</strong>
              </div>
            </div>

            <div class="price">
              <svg class="trash" viewBox="0 0 24 24" fill="none" stroke="#111" stroke-width="1.7">
                <path d="M3 6h18M8 6V4h8v2M6 6l1 14h10l1-14"/>
                <path d="M10 10v6M14 10v6"/>
              </svg>
              <div class="amount">$245.98</div>
            </div>
          </div>
        </article>

        <div style="height:1px;background:#E5E7EB;margin-top:var(--space-6)"></div>
      </div>

      <!-- Derecha: resumen -->
      <aside>
        <div class="summary">
          <h3 class="sum-title">Resumen del Pedido</h3>

          <div class="promo">
            <div>
              <label for="code" style="display:block; font-weight:700; margin-bottom:.35rem">Código de Promoción</label>
              <input id="code" placeholder="XXXXXXXX" aria-label="Código de promoción">
            </div>
            <button type="button">Aplicar</button>
          </div>

          <div class="line"><span>Subtotal (2 productos):</span><strong>$596.96</strong></div>
          <div class="line"><span>Envío:</span><strong>$120.00</strong></div>
          <div class="line"><span>IVA (Aplicable):</span><strong>$120.00</strong></div>

          <div class="pay">
            <button type="button">Proceder al Pago</button>
          </div>
        </div>

        <div class="box">
          <h3 class="sum-title">Ahorro Total</h3>
          <div class="line"><span>Descuento aplicable:</span><strong>$60.00</strong></div>
        </div>
      </aside>
    </section>

    <div style="height:1px;background:#E5E7EB; margin:var(--space-8) 0"></div>
  </main>
</body>
</html>

