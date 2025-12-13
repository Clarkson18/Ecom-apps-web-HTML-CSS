package definiciones;

import Enumeradores.EstadoReview;
import entidades.Review;
import java.util.List;

public interface IReviewDAO {

    List<Review> listarReviews();

    Review actualizarEstadoReview(String idReview, EstadoReview estado);

    Review eliminarReview(String idReview);

    Review agregarReview(Review review);
}
