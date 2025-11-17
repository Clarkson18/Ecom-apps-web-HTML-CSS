package definiciones;

public interface IProductoDAO {
    public List<Producto> listaProductos();
    public Producto eliminarProducto();
    public Producto agregarProducto();
    public Producto actualizarProducto();
    public Producto obtenerProductoId();
}
