package entidades;

import java.util.List;

import org.bson.types.ObjectId;

import Enumeradores.Rol;

public class Usuario {
    private String nombre;
    private String correo;
    private String password;
    private String telefono;
    private List<String> direcciones;
    private ObjectId id;
    private Rol rol;
    
    //constructor vacio
    public Usuario(){}
    
    public Usuario(String nombre, String correo, String password, String telefono, List<String> direcciones, Rol rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.direcciones = direcciones;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<String> direcciones) {
        this.direcciones = direcciones;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
