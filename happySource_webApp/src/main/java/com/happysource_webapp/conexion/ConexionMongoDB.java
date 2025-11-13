/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happysource_webapp.conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author abrilislas
 */
public class ConexionMongoDB {
    
    private static final String URI = "mongodb://localhost:27017"; 
    private static final String DB_NAME = "HappySource_DATABASE";
    private static MongoClient cliente = null;

    public static MongoDatabase getConexion() {
        if (cliente == null) {
            cliente = MongoClients.create(URI);
        }
        return cliente.getDatabase(DB_NAME);
    }

    public static void cerrarConexion() {
        if (cliente != null) {
            cliente.close();
            cliente = null;
        }
    }
}
