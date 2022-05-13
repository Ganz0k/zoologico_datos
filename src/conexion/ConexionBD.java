/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * Clase que establece una conexión con la base de datos de nombre
 * "zoologico_bd"
 *
 * @author luisg
 */
public class ConexionBD {

    private static final String HOST = "localhost";
    private static final int PUERTO = 27017;
    private static final String BASE_DATOS = "zoologico_bd";
    public static MongoDatabase baseDatos;

    /**
     * Establece una conexión con la base de datos "zoologico_bd" y la devuelve
     * como un objeto del tipo MongoDatabase
     *
     * @return objeto del tipo MongoDatabase después de hacer conexión con la
     * base de datos "zoologico_bd"
     */
    public static MongoDatabase crearBade() {
        if (baseDatos != null) {
            return baseDatos;
        } else {
            try {
                //CONFIGURACIÓN PARA QUE MONGODRIVE REALICE EL MAPEO DE POJOS AUMATICAMENTE
                CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

                CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

                ConnectionString cadenaConexion = new ConnectionString("mongodb://" + HOST + "/" + PUERTO);

                MongoClientSettings clientsSettings = MongoClientSettings.builder()
                        .applyConnectionString(cadenaConexion)
                        .codecRegistry(codecRegistry)
                        .build();

                MongoClient clienteMongo = MongoClients.create(clientsSettings);

                baseDatos = clienteMongo.getDatabase(BASE_DATOS);

                return baseDatos;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
    }
}
