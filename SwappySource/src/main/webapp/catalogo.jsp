<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <%@include file="./fragmentos/Icon.jspf" %>
    <head>
        <meta charset="utf-8">
        <title>Happy Source | Catálogo</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/styles.css">

        <script>
      window.API_BASE = "<%= request.getContextPath()%>/resources";
      window.PLACEHOLDER_IMG = "<%= request.getContextPath()%>/assets/suplemento1.jpeg";
        </script>
        
        <script defer src="<%= request.getContextPath() %>/scripts/catalogo.js"></script>
            
    </head>
    <body class="catalogoBody">
        <%@include file="./fragmentos/header.jspf" %>
        <div class="CatalogoSubtitulo">
            <h1>Catálogo de productos </h1>
            <p>Conócelos y enamórate de ellos. </p>
        </div>
        
        <div id="catalogoRoot"></div>
            <%@include file="./fragmentos/footer.jspf" %>
    </body>
</html>

