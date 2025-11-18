/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import definiciones.IUsuariosDAO;
import dtos.UsuarioLogueadoDTO;
import dtos.ProductoDTO;
import dtos.UsuarioDTO;
import entidades.Usuario;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import utils.PassManager;

import java.util.List;

import adaptadores.ProductoAdapter;
import definiciones.IPersistencia;
import definiciones.IProductoDAO;
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
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO actualizarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO eliminarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UsuarioDTO> consultarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioLogueadoDTO loginUsuario(String correo, String pass) {
        Usuario usuario = usuariosDAO.getUsuarioCorreo(correo);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        String password = usuario.getPassword();

        try {
            if (PassManager.verificarPassword(pass, password)) {
                return new UsuarioLogueadoDTO(usuario.getNombre(), usuario.getCorreo(), usuario.getId().toString(),
                        usuario.getRol(), usuario.getDirecciones());
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
    public List<ProductoDTO> listaProductos() {
        List<Producto> productos = productoDAO.listaProductos();
        List<ProductoDTO> productosDTO = ProductoAdapter.toDTOList(productos);
        return productosDTO;
    }

    @Override
    public ProductoDTO agregarProducto(ProductoDTO productoDTO) {
        Producto producto = ProductoAdapter.toEntity(productoDTO);
        Producto productoAgregado = productoDAO.agregarProducto(producto);
        return ProductoAdapter.toDTO(productoAgregado);
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) {
        Producto producto = ProductoAdapter.toEntity(productoDTO);
        Producto productoActualizado = productoDAO.actualizarProducto(producto);
        return ProductoAdapter.toDTO(productoActualizado);
    }

    @Override
    public ProductoDTO eliminarProducto(ProductoDTO productoDTO) {
        Producto producto = ProductoAdapter.toEntity(productoDTO);                                                              
        Producto productoEliminado = productoDAO.eliminarProducto(producto);
        return ProductoAdapter.toDTO(productoEliminado);
    }

    @Override
    public ProductoDTO obtenerProductoPorId(String id) {
        Producto producto = productoDAO.obtenerProductoPorId(id);
        return ProductoAdapter.toDTO(producto);
    }

}
