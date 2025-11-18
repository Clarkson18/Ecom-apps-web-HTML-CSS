package interfaces;

import dtos.ProductoDTO;
import java.util.List;

public interface IProductoDAO {
    public List<ProductoDTO> listaProductos();
    public ProductoDTO eliminarProducto();
    public ProductoDTO agregarProducto();
    public ProductoDTO actualizarProducto();
    public ProductoDTO obtenerProductoId();
}
