/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Enumeradores.Rol;
import java.util.List;

/**
 *
 * @author vv094
 */
public class UsuarioDTO {
    private String nombreCompleto;
    private String alias;
    private Rol rol;
    private String correoElectronico;
    private String telefono;
    private String password;
    private List<String> direcciones;
   // private MetodoPago metodoPago;

    public UsuarioDTO(String nombreCompleto, String alias, Rol rol, String correoElectronico, String telefono, String password, List<String> direcciones) {
        this.nombreCompleto = nombreCompleto;
        this.alias = alias;
        this.rol = rol;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.password = password;
        this.direcciones = direcciones;
    }
    

    public UsuarioDTO(String nombreCompleto, String correoElectronico, String telefono, String password, List<String> direcciones) {
        this.nombreCompleto = nombreCompleto;
        this.alias = alias;
        this.rol = rol;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.password = password;
        this.direcciones = direcciones;
    }    
    

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<String> direcciones) {
        this.direcciones = direcciones;
    }
    
    public void addDireccion(String direccion){
        this.direcciones.add(direccion);
    }
    
}
