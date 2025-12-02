/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package definiciones;

import java.util.List;

import Enumeradores.EstadoEnvio;
import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;

/**
 *
 * @author vv094
 */
public interface IPersistencia {
    //Usuario methods
    Usuario registrarUsuario(Usuario usuarioDTO);
    
    Usuario actualizarUsuario();
    
    Usuario eliminarUsuario();
    
    List<Usuario> consultarUsuarios();

    Usuario loginUsuario(String correo, String contraseña);

    //Producto methods
    List<Producto> listaProductos();
    Producto agregarProducto(Producto productoDTO);
    Producto actualizarProducto(Producto productoDTO);
    Producto eliminarProducto(Producto productoDTO);
    Producto obtenerProductoPorId(String id);

    //Pedido methods
    public Pedido actualizarEstadoPedido(String idPedido, EstadoEnvio nuevoEstado);
    public List<Pedido> consultarPedidos(String idUsuario);
    public Pedido crearPedido(Pedido pedido);
    public Pedido obtenerPedidoPorId(String idPedido);
}
