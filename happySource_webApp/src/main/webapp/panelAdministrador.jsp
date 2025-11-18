<%-- 
    Document   : panelAdministrador
    Created on : Nov 18, 2025, 10:10:40 AM
    Author     : abrilislas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Happy Source (Admin)| Managment tab</title>
    <link rel="stylesheet" href="styles/administradorStyles.css">
    <%@include file="fragmentos/icon.jspf" %>
  </head>
  <body>
    <div class="mainContainer">
      <nav class="header">
        <h2 id="logo">Happy Source (Admin pane).</h2>
      </nav>

      <center><h1>Bienvenido, ${usuario.nombre}</h1></center>

      <div class="mainContentDivs">

        <div class="OptionPane">
          <h2 class="title">Administración de usuarios</h2>
          <h4 class="subtitle">Selecciona una opción</h4>
          <div class="botonesContainer">
            <button type="getUsuarios" name="getUsuarios">Consultar usuarios</button>
            <button type="updateUsuario" name="updateUsuario">Actualizar usuario</button>
            <button type="deleteUsuario" name="deleteUsuario">Eliminar usuario</button>
            <button type="deleteUsuario" name="deleteUsuario">Consultar pedidos</button>
          </div>
        </div>

        <div class="OptionPane">
          <h2 class="title">Administración de productos</h2>
          <h4 class="subtitle">Selecciona una opción</h4>
          <div class="botonesContainer">
            <button type="addProducto" name="addProducto">Agregar producto</button>
            <button type="deleteProductos" name="deleteProductos">Eliminar producto</button>
            <button type="updateProductos" name="updateProductos">Actualizar producto</button>
            <button type="getProductos" name="getProductos">Consultar productos</button>
          </div>
        </div>

      </div>

    </div>

  </body>
</html>

