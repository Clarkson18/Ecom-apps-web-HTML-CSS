<%-- 
    Document   : iniciarSesion
    Created on : 30 nov 2025, 15:05:48
    Author     : vv094, Abril Islas
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Happy Source | Iniciar sesión</title>
    <link rel="stylesheet" href="./styles/forms.css">
  </head>

  <body class="iniciarSesion">

    <div class="contenedor-principal">
      <div class="contenido">
        <h2>Iniciar sesión</h2>
        <h4>Ingresa tus credenciales</h4>
        
        <form action="<%= request.getContextPath() %>/LoginServlet" method="post">
          <label for="correo-electronico">Correo: </label>
          <input id="correo-electronico" name="correo-electronico" class= "correo-electronico" type="text" placeholder="" required autocomplete="off"/>

          <label for="usuario">Contraseña: </label>
          <input id="passwordUsuario" name="passwordUsuario" type="password" placeholder="" required autocomplete="off"/>
        
          <a href="cambiar_password">¿Has olvidado tu contraseña?</a>

          <p style="color:red;">${error}</p>
          <button type="submit" class="botones">Iniciar sesión</button>
        </form>
        <h4>¿No tienes una cuenta?  <a href="registrarUsuario.jsp"> Crea una aquí</a></h4>
      </div>
    </div>

  </body>
</html>