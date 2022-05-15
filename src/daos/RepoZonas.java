/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionBD;
import entidades.Zona;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Repositorio de Zonas, actúa como una clase que utiliza la conexión
 * generada con la base de datos para hacer operaciones de adición y consulta
 * en la colección Zonas.
 * 
 * @author PC OSCAR
 */
public class RepoZonas {
    
    private final MongoDatabase baseDatos;

    /**
     * Constructor de al clase que inicializa el atributo baseDatos del tipo
     * MongoDatabase gracias a que el método crearBade genera un objeto de tipo
     * MongoDatabase.
     */
    public RepoZonas() {
        this.baseDatos = ConexionBD.crearBade();
    }
    
    /**
     * Método privado que regresa la colección "zonas" y la sujeta al formato
     * de la clase Zona.
     * 
     * @return coleccion "zonas" ligada al formato de la clase Zona.
     */
    private MongoCollection<Zona> getColeccion(){
        return this.baseDatos.getCollection("zonas", Zona.class);
    }
    
    /**
     * Método que guarda una Zona dada por el parámetro, en su colección
     * correspondiente. 
     * 
     * @param zona Zona a guardar en la colección "zonas"
     * @return true en caso de lograr guardar, false en caso contrario
     */
    public boolean guardarZona(Zona zona){
        try {
            MongoCollection<Zona> coleccion = this.getColeccion();
            coleccion.insertOne(zona);
            return true;
        } catch (Exception ex) {
            System.err.println("No se pudo agregar la zona");
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Añade un id de especie al atributo idsEspecie de una zona.
     * 
     * @param idZona id de la zona a actualizar
     * @param idEspecie id de la especie a añadir en la lista de idsEspecie
     */
    public void agregarEspecie(ObjectId idZona, ObjectId idEspecie){
        MongoCollection<Zona> coleccion = this.getColeccion();
        coleccion.updateOne(Filters.eq("_id", idZona), new Document()
                .append("$push", new Document()
                        .append("idsEspecie", idEspecie)));
    }
    
    /**
     * Añade un id de habitat al atributo idsHabitat de una zona.
     * 
     * @param idZona id de la zona a actualizar
     * @param idHabitat id del habitat a añadir en la lista de idsHabitat
     */
    public void agregarHabitat(ObjectId idZona, ObjectId idHabitat){
        MongoCollection<Zona> coleccion = this.getColeccion();
        coleccion.updateOne(Filters.eq("_id", idZona), new Document()
                .append("$push", new Document()
                        .append("idsHabitat", idHabitat)));
    }
    
    /**
     * Método que realiza una consulta de todos las zonas registradas en la
     * colección de zonas en la base de datos. Los añade todos a una lista y
     * la devuelve al sistema, regresa null en caso de que no se encuentre nada
     *
     * @return lista con todos los hábitats en la base de datos, null en caso de
     * que no se encuentre nada
     */
    public List<Zona> consultarZonas() {
        MongoCollection<Zona> coleccion = this.getColeccion();
        List<Zona> listaZonas = new ArrayList<>();
        coleccion.find().into(listaZonas);
        return listaZonas;
    }
}
