/*
 * Proyecto del curso Prebecario Gen. 43 de JAVA | PROTECO
 * Clase Movimiento (ataques)
 */
package com.fabiandiaz.proyectopokemon;

/**
 * @author Fabián Josafat Díaz Silleros
 */
public class Movimiento {
    // Atributos
    public String nombre;
    public int danio;
    public String efecto;
    public int presicion = 80;
    public String tipo;
    
    // Constructor
    public Movimiento(String nombre, int danio, String efecto, String tipo){
        this.nombre = nombre;
        this.danio = danio;
        this.efecto = efecto;
        this.tipo = tipo;
    }
    
    // Metodo ser Usado
    public void serUsado(){
        
    }
}
