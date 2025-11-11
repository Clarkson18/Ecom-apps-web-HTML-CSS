<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Happy Source | Catálogo</title>
    <link rel="stylesheet" href="../Styles/styles.css">
    <link rel="icon" type="image/png" sizes="32x32" href="../assets/logoHS.png">

  </head>
  <body class="catalogoBody">
      <%@include file="fragmentos/header.jspf" %> 
          <div class="CatalogoSubtitulo">
            <h1>Catálogo de productos </h1>
            <p>Conócelos y enamórate de ellos. </p>
          </div>
  <section class="categoria">
    <h2 class="divisionSeccionVitaminas">Vitaminas y probióticos</h2>
    <div class="productos">
      <div class="producto">
          <a class="displayProducto" href="preview_Producto.html">
      <img src="https://media.ulta.com/i/ulta/2550466?w=800&$ProductCardNeutralBGLight$&h=800&fmt=auto" alt="vitamina1"></a>
      <h3>Cápsulas de fibra Love Wellness</h3>
      <p class="precio">$350.98</p>
      <p class="disponible">Disponible</p>
        </a>
    </div>

      <div class="producto">
        <a class="displayProducto" href="#">
      <img src="https://ethical-nutrition.com/cdn/shop/files/veganvitamind3supplementuk.png?v=1746003137" alt="vitamina2"></a>
      <h3>Multivitamínico Love Wellness</h3>
      <p class="precio">$245.98</p>
      <p class="disponible">Disponible</p>
    </div>

      <div class="producto">
          <a class="displayProducto" href="#">
      <img src="https://www.naturewise.com/cdn/shop/files/Vegan_Vitamin_K2_-_180CT_-_PDP_-_Front.jpg?v=1754514438&width=3000" alt="vitamina3"></a>
      <h3>Ritual Essential for Women</h3>
      <p class="precio">$213.00</p>
      <p class="agotado">Agotado</p>
    </div>
  </div>
</section>

<section class="categoria">
  <h2 class="divisionProteina">Proteína en polvo</h2>
  <div class="productos">
    <div class="producto">
        <a class="displayProducto" href="#">
    <img src="https://freesoul.com/cdn/shop/articles/Protein_Reformulation_e957be15-e9ed-4b48-8afa-907731251fab.jpg?v=1752416671&width=684" alt="Proteina free soul"></a>
    <h3>Cápsulas de fibra Love Wellness</h3>
    <p class="precio">$350.98</p>
    <p class="disponible">Disponible</p>
      </a>
  </div>

    <div class="producto">
      <a class="displayProducto" href="#">
    <img src="https://www.bulk.com/media/catalog/product/B/B/BBLE_CDPA_CHOC_Main_Image_872c.jpg" alt="proteina Bulk"></a>
    <h3>Proteína en polvo Bulk - chocolate</h3>
    <p class="precio">$245.98</p>
    <p class="disponible">Disponible</p>
  </div>

    <div class="producto">
        <a class="displayProducto" href="#">
    <img src="https://theforestsuperfood.com/cdn/shop/files/p2_2ef262d5-ffb8-430b-a721-271dd8b9a0b5.png?v=1741693772&width=2000" alt="Proteina forest"></a>
    <h3>Forest proteína en polvo - Vainilla </h3>
    <p class="precio">$213.00</p>
    <p class="agotado">Agotado</p>
  </div>
</div>
</section>

<section class="categoria">
  <h2 class="divisionGummies">Selección de gomitas</h2>
  <div class="productos">
    <div class="producto">
        <a class="displayProducto" href="#">
    <img src="https://myrite.co/cdn/shop/products/DSC03508_BG_f03d5e39-92f0-4630-b3ca-29b55eac030f_2048x.jpg?v=1717655420" alt="vitamina1"></a>
    <h3>Rite - kids gummies </h3>
    <p class="precio">$350.98</p>
    <p class="disponible">Disponible</p>
      </a>
  </div>

    <div class="producto">
      <a class="displayProducto" href="#">
    <img src="https://myrite.co/cdn/shop/files/Rite-Gummies-Balance-Fruit_4479133e-c93e-4e28-9eb0-59ff0bbac5a4_2048x.jpg?v=1706786511" alt="vitamina2"></a>
    <h3>Rite - para salud intestinal</h3>
    <p class="precio">$245.98</p>
    <p class="disponible">Disponible</p>
  </div>

    <div class="producto">
        <a class="displayProducto" href="#">
    <img src="https://myrite.co/cdn/shop/files/HAIR_gummies_For_Stronger_Healthier_Hair_with_Vitamin_Plant_Complex_1500x.jpg?v=1750158034" alt="vitamina3"></a>
    <h3>Rite - HAIR GUMMIES</h3>
    <p class="precio">$213.00</p>
    <p class="agotado">Agotado</p>
  </div>
</div>
</section>
      <%@include file="fragmentos/footer.jspf" %> 
  </body>
</html>

