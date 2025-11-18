
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Happy Source | Inicio</title>
    <link rel="stylesheet" href="../styles/styles.css">
    <%@include file="fragmentos/header.jspf" %> 


  </head>
  <body class="landingPage">
    <div class="landingPageContainer">
        
        <%@include file="fragmentos/header.jspf" %> 

        <div class="bannerContainer">
          <div class="subtitulo">
            <h1>Productos pensados para tu estilo de vida </h1>
            <p>Explora nuestro catálogo de opciones saludables</p>
            <div class="botones">
              <button type="button" name="botonCatalogo" id="botonCatalogo" onclick="window.location.href='catalogo.html'">Consultar catálogo</button>
              <button type="button" name="botonCarrito" id="botonCarrito"  onclick="window.location.href='carrito.html'">Carrito de compras</button>
            </div>
          </div>

          <div class="banner"><img src="../assets/banner_HappySource.jpg" alt="banner"></div>
        </div>

        <div class="muestraCatalogo">
          <div class="titulo">
              <h1>Explora nuestro extenso catálogo</h1>
          </div>
          <div class="subtitulo">
              <p>Encuentra tus nuevos productos favoritos, nutridos de ingredientes que se preocupan tanto por tu bienestar como tú.<br>
              <strong>100% veganos y libres de crueldad animal.</strong></p>
          </div>

          <div class="imagenesCatalogo">
            <a href="catalogo.html"><img src="https://lovewellness.com/cdn/shop/files/LW_PDP_HealthyV-Bundle_Silo.jpg?v=1722215326" alt="suplemento"></a>
            <a href="catalogo.html"><img src="https://myrite.co/cdn/shop/files/Rite-Gummies-Balance-Fruit_4479133e-c93e-4e28-9eb0-59ff0bbac5a4_2048x.jpg?v=1706786511" alt="gomitas"></a>
            <a href="catalogo.html"><img src="https://www.cureveda.com/cdn/shop/files/Pro_Chocolate--Pack_of_01_8a5b8b9d-ed4d-40d8-baf4-00a8bbd29592.jpg?v=1758626294&width=2048" alt="proteína"></a>
          </div>
        </div>
    </div>

      <%@include file="fragmentos/footer.jspf" %> 

  </body>
</html>
