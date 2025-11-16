package dtos;

import Enumeradores.Rol;

public class AdminLogueadoDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private String id;
    private Rol rol;

    public AdminLogueadoDTO(String nombre, String apellido, String correo, String id, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.id = id;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
