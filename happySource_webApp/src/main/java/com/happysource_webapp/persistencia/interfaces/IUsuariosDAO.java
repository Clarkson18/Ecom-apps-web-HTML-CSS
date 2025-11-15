/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.happysource_webapp.persistencia.interfaces;

import com.happysource_webapp.dominio.UsuarioDTO;
import java.util.List;

/**
 *
 * @author abrilislas
 */
public interface IUsuariosDAO {
    
    void registrarUsuario(UsuarioDTO usuarioDTO);
    
    void actualizarUsuario();
    
    void eliminarUsuario();
    
    //List<UsuarioDTO> consultarUsuarios();
    
}
