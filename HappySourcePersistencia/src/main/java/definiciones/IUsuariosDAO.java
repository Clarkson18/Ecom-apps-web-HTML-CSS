/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package definiciones;

import entidades.Usuario;

import java.util.List;

/**
 *
 * @author vv094
 */
public interface IUsuariosDAO {
   
    Usuario registrarUsuario(Usuario usuario);
    
    Usuario actualizarUsuario(Usuario usuario);
    
    Usuario eliminarUsuario(Usuario usuario,  String correo);
    
    List<Usuario> consultarUsuarios();
    
    Usuario getUsuarioCorreo(String correo);

}
