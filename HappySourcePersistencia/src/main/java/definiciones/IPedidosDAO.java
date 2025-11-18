package definiciones;

import java.util.List;
import Enumeradores.EstadoEnvio;
import entidades.Pedido;

public interface IPedidosDAO {
    public Pedido actualizarEstadoPedido(String idPedido, EstadoEnvio nuevoEstado);
    public List<Pedido> consultarPedidos(String idUsuario);
    public Pedido crearPedido(Pedido pedido);
    public Pedido obtenerPedidoPorId(String idPedido);
}
