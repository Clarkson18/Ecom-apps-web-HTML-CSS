<%-- 
    Document   : iniciarSesion
    Created on : Nov 13, 2025, 5:02:09 PM
    Author     : abrilislas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Happy Source | Iniciar sesión</title>
    <link rel="stylesheet" href="../Styles/forms.css">
    <%@include file="fragmentos/icon.jspf" %>
  </head>

  <body class="iniciarSesion">

    <div class="contenedor-principal">
      <div class="contenido">
        <h2>Iniciar sesión</h2>
        <h4>Ingresa tus credenciales</h4>
        <form action="LoginServlet">
          <label for="usuario">Nombre de usuario: </label>
          <input id="usuarioID" name="usuarioID" type="text" placeholder="Abril Islas" required autocomplete="off"/>

          <label for="usuario">Contraseña: </label>
          <input id="passwordUsuario" name="passwordUsuario" type="password" placeholder="" required autocomplete="off"/>
        
          <a href="cambiar_password">¿Has olvidado tu contraseña?</a>

          <button type="submit" class="botones">Iniciar sesión</button>
        </form>
        <h4>¿No tienes una cuenta?  <a href="registro.jsp"> Crea una aquí</a></h4>
      </div>
    </div>

  </body>
</html>

