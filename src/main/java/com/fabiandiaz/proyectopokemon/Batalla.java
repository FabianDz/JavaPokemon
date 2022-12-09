/*
 * Proyecto del curso Prebecario Gen. 43 de JAVA | PROTECO
 * Clase Batalla
 */

package com.fabiandiaz.proyectopokemon;

import java.util.Scanner;

/**
 * @author Fabian Josafat Diaz Silleros
 */
public class Batalla { 
    
    // Método que prepara la batalla, genera los entrenadores
    public void preparativosBatalla(){
        // Declaramos un scanner
        Scanner sc = new Scanner(System.in);
        
        // Declaramos un objeto Menu
        Menu menu = new Menu();
        
        // Declaramos Strings para el nombre de los jugadores y pokemon elegidos
        String E1, E2, poke, mote;
        System.out.print("\n\n\tIngresa el nombre del primer Entrenador: ");
        E1 = sc.nextLine();
        System.out.print("\n\n\tIngresa el nombre del segundo Entrenador: ");
        E2 = sc.nextLine();
        
        // Creamos dos arreglos de Pokemon, para cada entrenador
        Pokemon e1Pokes[] = new Pokemon[3];
        Pokemon e2Pokes[] = new Pokemon[3];
        
        // Mostramos los pokes disponibles
        // NO PONGO restriccion de qué se puede introducir o no, 
        // porque la idea es que se pueda expandir a futuro con todos los pokes
        // existentes, y como se usa la API, sí se puede meter cualquier poke
        // Pero va a crashear si no es de los tipos existentes
        menu.todosPokes();
        // Pedimos que los elija y les ponga mote o apodo
        // For de tres iteraciones para elegir a los 3 pokemon y sus motes
        for (int i = 0; i < 3; i++) {
            
            System.out.print("\n\n"+E1+", elige a tu "+ (i+1) +"° compañero: ");
            poke = sc.nextLine();
            System.out.print("\nDale un mote: ");
            mote = sc.nextLine();
            
            // Creamos el poke
            e1Pokes[i] = new Pokemon(poke, mote);
        }
        
        // Creamos al primer Entrenador
        Entrenador e1 = new Entrenador(E1, e1Pokes);
        
        // Repetimos lo mismo para el segundo entrenador
        // Mostramos los pokes
        menu.todosPokes();
        // For de tres iteraciones para elegir a los 3 pokemon y sus motes
        for (int i = 0; i < 3; i++) {
            
            System.out.print("\n\n"+E2+", elige a tu "+ (i+1) +"° compañero: ");
            poke = sc.nextLine();
            System.out.print("\nDale un mote: ");
            mote = sc.nextLine();
            
            // Creamos el poke
            e2Pokes[i] = new Pokemon(poke, mote);
        }
        
        // Creamos al segundo Entrenador
        Entrenador e2 = new Entrenador(E2, e2Pokes);
        
        // Llamamos al metodo inicia Batalla 
        this.iniciaBatalla(e1, e2);
    }
    
    // Método que inicia la batalla, recibe de parámetro a los entrenadores
    public void iniciaBatalla(Entrenador e1, Entrenador e2){
        System.out.println("\n\nOlas"+e1.nombre+e2.nombre);
    }
    
}
