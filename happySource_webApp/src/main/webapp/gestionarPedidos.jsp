<%-- 
    Document   : gestionarPedidos
    Description: Vista principal para la gestión de pedidos del administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title>Happy Source (Admin) | Gestión de pedidos</title>
        <link rel="stylesheet" href="styles/administradorStyles.css">
        <%@include file="fragmentos/icon.jspf" %>
    </head>
    <body>
        <div class="mainContainer">
            <nav class="header">
                <h2 id="logo">Happy Source (Admin pane).</h2>
            </nav>

            <center><h1>Gestión de pedidos</h1></center>

            <div class="mainContentDivs gestionar-pedidos-main">
                <div class="OptionPane OptionPane--fullwidth">
                    <h2 class="title">Pedidos registrados</h2>
                    <h4 class="subtitle">Revisa el historial de pedidos y actualiza su estado.</h4>

                    <!-- Filtros básicos (puro front) -->
                    <div class="filtros-pedidos">
                        <div class="filtro">
                            <label for="filtroEstado">Estado:</label>
                            <select id="filtroEstado" name="estado">
                                <option value="">Todos</option>
                                <option value="PENDIENTE">Pendiente</option>
                                <option value="EN_PROCESO">En proceso</option>
                                <option value="ENVIADO">Enviado</option>
                                <option value="ENTREGADO">Entregado</option>
                            </select>
                        </div>

                        <div class="filtro">
                            <label for="filtroCliente">Cliente:</label>
                            <input type="text" id="filtroCliente" name="cliente"
                                   placeholder="Nombre o correo del cliente">
                        </div>

                        <button type="button" class="btn-filtrar">Filtrar</button>
                    </div>

                    <!-- Tabla principal de gestión de pedidos -->
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
                                <!-- Filas de ejemplo. Después le ponemos datos reales(PedidoDTO). -->
                                <tr>
                                    <td>#1001</td>
                                    <td>Juan Pérez</td>
                                    <td>18/11/2025</td>
                                    <td>
                                        <span class="estado-badge estado-pendiente">Pendiente</span>
                                    </td>
                                    <td>$350.00</td>
                                    <td>
                                        <button type="button" class="btn-tabla">Ver detalle</button>
                                        <button type="button" class="btn-tabla">Marcar como enviado</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#1002</td>
                                    <td>María López</td>
                                    <td>17/11/2025</td>
                                    <td>
                                        <span class="estado-badge estado-en-proceso">En proceso</span>
                                    </td>
                                    <td>$520.00</td>
                                    <td>
                                        <button type="button" class="btn-tabla">Ver detalle</button>
                                        <button type="button" class="btn-tabla">Marcar como enviado</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#1003</td>
                                    <td>Carlos Ruiz</td>
                                    <td>16/11/2025</td>
                                    <td>
                                        <span class="estado-badge estado-entregado">Entregado</span>
                                    </td>
                                    <td>$210.00</td>
                                    <td>
                                        <button type="button" class="btn-tabla">Ver detalle</button>
                                        <button type="button" class="btn-tabla">Ver comprobante</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="botonesContainer" style="margin-top: 20px;">
                        <button type="button" onclick="window.location.href = 'panelAdministrador.jsp'">
                            Volver al panel principal
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
