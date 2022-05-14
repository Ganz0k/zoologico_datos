/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.ConexionBD;
import entidades.Administrador;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de Administradores, actúa como una clase que utiliza la conexión
 * generada con la base de datos para hacer operaciones de añadición y consulta
 * en la colección empleados
 *
 * @author luisg
 */
public class RepoAdministradores {

    private final MongoDatabase baseDatos;

    /**
     * Constructor de la clase que inicializa el atributo baseDatos del tipo
     * MongoDatabase gracias a que el método crearBade genera un objeto de tipo
     * MongoDatabase
     */
    public RepoAdministradores() {
        this.baseDatos = ConexionBD.crearBade();
    }

    /**
     * Método privado que regresa la colección "empleados" y la sujeta al
     * formato de la clase Administrador
     *
     * @return colección "empleados" y la sujeta al formato de la clase
     * Administrador
     */
    private MongoCollection<Administrador> getColeccion() {
        return this.baseDatos.getCollection("empleados", Administrador.class);
    }

    /**
     * Regresa un administrador que coincida con el nombre y la contraseña de
     * los parámetros, regresa null en caso de no encontrar nada
     *
     * @param nombre nombre del administrador
     * @param contrasenia contraseña del administrador
     * @return un administrador que coincida con el nombre y la contraseña de
     * los parámetros, null si no encuentra nada
     */
    public Administrador getAdministrador(String nombre, String contrasenia) {
        MongoCollection<Administrador> coleccion = this.getColeccion();
        List<Administrador> lista = new ArrayList<>();
        coleccion.find(Filters.eq("discriminador", "ad")).into(lista);
        for (Administrador a : lista) {
            if (a.getNombre().equals(nombre) && a.getContrasenia().equals(contrasenia)) {
                return a;
            }
        }
        return null;
    }
}
