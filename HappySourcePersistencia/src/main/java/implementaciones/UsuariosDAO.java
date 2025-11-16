/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import conexion.ConexionMongoDB;
import definiciones.IUsuariosDAO;
import dtos.AdminLogueadoDTO;
import dtos.UsuarioDTO;
import entidades.Usuario;

import java.util.List;
import org.bson.Document;

/**
 *
 * @author vv094
 */
public class UsuariosDAO implements IUsuariosDAO {

    private final String NOMBRE_COLECCION = "Usuarios";
    private final String CAMPO_NOMBRES = "nombres";
    private final String CAMPO_CORREO = "correo";
    private final String CAMPO_CONTRASEÑA = "contraseña";
    private final String CAMPO_TELEFONO = "telefono";
    private final String CAMPO_DIRECCION = "direccion";
    private final String CAMPO_ROL = "rol";

    private static UsuariosDAO instance;    

    public static UsuariosDAO getInstance() {
        if (instance == null) {
            instance = new UsuariosDAO();
        }
        return instance;
    }

    @Override
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario actualizarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario eliminarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> consultarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario getUsuarioCorreo(String correo) {
        MongoCollection<Usuario> coleccion = crearConexion();
        Usuario usuario = coleccion.find(Filters.eq(CAMPO_CORREO, correo)).first();
        return usuario;
    }

    private MongoCollection crearConexion() {
        MongoDatabase db = ConexionMongoDB.getConexion();
        MongoCollection<Usuario> coleccion = db.getCollection(NOMBRE_COLECCION, Usuario.class);
        return coleccion;
    }

}
