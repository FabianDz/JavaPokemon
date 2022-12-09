/*
 * Proyecto del curso Prebecario Gen. 43 de JAVA | PROTECO
 * Clase Objeto (como pociones)
 */
package com.fabiandiaz.proyectopokemon;

/**
 * @author Fabián Josafat Díaz Silleros
 */
public class Objeto {
    // Atributos
    public String nombre;
    public int cantidad = 2;
    
    // Constructor
    public Objeto(String nombre){
        this.nombre = nombre;
    }
    
    // Método getter Nombre
    public String getNombre(){
        return this.nombre;
    }
    
    // Metodo usar
    public String usar(){
        // Si hay al menos un objeto
        if (this.cantidad > 0) {
            // Se le resta uno, porque se usa
            this.cantidad -= 1;
            // Se retorna el nombre
            return this.nombre;
        } else { // Si no hay mas cantidad
            // Se retorna null
            return null;
        }
    }
}
