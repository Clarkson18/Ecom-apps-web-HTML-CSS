package BusinessObject;

import Adaptadores.UsuarioAdapter;
import DTOs.UsuarioDTO;
import DTOs.UsuarioLogueadoDTO;
import definiciones.IUsuariosDAO;
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

    public UsuarioLogueadoDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioAdapter.toEntity(usuarioDTO);
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

        Usuario userRegistrado = usuariosDAO.registrarUsuario(usuario);

        return UsuarioAdapter.toLogueadoDTO(userRegistrado);

    }

    /**
     * Actualizar datos de usuario.
     */
    public UsuarioLogueadoDTO actualizarUsuario(UsuarioLogueadoDTO usuarioDTO) {
        Usuario usuario = UsuarioAdapter.toEntityLogueado(usuarioDTO);
        usuario = usuariosDAO.actualizarUsuario(usuario);
        return UsuarioAdapter.toLogueadoDTO(usuario);
    }

    /**
     * Eliminar usuario.
     */
    public UsuarioLogueadoDTO eliminarUsuario(UsuarioLogueadoDTO usuarioDTO) {
        Usuario usuario = UsuarioAdapter.toEntityLogueado(usuarioDTO);
        usuariosDAO.eliminarUsuario(usuario);
        return UsuarioAdapter.toLogueadoDTO(usuario);
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
