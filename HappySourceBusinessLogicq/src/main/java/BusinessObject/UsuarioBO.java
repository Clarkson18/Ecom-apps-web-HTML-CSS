package BusinessObject;

import definiciones.IUsuariosDAO;
import dtos.UsuarioDTO;
import entidades.Usuario;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abrilislas
 */

public class UsuarioBO {

    private final IUsuariosDAO usuariosDAO;

    public UsuarioBO(IUsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }
    
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {

        if (usuarioDTO == null) {
            throw new IllegalArgumentException("El DTO de usuario no puede ser nulo.");
        }
        if (usuarioDTO.getCorreoElectronico() == null || usuarioDTO.getCorreoElectronico().isBlank()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }
        if (usuarioDTO.getCorreoElectronico() == null || usuarioDTO.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }
        if (usuarioDTO.getNombreCompleto() == null || usuarioDTO.getNombreCompleto().isBlank()) {
            throw new IllegalArgumentException("El nombre del usuario es obligatorio.");
        }

        Usuario user = usuariosDAO.getUsuarioCorreo(usuarioDTO.getCorreoElectronico());
        if (user != null) {
            throw new IllegalStateException("Ya existe un usuario con este correo.");
        }

        return usuariosDAO.registrarUsuario(usuarioDTO);
    }

    /**
     * Actualizar datos de usuario.
     */
    public Usuario actualizarUsuario(UsuarioDTO usuarioDTO) {
        return usuariosDAO.actualizarUsuario(usuarioDTO);
    }

    /**
     * Eliminar usuario.
     */
    public Usuario eliminarUsuario(UsuarioDTO usuarioDTO) {
        String correoElectronico = usuarioDTO.getCorreoElectronico();
        return usuariosDAO.eliminarUsuario(usuarioDTO, correoElectronico);
    }

    /**
     * Obtener todos los usuarios registrados.
     */
    public List<Usuario> consultarUsuarios() {
        return usuariosDAO.consultarUsuarios();
    }

    /**
     * 
     * Obtener un usuario por correo
     */
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }
        return usuariosDAO.getUsuarioCorreo(correo);
    }
}

