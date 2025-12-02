package implementaciones;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

import conexion.ConexionMongoDB;
import definiciones.IUsuariosDAO;
import entidades.Usuario;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class UsuariosDAO implements IUsuariosDAO {

    private final String NOMBRE_COLECCION = "Usuarios";
    private final String CAMPO_NOMBRES = "nombres";
    private final String CAMPO_CORREO = "correo";
    private final String CAMPO_CONTRASEÑA = "contraseña";
    private final String CAMPO_TELEFONO = "telefono";
    private final String CAMPO_DIRECCION = "direcciones";
    private final String CAMPO_ROL = "rol";

    private static UsuariosDAO instance;    

    public static UsuariosDAO getInstance() {
        if (instance == null) {
            instance = new UsuariosDAO();
        }
        return instance;
    }
    
    @Override
    public Usuario registrarUsuario(Usuario Usuario) {
        try {
            MongoCollection<Usuario> coleccion = crearConexion();

            Usuario usuario = new Usuario();
            usuario.setId(new ObjectId());
            usuario.setNombre(Usuario.getNombre());
            usuario.setCorreo(Usuario.getCorreo());
            usuario.setPassword(Usuario.getPassword());
            usuario.setTelefono(Usuario.getTelefono());
            usuario.setDirecciones(Usuario.getDirecciones());
            usuario.setRol(Usuario.getRol());

            coleccion.insertOne(usuario);
            return usuario;

        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }

    @Override
    public List<Usuario> consultarUsuarios() {
        MongoCollection<Usuario> coleccion = crearConexion();
        return coleccion.find().into(new ArrayList<>());
    }

    @Override
    public Usuario getUsuarioCorreo(String correo) {
        MongoCollection<Usuario> coleccion = crearConexion();
        return coleccion.find(Filters.eq(CAMPO_CORREO, correo)).first();
    }

    private MongoCollection<Usuario> crearConexion() {
        MongoDatabase db = ConexionMongoDB.getConexion();
        return db.getCollection(NOMBRE_COLECCION, Usuario.class);
    }

    @Override
    public Usuario actualizarUsuario(Usuario Usuario) {
                try {
            MongoCollection<Usuario> coleccion = crearConexion();

            Document update = new Document()
                .append(CAMPO_NOMBRES, Usuario.getNombre())
                .append(CAMPO_CONTRASEÑA, Usuario.getPassword())
                .append(CAMPO_TELEFONO, Usuario.getTelefono())
                .append(CAMPO_DIRECCION, Usuario.getDirecciones())
                .append(CAMPO_ROL, Usuario.getRol());

            Document updateDoc = new Document("$set", update);

            FindOneAndUpdateOptions opciones = new FindOneAndUpdateOptions()
                .returnDocument(ReturnDocument.AFTER);

            return coleccion.findOneAndUpdate(
                Filters.eq(CAMPO_CORREO, Usuario.getCorreo()),
                updateDoc,
                opciones
            );

        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }

    @Override
    public Usuario eliminarUsuario(Usuario usuario) {
        try {
            MongoCollection<Usuario> coleccion = crearConexion();

            return coleccion.findOneAndDelete(Filters.eq(CAMPO_CORREO, usuario.getCorreo()));

        }catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }
}
