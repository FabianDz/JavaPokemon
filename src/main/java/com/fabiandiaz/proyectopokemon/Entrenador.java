/*
 * Proyecto del curso Prebecario Gen. 43 de JAVA | PROTECO
 * Clase Entrenador
 */
package com.fabiandiaz.proyectopokemon;

/**
 * @author Fabián Josafat Díaz Silleros
 */
public class Entrenador {
    // Atributos
    public String nombre;
    public Pokemon listaPokes[] = new Pokemon[3];
    public Objeto listaObjetos[] = new Objeto[3];
    
    // Constructor
    public Entrenador(String nombre, Pokemon listaPokes[]){
        this.nombre = nombre;
        this.listaPokes = listaPokes;
        this.listaObjetos[0] = new Objeto("Pocion");
        this.listaObjetos[1] = new Objeto("Ataque X");
        this.listaObjetos[2] = new Objeto("Defensa X");
    }
    
    // Métodos Entrenador

}
