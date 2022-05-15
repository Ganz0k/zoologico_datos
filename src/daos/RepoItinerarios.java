/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionBD;
import entidades.Guia;
import entidades.Itinerario;
import implementaciones.FDatos;
import interfaces.IDatos;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de Itinerarios, actúa como una clase que utiliza la conexión
 * generada con la base de datos para hacer operaciones de adición y consulta en
 * la colección itinerarios.
 *
 * @author PC OSCAR
 */
public class RepoItinerarios {

    private final MongoDatabase baseDatos;

    /**
     * Constructor de la clase que inicializa el atributo baseDatos del tipo
     * MongoDatabase gracias a que el método crearBade genera un objeto de tipo
     * MongoDatabase.
     */
    public RepoItinerarios() {
        this.baseDatos = ConexionBD.crearBade();
    }

    /**
     * Método privado que regresa la colección "itinerarios" y la sujeta al
     * formato de la clase Itinerario.
     *
     * @return colección "itinerarios" ligada al formato de la clase Itinerario.
     */
    private MongoCollection<Itinerario> getColeccion() {
        return this.baseDatos.getCollection("itinerarios", Itinerario.class);
    }

    /**
     * Método que guarda un Itinerario dado por el parámetro, en su colección
     * correspondiente.
     *
     * @param itinerario Itinerario a guardar en la colección "itinerarios"
     * @return true en caso de lograr guardar, false en caso contrario.
     */
    public boolean guardarItinerario(Itinerario itinerario) {
        try {
            MongoCollection<Itinerario> coleccion = this.getColeccion();
            coleccion.insertOne(itinerario);
            return true;
        } catch (Exception ex) {
            System.err.println("No se pudo agregar el itinerario");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Método que hace una consulta a la colección itinerarios de la base de
     * datos, la consulta que realiza es buscar si hay un itinerario guardado en
     * ella que coincida con el nombre dado como parámetro, regresa un
     * itinerario en el caso de que encuentre uno, null en caso contrario.
     *
     * @param nombre nombre a comparar con los nombres de los itinerarios en la
     * base de datos.
     * @return un itinerario en el caso de que encuentre uno, null en el caso
     * contrario.
     */
    public Itinerario verificarNombreItinerario(String nombre) {
        MongoCollection<Itinerario> coleccion = this.getColeccion();
        Itinerario itinerario = coleccion.find(Filters.eq("nombre", nombre)).first();
        return itinerario;
    }

    /**
     * Método que realiza una consulta de todos los itinerarios registrados en
     * la base de datos, añade a todos los itinerarios de la colección en una
     * lista y los devuelve al sistema. Regresa null en caso de que no encuentre
     * itinerarios.
     *
     * @return lista con todos los itinerarios en la base de datos, null en caso
     * de que no se encuentre nada
     */
    public List<Itinerario> consultarItinerarios() {
        MongoCollection<Itinerario> coleccion = this.getColeccion();
        List<Itinerario> listaItinerarios = new ArrayList<>();
        coleccion.find().into(listaItinerarios);
        return listaItinerarios;
    }

    /**
     * Método que busca un guía según su itinerario en la lista de guías
     *
     * @param itinerario El itinerario desde el que se buscará el guía
     * @return El guía si hay uno que coincidam, nulo de forma contraria.
     */
    public Guia consultarGuia(Itinerario itinerario) {
        IDatos fDatos = new FDatos();
        List<Guia> guias = fDatos.consultarGuias();

        for (Guia guia : guias) {
            List<Itinerario> itinerarios = guia.getItinerarios();
            for (Itinerario itinerario1 : itinerarios) {
                if (itinerario1.equals(itinerario)) {
                    return guia;
                }
            }

        }

        return null;
    }
}
