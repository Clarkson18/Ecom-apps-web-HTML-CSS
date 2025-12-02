/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adaptadores;

import dtos.UsuarioDTO;
import dtos.UsuarioLogueadoDTO;
import entidades.Producto;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vv094
 */
public class UsuarioAdapter {
     public static UsuarioLogueadoDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioLogueadoDTO();
      
    } 

    public static Producto toEntity(UsuarioDTO dto) {
        Producto usuario = new Producto();
        usuario.setId(new org.bson.types.ObjectId(dto.getId()));
        usuario.setNombre(dto.getNombre());
        usuario.setDesripcionProducto(dto.getDesripcionProducto());
        usuario.setPrecio(dto.getPrecio());
        usuario.setCantidadExistencia(dto.getCantidadExistencia());
        usuario.setCategoria(dto.getCategoria());
        return usuario;
    }

    public static List<UsuarioDTO> toDTOList(List<Producto> usuarios) {
        List<UsuarioDTO> dtoList = new ArrayList<>();
        for (Producto usuario : usuarios) {
            dtoList.add(toDTO(usuario));
        }
        return dtoList;
    }
}
