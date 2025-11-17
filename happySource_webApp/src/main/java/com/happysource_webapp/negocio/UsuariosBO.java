/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happysource_webapp.negocio;

import com.happysource_webapp.dominio.UsuarioDTO;

/**
 *
 * @author abrilislas
 */
public class UsuariosBO {
    
    private static UsuariosBO instance;
    private String MENSAJE_ERROR_REGISTRO = "HUBO UN ERROR AL REGISTRAR AL USUARIO"; 
    private String MENSAJE_ERROR_CONSULTA = "HUBO UN ERROR AL CONSULTAR AL USUARIO"; 

    
    private UsuariosBO(){}
    
    public static UsuariosBO getInstance(){
        if(instance ==null){
            instance = new UsuariosBO();
        }
        return instance;
    }
    
//    public Usuario consultarUsuario(UsuarioDTO usuarioDTO){
//        IPersistencia persistencia = new IUsuariosDAO();
//        return persistencia.consultarUsuario(usuarioDTO);
//    }
    
    public void verificarUsuario(UsuarioDTO usuarioDTO, String mensajeError){
        if(usuarioDTO==null){
            throw new IllegalArgumentException(MENSAJE_ERROR_REGISTRO);
        }
    }
    /*public AdministradorBO isAdministrador(){
    
    }*/
    
    public void registrarUsuario(){
    
    
    }
    
    
}
