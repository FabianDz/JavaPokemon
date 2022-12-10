/*
 * Proyecto del curso Prebecario Gen. 43 de JAVA | PROTECO
 * Proyecto Pokemon - Main
 */

package com.fabiandiaz.proyectopokemon;

import java.util.Scanner;

/**
 * @author Fabian Josafat Diaz Silleros
 */
public class ProyectoPokemon {

    public static void main(String[] args) {
        // Declaramos un scanner
        Scanner sc = new Scanner(System.in);
        // Declaramos la variable menu y opcion (si iniciar una batalla o salir)
        Menu menu = new Menu();
        int opc = 0;
        
        // while para generar batallas
        while(opc != 2){
            
            // Imprimimos el menu y solicitamos una opcion
            menu.menuPrincipal();
            System.out.print("\n\n\tElige una opción: ");
            // Leemos la opcion
            opc = sc.nextInt();
            
            switch (opc) {
                case 1 -> { // Opcion 1 (batalla)
                    // Creamos un objeto de la clase Batalla
                    Batalla batalla = new Batalla();
                    // Mandamos llamar el método de preparativos
                    // El cuál al terminar va a llamar a inicia Batalla
                    batalla.preparativosBatalla();
                }
                
                case 2 -> { // Opcion 2 (salir)
                    System.out.println("\n\n¡Nos vemos pronto!");
                }
                
                default ->{ // Cualquier otra opcion
                    System.out.println("\n\n\tOpción no válida, intenta de nuevo... \n");
                }
            
            }      
        }
    }
}
