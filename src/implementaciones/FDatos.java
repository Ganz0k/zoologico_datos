/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import daos.RepoAdministradores;
import daos.RepoAnimales;
import daos.RepoCuidadores;
import daos.RepoEspecies;
import daos.RepoGuias;
import daos.RepoHabitats;
import daos.RepoItinerarios;
import daos.RepoZonas;
import entidades.Administrador;
import entidades.Animal;
import entidades.Cuidador;
import entidades.Especie;
import entidades.Guia;
import entidades.Habitat;
import entidades.Itinerario;
import entidades.Zona;
import interfaces.IDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Clase que implementa la interfaz IDatos para mandar a llamar los métodos de
 * los repos
 *
 * @author luisg
 */
public class FDatos implements IDatos {

    private final RepoHabitats repoHabitats;
    private final RepoCuidadores repoCuidadores;
    private final RepoEspecies repoEspecies;
    private final RepoAnimales repoAnimales;
    private final RepoGuias repoGuias;
    private final RepoZonas repoZonas;
    private final RepoItinerarios repoItinerarios;
    private final RepoAdministradores repoAdministradores;
    
    /**
     * Constructor que inicializa todos los repos
     */
    public FDatos() {
        this.repoHabitats = new RepoHabitats();
        this.repoCuidadores = new RepoCuidadores();
        this.repoEspecies = new RepoEspecies();
        this.repoAnimales = new RepoAnimales();
        this.repoGuias = new RepoGuias();
        this.repoZonas = new RepoZonas();
        this.repoItinerarios = new RepoItinerarios();
        this.repoAdministradores = new RepoAdministradores();
    }

    /**
     * Manda a llamar el método guardarHabitat del RepoHabitats para guardar un
     * hábitat en la base de datos
     *
     * @param habitat habitat a guardar en la colección "habitats"
     * @return true en caso de que se logre guardar, false en caso contrario
     */
    @Override
    public boolean guardarHabitat(Habitat habitat) {
        return this.repoHabitats.guardarHabitat(habitat);
    }

    /**
     * Manda a llamar el método verificarNombreHabitat del RepoHabitats para
     * obtener un hábitat que coincida con el nombre dado como parámetro
     *
     * @param nombre nombre a comparar con los nombres de los hábitats en la
     * base de datos
     * @return un hábitat en el caso de que encuentre uno, null en el caso
     * contrario
     */
    @Override
    public Habitat verificarNombreHabitat(String nombre) {
        return this.repoHabitats.verificarNombreHabitat(nombre);
    }

    /**
     * Manda a llamar el método consultarHabitats del RepoHabitats para obtener
     * una lista con todos los hábitats en la base de datos, regresa null en
     * caso de no encontrar nada
     *
     * @return lista con todos los hábitats en la base de datos, null en caso de
     * que no se encuentre nada
     */
    @Override
    public List<Habitat> consultarHabitats() {
        return this.repoHabitats.consultarHabitats();
    }

    /**
     * Manda a llamar el método consultarCuidadores del RepoCuidadores para
     * obtener una lista con todos los cuidadores en la base de datos, regresa
     * null en caso de no encontrar nada
     *
     * @return lista con todos los cuidadores en la base de datos, null en caso
     * de que no se encuentre nada
     */
    @Override
    public List<Cuidador> consultarCuidadores() {
        return this.repoCuidadores.consultarCuidadores();
    }

    /**
     * Manda a llamar el método consultarEspecie del RepoEspecies para obtener
     * una especie que coincida con el nombre del parámetro
     *
     * @param nombre nombre a comparar con los nombres en español de las
     * especies en la base de datos
     * @return una especie en el caso de que encuentre una, null en el caso
     * contrario
     */
    @Override
    public Especie consultarEspecie(String nombre) {
        return this.repoEspecies.consultarEspecie(nombre);
    }

    /**
     * Manda a llamar el método consultarEspecie del RepoEspecies para obtener
     * una especie que coincida con el nombre científico de la especie del
     * parámetro
     *
     * @param especie especie la cual proveerá el nombre científico para
     * comparar con las especies en la base de datos
     * @return una especie en el caso de que encuentre una, null en el caso
     * contrario
     */
    @Override
    public Especie consultarEspecie(Especie especie) {
        return this.repoEspecies.consultarEspecie(especie);
    }

    /**
     * Manda a llamar el método guardarEspecie del RepoEspecies para guardar una
     * especie en la base de datos
     *
     * @param especie especie a guardar en la colección "especies"
     * @return true en caso de que se logre guardar, false en caso contrario
     */
    @Override
    public boolean guardarEspecie(Especie especie) {
        return this.repoEspecies.guardarEspecie(especie);
    }

    /**
     * Manda a llamar el método agregarEspecie del RepoCuidadores para añadir
     * una especie a la lista del campo especiesExperto del cuidador que
     * coincida con el id del parámetro
     *
     * @param idCuidador id del cuidador a actualizar
     * @param especie especie a añadir en la lista de especiesExperto
     */
    @Override
    public void agregarEspecieCuidador(ObjectId idCuidador, Especie especie) {
        this.repoCuidadores.agregarEspecie(idCuidador, especie);
    }

    /**
     * Manda a llamar el método agregarEspecie del RepoHabitats para añadir un
     * id de especie a la lista del campo idsEspecie del hábitat que coincida
     * con el id del parámetro
     *
     * @param idHabitat id del hábitat a actualizar
     * @param idEspecie id de la especie a añadir en la lista de idsEspecie
     */
    @Override
    public void agregarEspecieHabitat(ObjectId idHabitat, ObjectId idEspecie) {
        this.repoHabitats.agregarEspecie(idHabitat, idEspecie);
    }

    /**
     * Manda a llamar el método consultarAnimalesEspecie del RepoAnimales para
     * obtener una lista con todos los animales que el campo animales de la
     * especie que coincida con el id del parámetro contenga
     *
     * @param idEspecie id de especie a consultar
     * @return lista con todos los animales de la especie, null en caso de no
     * encontrar nada
     */
    @Override
    public List<Animal> consultarAnimalesEspecie(ObjectId idEspecie) {
        return this.repoAnimales.consultarAnimalesEspecie(idEspecie);
    }

    /**
     * Manda a llamar el método guardarAnimal del RepoAnimales para añadir un
     * nuevo animal a la lista del campo animales de la especie que coincida con
     * el id del parámetro
     *
     * @param idEspecie id de la especie a actualizar
     * @param animal animal a añadir en la lista animales
     */
    @Override
    public void guardarAnimal(ObjectId idEspecie, Animal animal) {
        this.repoAnimales.guardarAnimal(idEspecie, animal);
    }

    /**
     * Manda a llamar el método eliminarAnimal del RepoAnimales para eliminar un
     * animal que coincida con el id del parámetro de la lista de animales de la
     * especie que coindica con el id del parámetro
     *
     * @param idEspecie id de la especie a actualizar
     * @param idAnimal id del animal a eliminar de la lista animales
     */
    @Override
    public void eliminarAnimal(ObjectId idEspecie, ObjectId idAnimal) {
        this.repoAnimales.eliminarAnimal(idEspecie, idAnimal);
    }

    /**
     * Manda a llamar el método consultarGuias del RepoGuias para obtener
     * una lista con todos los guias en la base de datos, regresa null
     * en caso de encontrar nada.
     *
     * @return lista con todos los guias en la base de datos, null
     * en caso de no encontrar guias.
     */
    @Override
    public List<Guia> consultarGuias() {
        return this.repoGuias.consultarGuias();
    }

    /**
     * Manda a llamar el método agregarItinerario del RepoGuias para agregar una 
     * nueva referencia a itinerario dentro del guia que coincida con el id 
     * dado en el parámetro.
     *
     * @param idGuia id del guia a actualizar.
     * @param itinerario Itinerario a agregar en la lista de itinerarios del guia.
     */
    @Override
    public void agregarItinerario(ObjectId idGuia, Itinerario itinerario) {
        this.repoGuias.agregarItinerario(idGuia, itinerario);
    }
    
    /**
     * Manda a llamar el método guardarGuia del RepoGuias para guardar un nuevo
     * guia en la base de datos.
     *
     * @param guia guia a guardar en la colección "guias"
     * @return true en caso de que se logre guardar, false en caso contrario
     */
    @Override
    public boolean guardarGuia(Guia guia) {
        return this.repoGuias.guardarGuia(guia);
    }

    /**
     * Manda a llamar el método guardarCuidador del RepoCuidador para guardar 
     * un nuevo cuidador en la base de datos.
     * 
     * @param cuidador Cuidador a guardar en la colección "cuidador"
     * @return true en caso de que se logre guardar, false en caso contrario.
     */
    @Override
    public boolean guardarCuidador(Cuidador cuidador) {
        return this.repoCuidadores.guardarCuidador(cuidador);
    }

    /**
     * Manda a llamar el método guardarZona del RepoZonas para guardar
     * una nueva zona en la base de datos.
     * 
     * @param zona Zona a guardar en la coleccion "zonas"
     * @return true en caso de que se logre guardar, false en caso contrario.
     */
    @Override
    public boolean guardarZona(Zona zona) {
        return this.repoZonas.guardarZona(zona);
    }

    /**
     * Manda a llamar el método agregarEspecie de la RepoZonas para guardar una
     * nueva especie dentro de la zona con el id dado por el parámetro en la
     * base de datos.
     * 
     * @param idZona id de la zona a actualizar en la coleccion "zonas"
     * @param idEspecie id de la especie a agregar dentro de la zona
     */
    @Override
    public void agregarEspecie(ObjectId idZona, ObjectId idEspecie) {
        this.repoZonas.agregarEspecie(idZona, idEspecie);
    }

    /**
     * Manda a llamar el método agregarHabitat de la RepoZonas para guardar un
     * nuevo habitat dentro de la zona con el id dado por el parámetro en la
     * base de datos.
     * 
     * @param idZona id de la zona a actualizar en la coleccion "zonas"
     * @param idHabitat id del habitat a agregar dentro de la zona.
     */
    @Override
    public void agregarHabitat(ObjectId idZona, ObjectId idHabitat) {
        this.repoZonas.agregarHabitat(idZona, idHabitat);
    }

    /**
     * Manda a llamar el método guardarItinerario de la RepoItinerarios para
     * guardar un nuevo itinerario dado por el parámetro, en la base de datos.
     * 
     * @param itinerario Itinerario a guardar.
     * @return true en caso de lograr agregar, false en caso contrario.
     */
    @Override
    public boolean guardarItinerario(Itinerario itinerario) {
        return this.repoItinerarios.guardarItinerario(itinerario);
    }

    /**
     * Manda a llamar el método verificarNombreItinerario de la RepoItinerarios
     * para consultar un itinerario que tenga el mismo nombre que el dado por
     * el parametro.
     * 
     * @param nombre Nombre del iinerario a verificar.
     * @return obtiene el itinerario en caso de coincidir, null en caso contrario.
     */
    @Override
    public Itinerario verificarNombreItinerario(String nombre) {
        return this.repoItinerarios.verificarNombreItinerario(nombre);
    }

    /**
     * Manda a llamar el método getAdministrador del RepoAdministradores para
     * regresar un administrador que coincida con el nombre y la contraseña que
     * se proveen como parámetros
     * @param nombre del administrador
     * @param contrasenia contraseña del administrador
     * @return un administrador que coincida con el nombre y la contraseña de
     * los parámetros, null si no encuentra nada
     */
    @Override
    public Administrador getAdministrador(String nombre, String contrasenia) {
        return this.repoAdministradores.getAdministrador(nombre, contrasenia);
    }
}
