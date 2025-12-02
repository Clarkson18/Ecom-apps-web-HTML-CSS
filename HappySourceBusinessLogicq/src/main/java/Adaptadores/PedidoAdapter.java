/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adaptadores;

import DTOs.PedidoDTO;
import entidades.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vv094
 */
public class PedidoAdapter {
    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setUsuario(pedido.getUsuario());
        pedidoDTO.setDireccionEnvio(pedido.getDireccionEnvio());
        pedidoDTO.setEstadoEnvio(pedido.getEstadoEnvio());
        pedidoDTO.setListaProductos(pedido.getListaProductos());
        pedidoDTO.setPrecioTotalEnvio(pedido.getPrecioTotalEnvio());
        pedidoDTO.setFechaEntrega(pedido.getFechaEntrega());
        pedidoDTO.setFechaPedido(pedido.getFechaPedido());
        pedidoDTO.setId(pedido.getId().toString());

        return pedidoDTO;
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido(
                pedidoDTO.getUsuario(),
                pedidoDTO.getDireccionEnvio(),
                pedidoDTO.getListaProductos(),
                pedidoDTO.getPrecioTotalEnvio()
        );

        return pedido;
    }
    
    public static Pedido toEntityCompleto(PedidoDTO dto){
        Pedido pedido = new Pedido();
        pedido.setUsuario(dto.getUsuario());
        pedido.setDireccionEnvio(dto.getDireccionEnvio());
        pedido.setEstadoEnvio(dto.getEstadoEnvio());
        pedido.setListaProductos(dto.getListaProductos());
        pedido.setPrecioTotalEnvio(dto.getPrecioTotalEnvio());
        pedido.setFechaEntrega(dto.getFechaEntrega());
        pedido.setFechaPedido(dto.getFechaPedido());
        pedido.setId(new org.bson.types.ObjectId(dto.getId()));
        
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
