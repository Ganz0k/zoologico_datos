/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionBD;
import entidades.Animal;
import entidades.Especie;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Repositorio de Animales, actúa como una clase que utiliza la conexión
 * generada con la base de datos para hacer operaciones de añadición, consulta y
 * edición en la colección
 *
 * @author luisg
 */
public class RepoAnimales {

    private final MongoDatabase baseDatos;

    /**
     * Constructor de la clase que inicializa el atributo baseDatos del tipo
     * MongoDatabase gracias a que el método crearBade genera un objeto de tipo
     * MongoDatabase
     */
    public RepoAnimales() {
        this.baseDatos = ConexionBD.crearBade();
    }

    /**
     * Método privado que regresa la colección "especies" y la sujeta al formato
     * de la clase Animal
     *
     * @return colección "especies" y la sujeta al formato de la clase Especie
     */
    private MongoCollection<Animal> getColeccion() {
        return this.baseDatos.getCollection("especies", Animal.class);
    }

    /**
     * Devuelve una lista con todos los animales de la especie que coincida con
     * el id del parámetro
     *
     * @param idEspecie id de especie a consultar
     * @return lista con todos los animales de la especie, null en caso de no
     * encontrar nada
     */
    public List<Animal> consultarAnimalesEspecie(ObjectId idEspecie) {
        MongoCollection<Animal> coleccion = this.getColeccion();
        List<Document> etapas = new ArrayList<>();

        etapas.add(new Document()
                .append("$match", new Document()
                        .append("_id", idEspecie)));

        etapas.add(new Document()
                .append("$unwind", new Document()
                        .append("path", "$animales")));

        etapas.add(new Document()
                .append("$project", new Document()
                        .append("_id", 0)
                        .append("_id", "$animales._id")
                        .append("nombreAnimal", "$animales.nombreAnimal")
                        .append("edad", "$animales.edad")
                        .append("sexo", "$animales.sexo")));

        List<Animal> resultados = new ArrayList<>();
        coleccion.aggregate(etapas).into(resultados);
        return resultados;
    }

    /**
     * Añade un animal al campo animales de la especie que coincida con el id
     * del parámetro
     *
     * @param idEspecie id de la especie a actualizar
     * @param animal animal a añadir en la lista animales
     */
    public void guardarAnimal(ObjectId idEspecie, Animal animal) {
        MongoCollection<Especie> coleccion = this.baseDatos.getCollection("especies", Especie.class);
        coleccion.updateOne(Filters.eq("_id", idEspecie), new Document()
                .append("$push", new Document()
                        .append("animales", animal)));
    }

    /**
     * Elimina un animal que coincida con el id del parámetro de la lista de
     * animales de la especie que coincida el id del parámetro
     *
     * @param idEspecie id de la especie a actualizar
     * @param idAnimal id del animal a eliminar de la lista animales
     */
    public void eliminarAnimal(ObjectId idEspecie, ObjectId idAnimal) {
        MongoCollection<Especie> coleccion = this.baseDatos.getCollection("especies", Especie.class);
        coleccion.updateOne(new Document()
                .append("_id", idEspecie), new Document()
                .append("$pull", new Document()
                        .append("animales", new Document()
                                .append("_id", idAnimal))));
    }
}
