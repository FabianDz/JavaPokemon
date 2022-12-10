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
        System.out.println("\tFlareon");
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
    
    // Metodo que imprime los pokes actuales, su vida y su estado (si es diferente a Preparado)
    public void turno(Entrenador e1, Entrenador e2, int actualE1, int actualE2){
        
        System.out.print("\n\t"+e1.listaPokes[actualE1].apodo+": "+e1.listaPokes[actualE1].hp+" HP");
        if(!"Preparado".equals(e1.listaPokes[actualE1].estado)){
            System.out.println("\tEstado: "+e1.listaPokes[actualE1].estado);
        }
        System.out.print("\n\t"+e2.listaPokes[actualE2].apodo+": "+e2.listaPokes[actualE2].hp+" HP");
        if(!"Preparado".equals(e2.listaPokes[actualE2].estado)){
            System.out.println("\tEstado: "+e2.listaPokes[actualE2].estado);
        }
    }
    
    public void acciones(Entrenador e){
        // Imprimimos el estado de los pokemon
        System.out.println("\n\n\tTus pokemon: ");
        e.getEstadoPokes();
        // Imprimimos los objetos disponibles
        System.out.println("\n\n\tTus Objetos disponibles: ");
        e.getCantObj();
        // Imprimimos las opciones para realizar
        System.out.println("\n\n\t1. Realizar Movimiento");
        System.out.println("\t2. Usar un Objeto");
        System.out.println("\t3. Cambiar Pokemon");
        // Pedimos que elija su opción
        System.out.println("\n\tElige tu opción: ");
    }
    
}
