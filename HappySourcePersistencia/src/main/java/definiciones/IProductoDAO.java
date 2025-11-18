package definiciones;

import java.util.List;

import entidades.Producto;

public interface IProductoDAO {
    public List<Producto> listaProductos();
    public Producto eliminarProducto();
    public Producto agregarProducto();
    public Producto actualizarProducto();
    public Producto obtenerProductoId();
}
