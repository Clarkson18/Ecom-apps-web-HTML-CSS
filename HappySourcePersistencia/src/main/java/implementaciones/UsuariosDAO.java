/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionMongoDB;
import definiciones.IUsuariosDAO;
import dtos.AdminLogueadoDTO;
import dtos.UsuarioDTO;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author vv094
 */
public class UsuariosDAO implements IUsuariosDAO{

    private final MongoCollection<Document> coleccion;
    
    public UsuariosDAO() {
        MongoDatabase db = ConexionMongoDB.getConexion();
        coleccion = db.getCollection("usuarios");
    }


    @Override
    public void registrarUsuario(UsuarioDTO usuarioDTO) {
        
    }

    @Override
    public void actualizarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<UsuarioDTO> consultarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public AdminLogueadoDTO loginAdmin(String correo, String contraseña) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginAdmin'");
    }
    
}
