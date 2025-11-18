package definiciones;

import java.util.List;

import entidades.Producto;

public interface IProductoDAO {
    public List<Producto> listaProductos();
    public Producto eliminarProducto(Producto producto);
    public Producto agregarProducto(Producto producto);
    public Producto actualizarProducto(Producto producto);
    public Producto obtenerProductoPorId(String id);
}
