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
    
    public void preparativosBatalla(){
        // Declaramos un scanner
        Scanner sc = new Scanner(System.in);
        // Declaramos un objeto Menu
        Menu menu = new Menu();
        // Declaramos Strings para el nombre de los jugadores y pokemon elegidos
        String E1, E2;
        System.out.print("\n\n\tIngresa el nombre del primer Entrenador: ");
        E1 = sc.nextLine();
        System.out.print("\n\n\tIngresa el nombre del segundo Entrenador: ");
        E2 = sc.nextLine();
        menu.todosPokes();
        System.out.print("\n\n"+E1+", elige a tu primer compañero: ");
        System.out.print("\n\n"+E1+", elige a tu primer compañero: ");
        System.out.print("\n\n"+E1+", elige a tu primer compañero: ");
        menu.todosPokes();
        System.out.print("\n\n"+E2+", elige a tus compañeros: ");
        System.out.print("\n\n"+E1+", elige a tu primer compañero: ");
        System.out.print("\n\n"+E1+", elige a tu primer compañero: ");
    }
    
    public void iniciaBatalla(){
        
    }
    
}
