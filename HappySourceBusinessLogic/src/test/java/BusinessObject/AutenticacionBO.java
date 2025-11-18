
import definiciones.IPersistencia;
import dtos.UsuarioLogueadoDTO;
import implementaciones.Persistencia;
import java.util.logging.Logger;
import Exceptions.BusinessException;
import static com.sun.tools.classfile.Attribute.Exceptions;
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
    IPersistencia persistencia = new Persistencia();
    private String MENSAJE_ERROR="Ha ocurrido un error al autenticar el usuario";
    private static final Logger LOG = Logger.getLogger(AutenticacionBO.class.getName());
    
    
    public AutenticacionBO getInstance(){
        
        if (this.autenticacionBO==null){
            this.autenticacionBO = new AutenticacionBO();
        }
        return autenticacionBO;
    }
    
    public AutenticacionBO(){
        getInstance();
    }
    
    public UsuarioLogueadoDTO verificarUsuario(String correo, String contraseña){
        
        try{
            if(correo!=null || correo.isBlank() ||contraseña!=null || contraseña.isBlank()){
                return persistencia.loginUsuario(correo,contraseña);
            }
        
        }catch(BusinessException ex){
            LOG.severe(MENSAJE_ERROR);
            return null;

        }
    }
    
    
   
