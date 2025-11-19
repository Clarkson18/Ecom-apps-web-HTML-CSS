<%-- 
    Document   : panelAdministrador
    Created on : Nov 18, 2025, 10:10:40 AM
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
                        <form action="<%= request.getContextPath() %>/AdministrativeServlet" method="get">
                            <button type="submit" name="accion" value="getUsuarios">Consultar usuarios</button>
                            <button type="submit" name="accion" value="updateUsuario">Actualizar usuario</button>
                            <button type="submit" name="accion" value="deleteUsuario">Eliminar usuario</button>
                            <button type="submit" name="accion" value="getPedidos">Consultar pedidos</button>
                        </form>
                    </div>
                </div>

                <div class="OptionPane">
                    <h2 class="title">Administración de productos</h2>
                    <h4 class="subtitle">Selecciona una opción</h4>
                    <div class="botonesContainer">
                        <form action="<%= request.getContextPath() %>/AdministrativeServlet" method="get">
                            <button type="submit" name="accion" value="addProducto">Agregar producto</button>
                            <button type="submit" name="accion" value="deleteProductos">Eliminar producto</button>
                            <button type="submit" name="accion" value="updateProductos">Actualizar producto</button>
                            <button type="submit" name="accion" value="getProductos">Consultar productos</button>
                        </form>
                    </div>
                </div>

                <!--  Gestión de pedidos (vista dedicada) -->
                <div class="OptionPane">
                    <h2 class="title">Gestión de pedidos</h2>
                    <h4 class="subtitle">Consulta y administra los pedidos realizados.</h4>

                    <!-- Botón para ir a la pantalla completa de gestión de pedidos -->
                    <div class="botonesContainer">
                        <form action="gestionarPedidos.jsp" method="get">
                            <button type="submit" name="consultarPedidos">Ir a gestión de pedidos</button>
                        </form>
                    </div>

                    <!-- Vista rápida de pedidos (solo front por ahora) -->
                    <div class="tablaPedidosWrapper">
                        <table class="tabla-pedidos-admin">
                            <thead>
                                <tr>
                                    <th>ID pedido</th>
                                    <th>Cliente</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th>Total</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Filas de ejemplo; luego le ponemos datos reales -->
                                <tr>
                                    <td>#1001</td>
                                    <td>Monica H</td>
                                    <td>18/11/2025</td>
                                    <td><span class="estado-badge estado-pendiente">Pendiente</span></td>
                                    <td>$350.00</td>
                                    <td><button type="button" class="btn-tabla">Ver detalle</button></td>
                                </tr>
                                <tr>
                                    <td>#1002</td>
                                    <td>Juan Escutia</td>
                                    <td>17/11/2025</td>
                                    <td><span class="estado-badge estado-en-proceso">En proceso</span></td>
                                    <td>$520.00</td>
                                    <td><button type="button" class="btn-tabla">Ver detalle</button></td>
                                </tr>
                                <tr>
                                    <td>#1003</td>
                                    <td>Carlos Trejo</td>
                                    <td>16/11/2025</td>
                                    <td><span class="estado-badge estado-entregado">Entregado</span></td>
                                    <td>$210.00</td>
                                    <td><button type="button" class="btn-tabla">Ver detalle</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- Fin de Gestión de pedidos -->

            </div>

        </div>

    </body>
</html>
