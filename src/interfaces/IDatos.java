/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Administrador;
import entidades.Animal;
import entidades.Cuidador;
import entidades.Especie;
import entidades.Guia;
import entidades.Habitat;
import entidades.Itinerario;
import entidades.Queja;
import entidades.Zona;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author luisg
 */
public interface IDatos {

    public boolean guardarHabitat(Habitat habitat);

    public Habitat verificarNombreHabitat(String nombre);

    public List<Habitat> consultarHabitats();

    public List<Cuidador> consultarCuidadores();

    public Especie consultarEspecie(String nombre);

    public Especie consultarEspecie(Especie especie);

    public boolean guardarEspecie(Especie especie);

    public boolean guardarCuidador(Cuidador cuidador);

    public void agregarEspecieCuidador(ObjectId idCuidador, Especie especie);

    public void agregarEspecieHabitat(ObjectId idHabitat, ObjectId idEspecie);

    public List<Animal> consultarAnimalesEspecie(ObjectId idEspecie);

    public void guardarAnimal(ObjectId idEspecie, Animal animal);

    public void eliminarAnimal(ObjectId idEspecie, ObjectId idAnimal);

    public Administrador getAdministrador(String nombre, String contrasenia);

    // Guias
    public List<Guia> consultarGuias();

    public void agregarItinerario(ObjectId idGuia, Itinerario itinerario);

    public boolean guardarGuia(Guia guia);

    // Zonas
    public boolean guardarZona(Zona zona);

    public void agregarEspecie(ObjectId idZona, ObjectId idEspecie);

    public void agregarHabitat(ObjectId idZona, ObjectId idHabitat);

    // Itinerario
    public boolean guardarItinerario(Itinerario itinerario);

    public Itinerario verificarNombreItinerario(String nombre);

    public List<Itinerario> consultarItinerarios();

    public Guia consultarGuia(Itinerario itinerario);

    // Queja
    public boolean guardarQueja(Queja queja);
}
