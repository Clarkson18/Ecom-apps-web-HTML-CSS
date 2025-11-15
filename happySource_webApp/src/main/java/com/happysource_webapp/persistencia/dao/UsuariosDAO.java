/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happysource_webapp.persistencia.dao;

import com.happysource_webapp.conexion.ConexionMongoDB;
import com.happysource_webapp.dominio.UsuarioDTO;
import com.happysource_webapp.persistencia.interfaces.IUsuariosDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author abrilislas
 */
public class UsuariosDAO implements IUsuariosDAO {
    
    private final MongoCollection<Document> coleccion;
    
    public UsuariosDAO() {
        
        MongoDatabase db = ConexionMongoDB.getConexion();
        coleccion = db.getCollection("usuarios");
    }


    @Override
    public void registrarUsuario(UsuarioDTO usuarioDTO) {
        
        
        
    }

    @Override
    public void actualizarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
