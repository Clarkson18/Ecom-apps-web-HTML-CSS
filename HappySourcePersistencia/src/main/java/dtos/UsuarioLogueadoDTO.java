package dtos;

import java.util.List;

import Enumeradores.Rol;

public class UsuarioLogueadoDTO {
    private String nombre;
    private String correo;
    private String id;
    private List<String> direcciones;
    private Rol rol;

    public UsuarioLogueadoDTO(String nombre, String correo, String id, Rol rol, List<String> direcciones) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
        this.rol = rol;
        this.direcciones = direcciones;
    }

    public List<String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<String> direcciones) {
        this.direcciones = direcciones;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }   

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
