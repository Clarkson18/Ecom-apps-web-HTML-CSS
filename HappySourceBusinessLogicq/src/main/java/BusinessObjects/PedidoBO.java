package BusinessObjects;

import Enumeradores.EstadoEnvio;
import definiciones.IPedidosDAO;
import definiciones.IPersistencia;
import entidades.Pedido;
import implementaciones.PedidosDAO;
import implementaciones.Persistencia;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author abrilislas
 */
public class PedidoBO {
    
    IPedidosDAO pedidosDAO = new PedidosDAO();
    PedidoBO pedidoBO;
    private static final Logger LOG = Logger.getLogger(AutenticacionBO.class.getName());
    
    
    public PedidoBO getInstance(){
        
        if (this.pedidoBO==null){
            this.pedidoBO = new PedidoBO();
        }
        return pedidoBO;
    }
    
    public PedidoBO(){
        getInstance();
    }

    public Pedido actualizarEstadoPedido(String idPedido, EstadoEnvio nuevoEstado) {

        if (idPedido == null || idPedido.isBlank()) {
            throw new IllegalArgumentException("El ID del pedido no puede ser nulo o vacío.");
        }
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo.");
        }

        Pedido pedidoActual = pedidosDAO.obtenerPedidoPorId(idPedido);

        if (pedidoActual == null) {
            throw new RuntimeException("No existe un pedido con ese ID.");
        }

        if (pedidoActual.getEstadoEnvio() == EstadoEnvio.ENTREGADO) {
            throw new IllegalStateException("No se puede modificar un pedido ya entregado.");
        }

        return pedidosDAO.actualizarEstadoPedido(idPedido, nuevoEstado);
    }
    
    public List<Pedido> consultarPedidos() {
        return pedidosDAO.consultarTodosLosPedidos();
    }
    
    public Pedido crearPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
        if (pedido.getUsuario() == null || pedido.getUsuario().getId() == null) {
            throw new IllegalArgumentException("El pedido debe contener un usuario válido.");
        }
        if (pedido.getListaProductos() == null || pedido.getListaProductos().isEmpty()) {
            throw new IllegalArgumentException("Un pedido debe contener al menos un producto.");
        }

        pedido.setEstadoEnvio(EstadoEnvio.PROCESANDO_PAGO);

        return pedidosDAO.crearPedido(pedido);
    }
    
    public Pedido obtenerPedidoPorId(String idPedido) {
        if (idPedido == null || idPedido.isBlank()) {
            throw new IllegalArgumentException("El ID del pedido no puede ser nulo o vacío.");
        }
        return pedidosDAO.obtenerPedidoPorId(idPedido);
    }
}

    
    
    

