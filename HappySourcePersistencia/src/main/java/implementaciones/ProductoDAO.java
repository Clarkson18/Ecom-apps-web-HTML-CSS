//package implementaciones;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.bson.Document;
//import org.bson.conversions.Bson;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.model.FindOneAndUpdateOptions;
//import com.mongodb.client.model.ReturnDocument;
//
//import conexion.ConexionMongoDB;
//import definiciones.IProductoDAO;
//import entidades.Producto;
//
//public class ProductoDAO implements IProductoDAO {
//
//    private final String NOMBRE_COLECCION = "Productos";
//    private final String CAMPO_NOMBRES = "nombres";
//    private final String CAMPO_DESCRIPCION = "descripcion";
//    private final String CAMPO_PRECIO = "precio";
//    private final String CAMPO_CATEGORIA = "categoria";
//    private final String CAMPO_IMAGEN = "imagen";
//    @Override
//    public List<Producto> listaProductos() {
//        MongoCollection<Producto> coleccion = crearConexion();
//        List<Producto> productos = new ArrayList<>();
//        coleccion.find().into(productos);
//        return productos;
//    }
//
//    @Override
//    public Producto eliminarProducto(Producto producto) {
//        try {
//            MongoCollection<Producto> coleccion = crearConexion();
//
//            Bson filtro = Filters.eq("_id", producto.getId());
//
//            Producto productoEliminado = coleccion.findOneAndDelete(filtro);
//
//            if (productoEliminado == null) {
//                throw new RuntimeException("No se encontró el producto con ID: " + producto.getId());
//            }
//
//            return productoEliminado;
//
//        } catch (Exception e) {
//            System.err.println("Error al eliminar producto: " + e.getMessage());
//            throw new RuntimeException("Error de base de datos", e);
//        }
//    }
//
//    @Override
//    public Producto agregarProducto(Producto producto) {
//        MongoCollection coleccion = crearConexion();
//
//        coleccion.insertOne(producto);
//
//        return producto;
//    }
//
//    @Override
//    public Producto actualizarProducto(Producto producto) {
//        try{
//            FindOneAndUpdateOptions opciones = new FindOneAndUpdateOptions()
//                    .upsert(false)
//                    .returnDocument(ReturnDocument.AFTER);
//
//            Document updateSet = new Document();
//            updateSet.append(CAMPO_NOMBRES, cliente.getNombres());
//            updateSet.append(CAMPO_APELLIDO, cliente.getApellidos());
//            updateSet.append(CAMPO_CORREO, cliente.getEmail());
//            updateSet.append(CAMPO_TELEFONO, cliente.getNumeroTelefono());
//
//            Document update = new Document("$set", updateSet);
//
//            MongoCollection<Cliente> coleccion = crearConexion();
//
//            Bson filtro = Filters.eq("_id", cliente.getId());
//
//            Cliente clienteActualizado = coleccion.findOneAndUpdate(filtro, update, opciones);
//
//            if (clienteActualizado == null) {
//                throw new RuntimeException("No se encontró el cliente con ID: " + cliente.getId());
//            }
//
//            return clienteActualizado;
//
//        } catch (Exception e) {
//            System.err.println("Error al eliminar cliente: " + e.getMessage());
//            throw new RuntimeException("Error de base de datos", e);
//        }
//    }
//
//    @Override
//    public Producto obtenerProductoPorId(String id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from
//                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    private MongoCollection crearConexion() {
//        MongoDatabase db = ConexionMongoDB.getConexion();
//        MongoCollection<Producto> coleccion = db.getCollection(NOMBRE_COLECCION, Producto.class);
//        return coleccion;
//    }
//}