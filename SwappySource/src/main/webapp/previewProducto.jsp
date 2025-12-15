<%-- Document : previewProducto Created on : Nov 11, 2025, 10:33:04 AM Author :
abrilislas --%> <%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <title>Happy Source | Producto</title>
        <link rel="stylesheet" href="./styles/styles.css" />
        <%@include file="./fragmentos/header.jspf" %>

        <script>
        window.API_BASE = "<%= request.getContextPath()%>/resources";
        </script>

        <script defer src="<%= request.getContextPath()%>/scripts/previewProducto.js"></script>  
    </head>

    <body>
        <header class="categoriaProducto"><h3 id="previewCategoria">Cargando...</h3></header>

        <section class="productoContainer">
            <div class="productoImagen">
                <img id="previewImg" src="" alt="Producto" />
            </div>

            <div class="productoInfo">
                <h1 id="previewNombre">Cargando...</h1>
                <p class="descripcion" id="previewDescripcion"></p>
                <p class="precioG" id="previewPrecio"></p>
                <div class="botonesProducto">
                    <p style="margin-top:8px;">Stock: <strong><span id="previewStock"></span></strong></p>    
                    <button class="btnComprar" id="btnComprar">Comprar ahora</button>
                    <button class="btnAgregar" id="btnAgregarCarrito">Agregar al carrito</button>
                </div>

                <div class="reviews">
                    <h4>Reseñas</h4>

                    <div id="reviewsList"></div> 

                    <form id="reviewForm" style="margin-top:12px; display:none;"> 
                        <label style="display:block; margin-bottom:6px;">Calificación</label>
                        <select id="reviewRating" required style="padding:8px; border-radius:10px;">
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>

                        <label style="display:block; margin:10px 0 6px;">Comentario</label>
                        <textarea id="reviewText" required placeholder="Escribe tu reseña..."
                                  style="width:100%; padding:10px; border-radius:10px;"></textarea>

                        <button type="submit" class="btnAgregar" style="margin-top:8px;">Publicar reseña</button>
                    </form>

                    <p id="reviewLoginMsg" style="display:none; margin-top:10px;">
                        Inicia sesión como cliente para escribir una reseña.
                    </p>
                </div>
            </div>
        </section>

        <section class="beneficios">
            <h3>Product Benefits</h3>
            <p>
                Nuestra proteína está potenciada con B12 y adaptógenos naturales. Mejora
                el rendimiento, energía y recuperación muscular sin ingredientes
                artificiales.
            </p>
        </section>
        <%@include file="./fragmentos/footer.jspf" %>
    </body>
</html>
