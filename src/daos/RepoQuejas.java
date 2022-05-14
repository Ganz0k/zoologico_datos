/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.ConexionBD;
import entidades.Queja;

/**
 *
 * @author julio
 */
public class RepoQuejas {

    private final MongoDatabase baseDatos;

    /**
     * Constructor de al clase que inicializa el atributo baseDatos del tipo
     * MongoDatabase gracias a que el método crearBade genera un objeto de tipo
     * MongoDatabase.
     */
    public RepoQuejas() {
        this.baseDatos = ConexionBD.crearBade();
    }

    /**
     * Método privado que regresa la colección "quejas" y la sujeta al formato de
     * la clase Queja.
     *
     * @return coleccion "quejas" ligada al formato de la clase Queja.
     */
    private MongoCollection<Queja> getColeccion() {
        return this.baseDatos.getCollection("quejas", Queja.class);
    }

    /**
     * Método que guarda una Queja dada por el parámetro, en su colección
     * correspondiente.
     *
     * @param queja Queja a guardar en la colección "quejas"
     * @return true en caso de lograr guardar, false en caso contrario
     */
    public boolean guardarQueja(Queja queja) {
        try {
            MongoCollection<Queja> coleccion = this.getColeccion();
            coleccion.insertOne(queja);
            return true;
        } catch (Exception ex) {
            System.err.println("No se pudo agregar la queja");
            ex.printStackTrace();
            return false;
        }
    }
}
