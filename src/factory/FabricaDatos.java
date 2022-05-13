/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import implementaciones.FDatos;
import interfaces.IDatos;

/**
 * Fábrica que crea un objeto de tipo IDatos para acceder a todos los métodos de
 * los repositorios del subsistema de datos
 *
 * @author luisg
 */
public class FabricaDatos {

    /**
     * Método estatico que crea un objeto de IDatos, especificamente de la clase
     * FDatos la cual implementa la interfaz IDatos
     *
     * @return un objeto de IDatos, especificamente de la clase FDatos la cual
     * implementa la interfaz IDatos
     */
    public static IDatos crearFDatos() {
        return new FDatos();
    }
}
