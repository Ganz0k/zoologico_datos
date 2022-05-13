/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionBD;
import entidades.Especie;

/**
 * Repositorio de Especies, actúa como una clase que utiliza la conexión
 * generada con la base de datos para hacer operaciones de añadición y consulta
 * en la colección especies
 *
 * @author luisg
 */
public class RepoEspecies {

    private final MongoDatabase baseDatos;

    /**
     * Constructor de la clase que inicializa el atributo baseDatos del tipo
     * MongoDatabase gracias a que el método crearBade genera un objeto de tipo
     * MongoDatabase
     */
    public RepoEspecies() {
        this.baseDatos = ConexionBD.crearBade();
    }

    /**
     * Método privado que regresa la colección "especies" y la sujeta al formato
     * de la clase Especie
     *
     * @return colección "especies" y la sujeta al formato de la clase Especie
     */
    private MongoCollection<Especie> getColeccion() {
        return this.baseDatos.getCollection("especies", Especie.class);
    }

    /**
     * Método que hace una consulta a la colección especies de la base de datos,
     * la consulta que realiza es buscar si hay una especie guaradada en ella
     * que coincida con el nombre dado como parámetro, regresa una especie en el
     * caso de que encuentre una, null en el caso contrario
     *
     * @param nombre nombre a comparar con los nombres en español de las
     * especies en la base de datos
     * @return una especie en el caso de que encuentre una, null en el caso
     * contrario
     */
    public Especie consultarEspecie(String nombre) {
        MongoCollection<Especie> coleccion = this.getColeccion();
        Especie especie = coleccion.find(Filters.eq("nombreEspaniol", nombre)).first();
        return especie;
    }

    /**
     * Método que hace una consulta a la colección especies de la base de datos,
     * la consulta que realiza es buscar si hay una especie guardada en ella que
     * coincida con el nombre científico de la especie dada como parámetro,
     * regresa una especie en el caso de que encuentre una, null en caso
     * contrario
     *
     * @param especie especie la cual proveerá el nombre científico para
     * comparar con las especies en la base de datos
     * @return una especie en el caso de que encuentre una, null en el caso
     * contrario
     */
    public Especie consultarEspecie(Especie especie) {
        MongoCollection<Especie> coleccion = this.getColeccion();
        Especie especie2 = coleccion.find(Filters.eq("nombreCientifico", especie.getNombreCientifico())).first();
        return especie2;
    }

    /**
     * Método que guarda una especie que le es mandada como parámetro en la
     * colección especies. Regresa true en el caso de que se logre guardar,
     * false en caso contrario
     *
     * @param especie especie a guardar en la colección "especies"
     * @return true en caso de que se logre guardar, false en caso contrario
     */
    public boolean guardarEspecie(Especie especie) {
        try {
            MongoCollection<Especie> coleccion = this.getColeccion();
            coleccion.insertOne(especie);
            return true;
        } catch (Exception ex) {
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }
    }
}
