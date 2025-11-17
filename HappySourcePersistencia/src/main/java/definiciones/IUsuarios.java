/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package definiciones;

import dtos.AdminLoginDTO;
import dtos.AdminLogueadoDTO;
import dtos.UsuarioDTO;
import java.util.List;

/**
 *
 * @author vv094
 */
public interface IUsuarios {
    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);
    
    UsuarioDTO actualizarUsuario();
    
    UsuarioDTO eliminarUsuario();
    
    List<UsuarioDTO> consultarUsuarios();

    AdminLogueadoDTO loginAdmin(String correo, String contraseña);
}
