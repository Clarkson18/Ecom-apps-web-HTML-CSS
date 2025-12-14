<%-- 
    Document   : registrarUsuario
    Created on : Nov 13, 2025, 5:01:18 PM
    Author     : abrilislas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Happy Source | Registro</title>
    <link rel="stylesheet" href="./styles/forms.css">
  </head>
  
  <body class="registroBody">
    <div class="contenedor-principal">
      <div class="contenido">
        <h2>Crear cuenta</h2>
        <h4>Ingresa tus datos</h4>
        
        <form id="registroForm" action="<%= request.getContextPath() %>/RegistrarUsuarioServlet" method="POST">
            
          <label>Nombre:</label>
          <input type="text" name="nombre" required>

          <label>Correo:</label>
          <input type="email" name="correo" required>

          <label>Teléfono:</label>
          <input type="text" name="telefono">

          <label>Dirección:</label>
          <input type="text" name="direccion">

          <label>Contraseña:</label>
          <input type="password" name="password" required>

          <label>Confirmar contraseña:</label>
          <input type="password" name="passwordConfirm" required>

          <input type="hidden" name="rol" value="cliente">

          <button type="submit" class="botones">Registrarme</button>

          <h4>¿Ya tienes una cuenta?  <a href="iniciarSesion.jsp"> Inicia sesión aquí</a></h4>

        </form>
      </div>
    </div>

  </body>
</html>

