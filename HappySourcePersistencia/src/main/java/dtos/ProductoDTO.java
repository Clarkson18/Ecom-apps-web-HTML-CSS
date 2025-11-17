/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import Enumeradores.Categoria;

/**
 *
 * @author abrilislas
 */
public class ProductoDTO {
    
    public String nombre;
    public String desripcionProducto;
    public double precio;
    public int cantidadExistencia;
    public Categoria categoria;

    public ProductoDTO(String nombre, String desripcionProducto, double precio, int cantidadExistencia, Categoria categoria) {
        this.nombre = nombre;
        this.desripcionProducto = desripcionProducto;
        this.precio = precio;
        this.cantidadExistencia = cantidadExistencia;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesripcionProducto() {
        return desripcionProducto;
    }

    public void setDesripcionProducto(String desripcionProducto) {
        this.desripcionProducto = desripcionProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadExistencia() {
        return cantidadExistencia;
    }

    public void setCantidadExistencia(int cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
