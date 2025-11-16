/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import definiciones.IPersistencia;
import definiciones.IUsuariosDAO;
import dtos.AdminLogueadoDTO;
import dtos.UsuarioDTO;
import entidades.Usuario;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import utils.PassManager;

import java.util.List;

/**
 *
 * @author vv094
 */
public class Persistencia implements IPersistencia {

    private final IUsuariosDAO usuariosDAO;

    public Persistencia() {
        this.usuariosDAO = new UsuariosDAO();
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO actualizarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO eliminarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UsuarioDTO> consultarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AdminLogueadoDTO loginAdmin(String correo, String pass) {
        Usuario usuario = usuariosDAO.getUsuarioCorreo(correo);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        String password = usuario.getContraseña();

        try {
            if (PassManager.verificarPassword(pass, password) && usuario.getRol().equals("ADMIN")) {
                return new AdminLogueadoDTO(usuario.getNombre(), usuario.getCorreo(), usuario.getId().toString(), usuario.getRol());
            } else {
                throw new IllegalArgumentException("Credenciales incorrectas");

            }
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(Persistencia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw new IllegalArgumentException("Credenciales incorrectas");
        } catch (InvalidKeySpecException ex) {
            System.getLogger(Persistencia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw new IllegalArgumentException("Credenciales incorrectas");
        }
    }

}
