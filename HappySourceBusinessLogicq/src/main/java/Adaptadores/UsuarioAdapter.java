/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adaptadores;

import DTOs.UsuarioDTO;
import DTOs.UsuarioLogueadoDTO;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vv094
 */
public class UsuarioAdapter {

    public static UsuarioLogueadoDTO toLogueadoDTO(Usuario usuario) {
        UsuarioLogueadoDTO dto = new UsuarioLogueadoDTO(
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getId().toString(),
                usuario.getRol(),
                usuario.getDirecciones(),
                usuario.getTelefono()
        );
        return dto;

    }

    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombreCompleto());
        usuario.setCorreo(dto.getCorreoElectronico());
        usuario.setRol(dto.getRol());
        usuario.setDirecciones(dto.getDirecciones());
        usuario.setTelefono(dto.getTelefono());

        return usuario;
    }

    public static Usuario toEntityLogueado(UsuarioLogueadoDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setId(new org.bson.types.ObjectId(dto.getId()));
        usuario.setRol(dto.getRol());
        usuario.setDirecciones(dto.getDirecciones());
        usuario.setTelefono(dto.getTelefono());
        return usuario;
    }

    public static List<UsuarioLogueadoDTO> toDTOList(List<Usuario> usuarios) {
        List<UsuarioLogueadoDTO> dtoList = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            dtoList.add(toLogueadoDTO(usuario));
        }
        return dtoList;
    }
}
