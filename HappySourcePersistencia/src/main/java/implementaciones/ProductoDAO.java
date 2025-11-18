package implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

import conexion.ConexionMongoDB;
import definiciones.IProductoDAO;
import entidades.Producto;

public class ProductoDAO implements IProductoDAO {

    private final String NOMBRE_COLECCION = "Productos";
    private final String CAMPO_NOMBRES = "nombres";
    private final String CAMPO_DESCRIPCION = "descripcion";
    private final String CAMPO_PRECIO = "precio";
    private final String CAMPO_CATEGORIA = "categoria";
    private final String CAMPO_CANTIDAD = "cantidad";
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
        MongoCollection coleccion = crearConexion();

        coleccion.insertOne(producto);

        return producto;
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        try{
            FindOneAndUpdateOptions opciones = new FindOneAndUpdateOptions()
                    .upsert(false)
                    .returnDocument(ReturnDocument.AFTER);

            Document updateSet = new Document();
            updateSet.append(CAMPO_NOMBRES, producto.getNombre());
            updateSet.append(CAMPO_DESCRIPCION, producto.getDesripcionProducto());
            updateSet.append(CAMPO_PRECIO, producto.getPrecio());
            updateSet.append(CAMPO_CATEGORIA, producto.getCategoria());
            updateSet.append(CAMPO_CANTIDAD, producto.getCantidadExistencia());

            Document update = new Document("$set", updateSet);

            MongoCollection<Producto> coleccion = crearConexion();

            Bson filtro = Filters.eq("_id", producto.getId());

            Producto productoActualizado = coleccion.findOneAndUpdate(filtro, update, opciones);

            if (productoActualizado == null) {
                throw new RuntimeException("No se encontró el producto con ID: " + producto.getId());
            }

            return productoActualizado;

        } catch (Exception e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            throw new RuntimeException("Error de base de datos", e);
        }
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