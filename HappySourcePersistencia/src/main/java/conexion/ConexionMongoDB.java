/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author vv094
 */
public class ConexionMongoDB {

    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "HappySource_DATABASE";
    private static MongoClient cliente = null;

    public ConexionMongoDB() {

    }

//singleton
    public static MongoDatabase getConexion() {
        if (cliente == null) {
            CodecRegistry pojo = CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

            MongoClientSettings clienteSettings = MongoClientSettings.builder()
                    .applyConnectionString(new com.mongodb.ConnectionString(URI))
                    .codecRegistry(pojo)
                    .build();

            // Creación del cliente
            cliente = MongoClients.create(clienteSettings);

            // Retorna la base de datos con los codecs configurados
            return cliente.getDatabase(DB_NAME).withCodecRegistry(pojo);
        }

        // Si el cliente ya fue creado, retorna la base directamente
        return cliente.getDatabase(DB_NAME) ;
    }

    public static void cerrarConexion() {
        if (cliente != null) {
            cliente.close();
            cliente = null;
        }
    }
}
