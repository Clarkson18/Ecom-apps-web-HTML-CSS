package entidades;

import Enumeradores.EstadoReview;
import java.util.Date;
import org.bson.types.ObjectId;

public class Review {

    private ObjectId id;
    private ObjectId productoId;
    private String correoUsuario;
    private String comentario;
    private int calificacion; // 1-5
    private EstadoReview estado; // PENDIENTE/APROBADA/RECHAZADA
    private Date fecha;

    public Review() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProductoId() {
        return productoId;
    }

    public void setProductoId(ObjectId productoId) {
        this.productoId = productoId;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public EstadoReview getEstado() {
        return estado;
    }

    public void setEstado(EstadoReview estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
