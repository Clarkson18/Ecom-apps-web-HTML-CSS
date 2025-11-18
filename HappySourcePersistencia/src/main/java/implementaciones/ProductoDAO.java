package implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import conexion.ConexionMongoDB;
import definiciones.IProductoDAO;
import entidades.Producto;

public class ProductoDAO implements IProductoDAO {

    private final String NOMBRE_COLECCION = "Productos";

    @Override
    public List<Producto> listaProductos() {
        MongoCollection<Producto> coleccion = crearConexion();
        List<Producto> productos = new ArrayList<>();
        coleccion.find().into(productos);
        return productos;
    }

    @Override
    public Producto eliminarProducto(Producto producto) {
        try {
            MongoCollection<Producto> coleccion = crearConexion();

            Bson filtro = Filters.eq("_id", producto.getId());

            Producto productoEliminado = coleccion.findOneAndDelete(filtro);

            if (productoEliminado == null) {
                throw new RuntimeException("No se encontró el producto con ID: " + producto.getId());
            }

            return productoEliminado;

        } catch (Exception e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            throw new RuntimeException("Error de base de datos", e);
        }
    }

    @Override
    public Producto agregarProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto obtenerProductoPorId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private MongoCollection crearConexion() {
        MongoDatabase db = ConexionMongoDB.getConexion();
        MongoCollection<Producto> coleccion = db.getCollection(NOMBRE_COLECCION, Producto.class);
        return coleccion;
    }
}