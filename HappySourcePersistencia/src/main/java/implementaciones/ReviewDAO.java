package implementaciones;

import Enumeradores.EstadoReview;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import conexion.ConexionMongoDB;
import definiciones.IReviewDAO;
import entidades.Review;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class ReviewDAO implements IReviewDAO {

    private final String NOMBRE_COLECCION = "Reviews";
    private final String CAMPO_ID = "_id";
    private final String CAMPO_ESTADO = "estado";

    @Override
    public List<Review> listarReviews() {
        MongoCollection<Review> col = crearConexion();
        return col.find().into(new ArrayList<>());
    }

    @Override
    public Review actualizarEstadoReview(String idReview, EstadoReview estado) {
        MongoCollection<Review> col = crearConexion();

        FindOneAndUpdateOptions opciones = new FindOneAndUpdateOptions()
                .upsert(false)
                .returnDocument(ReturnDocument.AFTER);

        Document updateSet = new Document();
        updateSet.append(CAMPO_ESTADO, estado.name());

        Document update = new Document("$set", updateSet);

        Bson filtro = Filters.eq(CAMPO_ID, new ObjectId(idReview));
        Review actualizado = col.findOneAndUpdate(filtro, update, opciones);

        if (actualizado == null) {
            throw new RuntimeException("No se encontró la reseña con ID: " + idReview);
        }
        return actualizado;
    }

    @Override
    public Review eliminarReview(String idReview) {
        MongoCollection<Review> col = crearConexion();
        Review eliminado = col.findOneAndDelete(Filters.eq(CAMPO_ID, new ObjectId(idReview)));

        if (eliminado == null) {
            throw new RuntimeException("No se encontró la reseña con ID: " + idReview);
        }
        return eliminado;
    }

    @Override
    public Review agregarReview(Review review) {
        MongoCollection<Review> col = crearConexion();

        if (review.getId() == null) {
            review.setId(new ObjectId());
        }
        if (review.getEstado() == null) {
            review.setEstado(EstadoReview.PENDIENTE);
        }
        if (review.getFecha() == null) {
            review.setFecha(new Date());
        }

        col.insertOne(review);
        return review;
    }

    private MongoCollection<Review> crearConexion() {
        MongoDatabase db = ConexionMongoDB.getConexion();
        return db.getCollection(NOMBRE_COLECCION, Review.class);
    }

    public List<Review> listarAprobadasPorProducto(ObjectId productoId) {
        MongoCollection<Review> col = crearConexion();
        return col.find(
                Filters.and(
                        Filters.eq("productoId", productoId),
                        Filters.eq("estado", EstadoReview.APROBADA.name())
                )
        ).into(new ArrayList<>());
    }

    public List<Review> listarPorProductoYCorreo(ObjectId productoId, String correoUsuario) {
        MongoCollection<Review> col = crearConexion();
        return col.find(
                Filters.and(
                        Filters.eq("productoId", productoId),
                        Filters.eq("correoUsuario", correoUsuario)
                )
        ).into(new ArrayList<>());
    }
}
