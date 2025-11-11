<%-- 
    Document   : previewProducto
    Created on : Nov 11, 2025, 10:33:04 AM
    Author     : abrilislas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Happy Source | Producto</title>
    <link rel="stylesheet" href="../Styles/styles.css">
    <link rel="icon" type="image/png" sizes="32x32" href="../assets/logoHS.png">
  </head>

  <body>
        <%@include file="fragmentos/header.jspf" %> 

       <header class="categoriaProducto"><h3>Proteína en polvo</h3></header>

        <section class="productoContainer">
          <div class="productoImagen">
            <img src="https://freesoul.com/cdn/shop/articles/Protein_Reformulation_e957be15-e9ed-4b48-8afa-907731251fab.jpg?v=1752416671&width=684" alt="Proteina free soul">
          </div>

        <div class="productoInfo">
          <h1>Free* SOUL proteína</h1>
          <p class="descripcion">Proteína vegana sabor chocolate</p>
          <p class="precioG">$248.00</p>
          <div class="botonesProducto">
            <button class="btnComprar">Comprar ahora</button>
            <button class="btnAgregar">Agregar al carrito</button>
          </div>

          <div class="reviews">
            <h4>Reseñas</h4>
            <p><strong>Abril Islas:</strong> Excelente proteína, la recomiendo siempre.</p>
            <p><strong>José Ramón:</strong> Sabe muy bien y es fácil de disolver.</p>
          </div>
        </div>
      </section>

      <section class="beneficios">
        <h3>Product Benefits</h3>
        <p>
          Nuestra proteína está potenciada con B12 y adaptógenos naturales.
          Mejora el rendimiento, energía y recuperación muscular sin ingredientes artificiales.
        </p>
      </section>
    <%@include file="fragmentos/footer.jspf" %> 
  </body>
</html>

