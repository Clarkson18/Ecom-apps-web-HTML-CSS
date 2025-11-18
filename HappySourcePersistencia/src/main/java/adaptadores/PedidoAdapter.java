package adaptadores;

import java.util.ArrayList;
import java.util.List;

import dtos.PedidoDTO;
import entidades.Pedido;

public class PedidoAdapter {
    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO(
                pedido.getUsuario(),
                pedido.getDireccionEnvio(),
                pedido.getEstadoEnvio(),
                pedido.getListaProductos(),
                pedido.getPrecioTotalEnvio(),
                pedido.getFechaEntrega(),
                pedido.getFechaPedido(),
                pedido.getId()
        );

        return pedidoDTO;
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido(
                pedidoDTO.getUsuario(),
                pedidoDTO.getDireccionEnvio(),
                pedidoDTO.getEstadoEnvio(),
                pedidoDTO.getListaProductos(),
                pedidoDTO.getPrecioTotalEnvio(),
                pedidoDTO.getFechaEntrega(),
                pedidoDTO.getFechaPedido(),
                pedidoDTO.getId()
        );

        return pedido;
    }

    public static List<PedidoDTO> toDTOList(List<Pedido> pedidos) {
        List<PedidoDTO> pedidosDTO = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            pedidosDTO.add(toDTO(pedido));
        }
        return pedidosDTO;
    }
}
