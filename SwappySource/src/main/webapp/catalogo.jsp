<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <%@include file="./fragmentos/Icon.jspf" %>
    <head>
        <meta charset="utf-8">
        <title>Happy Source | Catálogo</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/styles/styles.css">

        <script>
            window.API_BASE = "<%= request.getContextPath()%>/resources";
            window.PLACEHOLDER_IMG = "<%= request.getContextPath()%>/assets/suplemento1.jpeg";
        </script>

        <script defer src="<%= request.getContextPath()%>/scripts/catalogo.js"></script>

    </head>
    <body class="catalogoBody">
        <%@include file="./fragmentos/header.jspf" %>
        <div class="CatalogoSubtitulo">
            <h1>Catálogo de productos </h1>
            <p>Conócelos y enamórate de ellos. </p>
        </div>


        <div style="max-width: 980px; margin: 0 auto; padding: 20px;">
            <div style="display:flex; gap:12px; flex-wrap:wrap; justify-content:center;">
                <input id="searchInput" type="text" placeholder="Buscar producto..."
                       style="padding:10px 12px; min-width:260px; border-radius:10px; border:1px solid #ccc;">

                <select id="categoriaSelect"
                        style="padding:10px 12px; border-radius:10px; border:1px solid #ccc;">
                    <option value="">Todas las categorías</option>
                    <option value="PROTEINA_VEGETAL">PROTEINA_VEGETAL</option>
                    <option value="VITAMINAS">VITAMINAS</option>
                </select>

                <button id="btnLimpiar"
                        style="padding:10px 16px; border-radius:10px; border:none; cursor:pointer;">
                    Limpiar
                </button>
            </div>
        </div>
        <div id="catalogoRoot"></div>
        <%@include file="./fragmentos/footer.jspf" %>
    </body>
</html>

