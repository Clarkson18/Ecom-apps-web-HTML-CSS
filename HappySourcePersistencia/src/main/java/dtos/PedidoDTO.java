/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import Enumeradores.EstadoEnvio;
import entidades.Producto;
import entidades.Usuario;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author abrilislas
 */
public class PedidoDTO {
    
    private Usuario usuario;
    public String direccionEnvio; 
    public EstadoEnvio estadoEnvio;
    public List<Producto> listaProductos;
    public double precioTotalEnvio;
    public LocalDateTime fechaEntrega;
    public LocalDateTime fechaPedido;

    public PedidoDTO(Usuario usuario, String direccionEnvio, List<Producto> listaProductos, double precioTotalEnvio, LocalDateTime fechaPedido) {
        this.usuario = usuario;
        this.direccionEnvio = direccionEnvio;
        this.listaProductos = listaProductos;
        this.precioTotalEnvio = precioTotalEnvio;
        this.fechaPedido = fechaPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
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


