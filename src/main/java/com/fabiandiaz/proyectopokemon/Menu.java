/*
 * Proyecto del curso Prebecario Gen. 43 de JAVA | PROTECO
 * Clase Menu Principal
 */

package com.fabiandiaz.proyectopokemon;

/**
 * @author Fabian Josafat Diaz Silleros
 */
public class Menu {
    
    
    public void menuPrincipal(){ // Imprime el menu Principal
        System.out.println("\n\tBienvenido a mi Simulador de Batallas Pokémon\n");
        System.out.println("\n\t¿Qué quieres hacer hoy?");
        System.out.println("\n\t\t1. Iniciar una batalla");
        System.out.println("\n\t\t2. Salir"); 
    }
    
    public void todosPokes(){ // Imprime todos los pokes que se pueden elegir
        System.out.println("\n\t\tTipos Planta"); 
        System.out.println("\tSceptile");
        System.out.println("\tRillaboom");
        System.out.println("\tVenusaur");
        
        System.out.println("\n\t\tTipos Fuego"); 
        System.out.println("\tTyphlosion");
        System.out.println("\tDarmanitan");
        System.out.println("\tCinderace");
        
        System.out.println("\n\t\tTipos Eléctrico"); 
        System.out.println("\tLuxray");
        System.out.println("\tElectivire");
        System.out.println("\tJolteon");
        
        System.out.println("\n\t\tTipos Agua");
        System.out.println("\tBlastoise");
        System.out.println("\tMilotic");
        System.out.println("\tVaporeon");
    }
    
}
