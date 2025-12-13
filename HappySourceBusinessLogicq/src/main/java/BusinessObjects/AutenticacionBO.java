package BusinessObjects;

import Adaptadores.UsuarioAdapter;
import DTOs.UsuarioLogueadoDTO;
import entidades.Usuario;
import implementaciones.Persistencia;
import java.util.logging.Logger;

public class AutenticacionBO {

    private final Persistencia persistencia = new Persistencia();
    private static final Logger LOG = Logger.getLogger(AutenticacionBO.class.getName());

    public UsuarioLogueadoDTO iniciarSesion(String correo, String contraseña) {
        try {
            if (correo != null && !correo.isBlank() && contraseña != null && !contraseña.isBlank()) {
                Usuario usuario = persistencia.loginUsuario(correo, contraseña);
                return UsuarioAdapter.toLogueadoDTO(usuario);
            }
        } catch (Exception ex) {
            LOG.severe("Ha ocurrido un error al autenticar el usuario: " + ex.getMessage());
        }
        return null;
    }
}
