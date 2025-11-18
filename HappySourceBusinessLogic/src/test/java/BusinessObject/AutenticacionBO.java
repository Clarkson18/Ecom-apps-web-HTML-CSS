
import dtos.UsuarioLogueadoDTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abrilislas
 */
public class AutenticacionBO {
    
    AutenticacionBO autenticacionBO;
    IPersistencia persistencia = new IPersistencia();
    public AutenticacionBO getInstance(){
        
        if (this.autenticacionBO==null){
            this.autenticacionBO = new AutenticacionBO();
        }
        return autenticacionBO;
    }
    
    public UsuarioLogueadoDTO verificarUsuario(String correo, String contraseña){
        if(correo!=null || correo.isBlank()){
            return new BusinessException();
        }
        
        if(contraseña!=null || contraseña.isBlank()){
            return new BusinessException();
        }
        return persistencia.UsuarioLogueadoDTO(correo,contraseña);
    }
    
    
}
