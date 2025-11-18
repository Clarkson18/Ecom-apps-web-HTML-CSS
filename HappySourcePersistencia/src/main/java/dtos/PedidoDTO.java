/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import Enumeradores.EstadoEnvio;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author abrilislas
 */
public class PedidoDTO {
    
    private UsuarioDTO usuario;
    public String direccionEnvio; 
    public EstadoEnvio estadoEnvio;
    public List<ProductoDTO> listaProductos;
    public double precioTotalEnvio;
    public LocalDateTime fechaEntrega;
    public LocalDateTime fechaPedido;

    public PedidoDTO(UsuarioDTO usuario, String direccionEnvio, List<ProductoDTO> listaProductos, double precioTotalEnvio, LocalDateTime fechaPedido) {
        this.usuario = usuario;
        this.direccionEnvio = direccionEnvio;
        this.listaProductos = listaProductos;
        this.precioTotalEnvio = precioTotalEnvio;
        this.fechaPedido = fechaPedido;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public EstadoEnvio getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public List<ProductoDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoDTO> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public double getPrecioTotalEnvio() {
        return precioTotalEnvio;
    }

    public void setPrecioTotalEnvio(double precioTotalEnvio) {
        this.precioTotalEnvio = precioTotalEnvio;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    
}


