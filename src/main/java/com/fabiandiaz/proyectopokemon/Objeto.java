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
    
    // Metodo usar
    public void usar(){
        
        // Le restamos uno a la cantidad del objeto
        this.cantidad -= 1;
        
    }
}
