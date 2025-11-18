package implementaciones;

import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

import Enumeradores.EstadoEnvio;
import conexion.ConexionMongoDB;
import definiciones.IPedidosDAO;
import entidades.Pedido;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class PedidosDAO implements IPedidosDAO {

    private final String NOMBRE_COLECCION = "Pedidos";
    private final String CAMPO_ESTADO_ENVIO = "estadoEnvio";
    private final String CAMPO_ID = "_id";
    private final String CAMPO_ID_USUARIO = "usuario._id";

    @Override
    public Pedido actualizarEstadoPedido(String idPedido, EstadoEnvio nuevoEstado) {
        try {
            MongoCollection<Pedido> coleccion = crearConexion();

            FindOneAndUpdateOptions opciones = new FindOneAndUpdateOptions()
                    .upsert(false)
                    .returnDocument(ReturnDocument.AFTER);

            Document updateSet = new Document();
            updateSet.append(CAMPO_ESTADO_ENVIO, nuevoEstado.name());

            Document update = new Document("$set", updateSet);

            Bson filtro = Filters.eq(CAMPO_ID, new ObjectId(idPedido));

            Pedido pedidoActualizado = coleccion.findOneAndUpdate(filtro, update, opciones);

            if (pedidoActualizado == null) {
                throw new RuntimeException("No se encontró el pedido con ID: " + idPedido);
            }

            return pedidoActualizado;

        } catch (Exception e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            throw new RuntimeException("Error de base de datos", e);
        }

    }

    @Override
    public List<Pedido> consultarPedidos(String idUsuario) {
        MongoCollection<Pedido> coleccion = crearConexion();
        
        Bson filtro = Filters.eq(CAMPO_ID_USUARIO, new ObjectId(idUsuario));

        List<Pedido> pedidos = coleccion.find(filtro).into(new java.util.ArrayList<Pedido>());

        return pedidos;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        try {
            MongoCollection<Pedido> coleccion = crearConexion();

            coleccion.insertOne(pedido);
            return pedido;

        }catch (Exception e) {
            System.err.println("Error al crear pedido: " + e.getMessage());
            throw new RuntimeException("Error de base de datos al crear el pedido", e);
        }
    }

    @Override
    public Pedido obtenerPedidoPorId(String idPedido) {
        ObjectId objectId = new ObjectId(idPedido);
        MongoCollection<Pedido> coleccion = crearConexion();
        return coleccion.find(Filters.eq(CAMPO_ID, objectId)).first();
    }

    private MongoCollection crearConexion() {
        MongoDatabase db = ConexionMongoDB.getConexion();
        MongoCollection<Pedido> coleccion = db.getCollection(NOMBRE_COLECCION, Pedido.class);
        return coleccion;
    }

    @Override
    public List<Pedido> consultarTodosLosPedidos() {
        try {
            MongoCollection<Pedido> coleccion = crearConexion();
            List<Pedido> pedidos = coleccion.find().into(new java.util.ArrayList<>());
            return pedidos;
        }catch (Exception e) {
            System.err.println("Error al consultar todos los pedidos: " + e.getMessage());
            throw new RuntimeException("Error de base de datos", e);
        }
    }
    
}
