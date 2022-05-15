/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionBD;
import entidades.Itinerario;
import entidades.Guia;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Repositorio de Guias, actúa como una clase que utiliza la conexión generada
 * con la base de datos para hacer operaciones de adición, consulta y edición en
 * la coleción.
 *
 * @author PC OSCAR
 */
public class RepoGuias {

    private final MongoDatabase basedatos;

    /**
     * Constructor de la clase que inicializa el atributo baseDatos del tipo
     * MongoDatabase gracias a que el método crearBade genera un objeto de tipo
     * MongoDatabase
     */
    public RepoGuias() {
        this.basedatos = ConexionBD.crearBade();
    }

    /**
     * Método privado que regresa la colección "guias" y la sujeta al formato de
     * la clase Guia
     *
     * @return colección "guias" y la sujetada al formato de la clase Guia
     */
    private MongoCollection<Guia> getColeccion() {
        return this.basedatos.getCollection("guias", Guia.class);
    }

    /**
     * Método que realiza una consulta de todos los guias registrado en la base
     * de datos, añade a todos los guías de la colección en una lista y los
     * devuelve al sistema. Regresa null en caso de que no encuentre guias.
     *
     * @return lista con todos los cuidadores en la base de datos, null en caso
     * de que no se encuentre nada
     */
    public List<Guia> consultarGuias() {
        MongoCollection<Guia> coleccion = this.getColeccion();
        List<Guia> listaGuias = new ArrayList<>();
        coleccion.find().into(listaGuias);
        return listaGuias;
    }

    /**
     * Añade un itinerario al atributo itinerarios de un guía.
     *
     * @param itinerario Itinerario a añadir en la lista de itinerarios
     * @param idGuia id del guia a actualizar
     */
    public void agregarItinerario(ObjectId idGuia, Itinerario itinerario) {
        MongoCollection<Guia> coleccion = this.getColeccion();
        coleccion.updateOne(Filters.eq("_id", idGuia), new Document()
                .append("$push", new Document()
                        .append("itinerarios", itinerario)));
    }

    /**
     * Método que guarda un guia en la colección guias, dado por el parametro.
     * Regresa true en caso de que se logre guardar, false en caso contrario
     *
     * @param guia guia a guardar en la colección "guias"
     * @return true en caso de que se logre guardar, false en caso contrario
     */
    public boolean guardarGuia(Guia guia) {
        try {
            MongoCollection<Guia> coleccion = this.getColeccion();
            coleccion.insertOne(guia);
            return true;
        } catch (Exception ex) {
            System.err.println("No se pudo agregar el hábitat");
            ex.printStackTrace();
            return false;
        }
    }

}
