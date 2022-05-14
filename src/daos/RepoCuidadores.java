/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionBD;
import entidades.Cuidador;
import entidades.Especie;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Repositorio de Cuidadores, actúa como una clase que utiliza la conexión
 * generada con la base de datos para hacer operaciones de añadición, consulta y
 * edición en la colección
 *
 * @author luisg
 */
public class RepoCuidadores {

    private final MongoDatabase basedatos;

    /**
     * Constructor de la clase que inicializa el atributo baseDatos del tipo
     * MongoDatabase gracias a que el método crearBade genera un objeto de tipo
     * MongoDatabase
     */
    public RepoCuidadores() {
        this.basedatos = ConexionBD.crearBade();
    }

    /**
     * Método privado que regresa la colección "empleados" y la sujeta al
     * formato de la clase Cuidador
     *
     * @return colección "empleados" y la sujetada al formato de la clase
     * Cuidador
     */
    private MongoCollection<Cuidador> getColeccion() {
        return this.basedatos.getCollection("empleados", Cuidador.class);
    }

    /**
     * Método que guarda un cuidador dado por el parámetro, en su colección 
     * correspondiente. 
     * 
     * @param cuidador Cuidador a guardar en la colección "zonas"
     * @return true en caso de lograr guardar, false en caso contrario
     */
    public boolean guardarCuidador(Cuidador cuidador){
        try {
            MongoCollection<Cuidador> coleccion = this.getColeccion();
            coleccion.insertOne(cuidador);
            return true;
        } catch (Exception ex) {
            System.err.println("No se pudo agregar el cuidador");
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Método que realiza una consulta de todos los empleados registrados en la
     * base de datos utilizando el operador $eq en el atributo discriminador
     * para que solo los cuidadores sean los que entren en la consulta. Los
     * añade todos a una lista y la devuelve al sistema, regresa null en caso de
     * que no encuentre nada
     *
     * @return lista con todos los cuidadores en la base de datos, null en caso
     * de que no se encuentre nada
     */
    public List<Cuidador> consultarCuidadores() {
        MongoCollection<Cuidador> coleccion = this.getColeccion();
        List<Cuidador> lista = new ArrayList<>();
        coleccion.find(Filters.eq("discriminador", "cu")).into(lista);
        return lista;
    }

    /**
     * Añade una especie al atributo especiesExperto de un cuidador
     *
     * @param idCuidador id del cuidador a actualizar
     * @param especie especie a añadir en la lista de especiesExperto
     */
    public void agregarEspecie(ObjectId idCuidador, Especie especie) {
        MongoCollection<Cuidador> coleccion = this.getColeccion();
        coleccion.updateOne(Filters.eq("_id", idCuidador), new Document()
                .append("$push", new Document()
                        .append("especiesExperto", especie)));
    }
}
