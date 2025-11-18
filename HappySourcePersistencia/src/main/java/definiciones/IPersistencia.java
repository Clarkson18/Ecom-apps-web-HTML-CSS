/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package definiciones;

import dtos.UsuarioLogueadoDTO;
import entidades.Producto;
import dtos.ProductoDTO;
import dtos.UsuarioDTO;
import java.util.List;

/**
 *
 * @author vv094
 */
public interface IPersistencia {
    //Usuario methods
    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);
    
    UsuarioDTO actualizarUsuario();
    
    UsuarioDTO eliminarUsuario();
    
    List<UsuarioDTO> consultarUsuarios();

    UsuarioLogueadoDTO loginUsuario(String correo, String contraseña);

    //Producto methods
    List<ProductoDTO> listaProductos();
    ProductoDTO agregarProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(ProductoDTO productoDTO);
    ProductoDTO eliminarProducto(ProductoDTO productoDTO);
    ProductoDTO obtenerProductoPorId(String id);
}
