package adaptadores;

import java.util.ArrayList;
import java.util.List;

import dtos.ProductoDTO;
import entidades.Producto;

public class ProductoAdapter {
    public static ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId().toString());
        dto.setNombre(producto.getNombre());
        dto.setDesripcionProducto(producto.getDesripcionProducto());
        dto.setPrecio(producto.getPrecio());
        dto.setCantidadExistencia(producto.getCantidadExistencia());
        dto.setCategoria(producto.getCategoria());
        return dto;
    } 

    public static Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(new org.bson.types.ObjectId(dto.getId()));
        producto.setNombre(dto.getNombre());
        producto.setDesripcionProducto(dto.getDesripcionProducto());
        producto.setPrecio(dto.getPrecio());
        producto.setCantidadExistencia(dto.getCantidadExistencia());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }

    public static List<ProductoDTO> toDTOList(List<Producto> productos) {
        List<ProductoDTO> dtoList = new ArrayList<>();
        for (Producto producto : productos) {
            dtoList.add(toDTO(producto));
        }
        return dtoList;
    }
}
