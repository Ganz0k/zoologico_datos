/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionBD;
import entidades.Habitat;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Repositorio de Hábitats, actúa como una clase que utiliza la conexión
 * generada con la base de datos para hacer operaciones de añadición y consulta
 * en la colección habitats
 *
 * @author luisg
 */
public class RepoHabitats {

    private final MongoDatabase baseDatos;

    /**
     * Constructor de la clase que inicializa el atributo baseDatos del tipo
     * MongoDatabase gracias a que el método crearBade genera un objeto de tipo
     * MongoDatabase
     */
    public RepoHabitats() {
        this.baseDatos = ConexionBD.crearBade();
    }

    /**
     * Método privado que regresa la colección "habitats" y la sujeta al formato
     * de la clase Habitat
     *
     * @return colección "habitats" y la sujetada al formato de la clase Habitat
     */
    private MongoCollection<Habitat> getColeccion() {
        return this.baseDatos.getCollection("habitats", Habitat.class);
    }

    /**
     * Método que guarda un hábitat que le es mandado como parámetro en la
     * colección hábitats. Regresa true en caso de que se logre guardar, false
     * en caso contrario
     *
     * @param habitat habitat a guardar en la colección "habitats"
     * @return true en caso de que se logre guardar, false en caso contrario
     */
    public boolean guardarHabitat(Habitat habitat) {
        try {
            MongoCollection<Habitat> coleccion = this.getColeccion();
            coleccion.insertOne(habitat);
            return true;
        } catch (Exception ex) {
            System.err.println("No se pudo agregar el hábitat");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Método que hace una consulta a la colección hábitats de la base de datos,
     * la consulta que realiza es buscar si hay un hábitat guardado en ella que
     * coincida con el nombre dado como parámetro, regresa un hábitat en el caso
     * de que encuentre uno, null en el caso contrario
     *
     * @param nombre nombre a comparar con los nombres de los hábitats en la
     * base de datos
     * @return un hábitat en el caso de que encuentre uno, null en el caso
     * contrario
     */
    public Habitat verificarNombreHabitat(String nombre) {
        MongoCollection<Habitat> coleccion = this.getColeccion();
        Habitat habitat = coleccion.find(Filters.eq("nombre", nombre)).first();
        return habitat;
    }

    /**
     * Método que realiza una consulta de todos los hábitats registrados en la
     * colección de habitats en la base de datos. Los añade todos a una lista y
     * la devuelve al sistema, regresa null en caso de que no se encuentre nada
     *
     * @return lista con todos los hábitats en la base de datos, null en caso de
     * que no se encuentre nada
     */
    public List<Habitat> consultarHabitats() {
        MongoCollection<Habitat> coleccion = this.getColeccion();
        List<Habitat> listaHabitats = new ArrayList<>();
        coleccion.find().into(listaHabitats);
        return listaHabitats;
    }

    /**
     * Añade un id de especie al atributo idsEspecie de un hábitat
     *
     * @param idHabitat id del hábitat a actualizar
     * @param idEspecie id de la especie a añadir en la lista de idsEspecie
     */
    public void agregarEspecie(ObjectId idHabitat, ObjectId idEspecie) {
        MongoCollection<Habitat> coleccion = this.getColeccion();
        coleccion.updateOne(Filters.eq("_id", idHabitat), new Document()
                .append("$push", new Document()
                        .append("idsEspecie", idEspecie)));
    }
}
