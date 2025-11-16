/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package definiciones;

import dtos.AdminLogueadoDTO;
import dtos.UsuarioDTO;
import entidades.Usuario;

import java.util.List;

/**
 *
 * @author vv094
 */
public interface IUsuariosDAO {
    void registrarUsuario(UsuarioDTO usuarioDTO);
    
    void actualizarUsuario();
    
    void eliminarUsuario();
    
    List<Usuario> consultarUsuarios();
    
    Usuario getUsuarioCorreo(String correo);

}
