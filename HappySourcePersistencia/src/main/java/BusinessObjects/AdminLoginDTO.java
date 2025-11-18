/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessObjects;
import com.mycompany.Exceptions.BusinessException;
import definiciones.IProductoDAO;
import dtos.ProductoDTO;

/**
 *
 * @author abrilislas
 */
public class ProductosBO {
    
    private IProductoDAO productoDAO;

    public ProductosBO(IProducotDAO productoDAO) {
        this.productoDAO = productoDAO;
    }
    
    private String MENSAJE_ERROR="Hubo un error al procesar la solicitud en la capa de negocios";
    private static final System.Logger LOG = System.getLogger(ProductosBO.class.getName());
    
    public void registrarProducto(ProductoDTO productoDTO) throws BusinessException{
           
        if (productoDTO.Precio < 0) {
            throw new BusinessException(MENSAJE_ERROR+": El precio no puede ser negativo "+LOG);
        }
        productDAO.insert(productoDTO);
    }
    
    
    public eliminarProducto(ProductoDTO productoDTO){
    
    
    }
    
}