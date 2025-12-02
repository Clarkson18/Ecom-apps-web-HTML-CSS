/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import definiciones.IUsuariosDAO;
import entidades.Usuario;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import utils.PassManager;

import java.util.List;

import Enumeradores.EstadoEnvio;
import definiciones.IPersistencia;
import definiciones.IProductoDAO;
import entidades.Pedido;
import entidades.Producto;

/**
 *
 * @author vv094
 */
public class Persistencia implements IPersistencia {

    private final IUsuariosDAO usuariosDAO;
    private final IProductoDAO productoDAO;

    public Persistencia() {
        this.usuariosDAO = new UsuariosDAO();
        this.productoDAO = new ProductoDAO();
    }

    // Metodos de usuario
    @Override
    public Usuario registrarUsuario(Usuario usuarioDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario actualizarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario eliminarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> consultarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario loginUsuario(String correo, String pass) {
        Usuario usuario = usuariosDAO.getUsuarioCorreo(correo);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        String password = usuario.getPassword();

        try {
            if (PassManager.verificarPassword(pass, password)) {
                return usuario;
            } else {
                throw new IllegalArgumentException("Credenciales incorrectas");

            }
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(Persistencia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw new IllegalArgumentException("Credenciales incorrectas");
        } catch (InvalidKeySpecException ex) {
            System.getLogger(Persistencia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw new IllegalArgumentException("Credenciales incorrectas");
        }
    }

    // Metodos de producto
    @Override
    public List<Producto> listaProductos() {
        List<Producto> productos = productoDAO.listaProductos();
        return productos;
    }

    @Override
    public Producto agregarProducto(Producto producto) {
        Producto productoAgregado = productoDAO.agregarProducto(producto);
        return productoAgregado;
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        Producto productoActualizado = productoDAO.actualizarProducto(producto);
        return productoActualizado;
    }

    @Override
    public Producto eliminarProducto(Producto producto) {
        Producto productoEliminado = productoDAO.eliminarProducto(producto);
        return productoEliminado;
    }

    @Override
    public Producto obtenerProductoPorId(String id) {
        Producto producto = productoDAO.obtenerProductoPorId(id);
        return producto;
    }

    @Override
    public Pedido actualizarEstadoPedido(String idPedido, EstadoEnvio nuevoEstado) {
        Pedido pedidoActualizado = new PedidosDAO().actualizarEstadoPedido(idPedido, nuevoEstado);
        return pedidoActualizado;
    }

    @Override
    public List<Pedido> consultarPedidos(String idUsuario) {
        List<Pedido> pedidos = new PedidosDAO().consultarPedidos(idUsuario);
        return pedidos;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearPedido'");
    }

    @Override
    public Pedido obtenerPedidoPorId(String idPedido) {
        Pedido pedido = new PedidosDAO().obtenerPedidoPorId(idPedido);
        return pedido;
    }

}
