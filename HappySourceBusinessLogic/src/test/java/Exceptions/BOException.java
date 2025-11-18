/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abrilislas
 */
public class BOException extends Exception{
    
    public BOException() {
        super();
    }

    public BOException(String mensaje) {
        super(mensaje);
    }

    public BOException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    
}
