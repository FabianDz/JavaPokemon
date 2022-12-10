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
        // Se mandan los dos entrenadores y el menu
        this.iniciaBatalla(e1, e2, menu, sc);
    }
    
    // Método que inicia la batalla, recibe de parámetro a los entrenadores
    public void iniciaBatalla(Entrenador e1, Entrenador e2, Menu menu, Scanner sc){
        // Mostramos el resumen de los entrenadores
        System.out.println("\n\nLos participantes serán:\n");
        // Imprimimos a los entrenadores
        e1.getEntrenador();
        e2.getEntrenador();
        
        // Imprimimos que se han sacado a los dos primeros pokes
        System.out.println("\n\n"+e1.nombre+" saca a "+e1.listaPokes[0].apodo);
        System.out.println("\n\n"+e2.nombre+" envía a "+e2.listaPokes[0].apodo);
        // Empezamos con una variable para llevar el número de turnos
        // Y el while true para que la batalla no acabe hasta que uno sea derrotado
        int turno = 0;
        // y dos variables para llevar el control de los pokemon actual de cada entrenador
        int actualE1 = 0, actualE2 = 0;
        // Y una variable mas para las elecciones y movimientos
        int eleccionE1 = 0, eleccionE2 = 0;
        while (true) {            
            // Cada nuevo turno, se le suma uno a la cuenta
            turno += 1;
            System.out.println("\n\t\tTurno: "+turno);
            // Imprimimos el menu general de turno (vida y pokes actuales)
            menu.turno(e1, e2, actualE1, actualE2);
            
            // while hasta que realice una accion valida
            // Entrenador 1
            while (true) {                
                // Metodo del menu para imprimir las opciones del jugador 1
                menu.acciones(e1);
                // leemos su elección
                eleccionE1 = sc.nextInt();
                // cadena de ifs de la eleccion
                if(eleccionE1 == 1){ // Si va a realizar un movimiento
                    // Mostramos los movimientos
                    e1.listaPokes[actualE1].mostrarMovimientos();
                    // while para una respuesta correcta
                    while (true) { 
                        // Leemos la opcion
                        // la guardamos en la misma variable, la cual nos va a 
                        // servir para la eleccion de movimiento mas adelante
                        eleccionE1 = sc.nextInt();
                        // Si no es una respuesta valida
                        if(eleccionE1 < 1 || eleccionE1 > 4){ // damos el mensaje
                            System.out.println("\n\nOpción no válida, intente de nuevo...");
                            // Invocamos la siguiente iteración
                            continue;
                        }
                        // Si está bien, cortamos el while
                        break;
                    }
                    // Cerramos el while
                    break;
                }
                if(eleccionE1 == 2){ // Si va a usar un objeto
                    // Primero verificamos que todavía tenga objetos
                    if(e1.listaObjetos[0].cantidad != 0 && e1.listaObjetos[1].cantidad != 0 && e1.listaObjetos[2].cantidad != 0){
                        
                        while (true) {                            
                            
                            System.out.println("\n\n¿Qué objeto quieres usar? 1. Pocion, 2. Ataque X, 3. Defensa X: ");
                            eleccionE1 = sc.nextInt();
                            // Si ya no hay de ese objeto o elige un numero diferente a 1, 2 o 3
                            if(e1.listaObjetos[eleccionE1-1].cantidad == 0 || eleccionE1 < 1 || eleccionE1 > 3){ 
                                System.out.println("\n\nNo te quedan objetos de ese tipo, intenta con otro...");
                                // Saltamos a la siguiente iteracion
                                continue;
                            }
                            // Se aplica el efecto del objeto
                            e1.listaPokes[actualE1].aplicarObjeto(eleccionE1-1);
                            // Se resta uno al inventario
                            e1.listaObjetos[eleccionE1-1].usar();
                            // Cerramos el while
                            break;
                        }
                        
                    }else{ // si no hay, lo mandamos de regreso a elegir qué hacer
                        System.out.println("\n\nNo te quedan objetos, intenta otra cosa...");
                        continue;
                    }
                    
                    // Ponemos eleccion en 0, para decir que no se lanzó un movimiento
                    eleccionE1 = 0;
                    // cerramos el while
                    break;
                }
                if(eleccionE1 == 3){ // Si va a cambiar de pokemon
                    // Primero verificamos que todavía tenga pokemon listos
                    // con un metodo que nos diga si los otros dos pokes estan debilitados
                    if(!e1.otrosDebilitados(actualE1)){
                        
                        // imprimimos sus pokes
                        e1.getEstadoPokes();
                        
                        while (true) {                            
                            System.out.println("\n\nElige tu siguiente Pokémon:");
                            // Leemos
                            eleccionE1 = sc.nextInt();
                            // revisamos si esta vivo
                            if(!e1.pokeDebilitado(eleccionE1-1)){
                                actualE1 = eleccionE1 - 1;
                                System.out.println("\n\nHas cambiado de Pokémon");
                                // Rompemos el while
                                break;
                            }
                            System.out.println("\n\nPokemon no valido, intenta de nuevo");
                        }
                        
                    }else{ // si no hay, lo mandamos de regreso a elegir qué hacer
                        System.out.println("\n\nNo te quedan pokemon, intenta otra cosa...");
                        continue;
                    }
                    
                    // Ponemos eleccion en 0, para decir que no se lanzó un movimiento
                    eleccionE1 = 0;
                    // Cerramos el while
                    break;
                }

                // Avisamos que su eleccion no es posible y que lo intente de nuevo
                System.out.println("\n\nOpción no válida, intente de nuevo...");
            }
            /*
            
            
            
            Entrenador 2 while
            
            
            */
            
            // while hasta que realice una accion valida
            // Entrenador 2
            while (true) {                
                // Metodo del menu para imprimir las opciones del jugador 2
                menu.acciones(e2);
                // leemos su elección
                eleccionE2 = sc.nextInt();
                // cadena de ifs de la eleccion
                if(eleccionE2 == 1){ // Si va a realizar un movimiento
                    // Mostramos los movimientos
                    e2.listaPokes[actualE2].mostrarMovimientos();
                    // while para una respuesta correcta
                    while (true) { 
                        // Leemos la opcion
                        // la guardamos en la misma variable, la cual nos va a 
                        // servir para la eleccion de movimiento mas adelante
                        eleccionE2 = sc.nextInt();
                        // Si no es una respuesta valida
                        if(eleccionE2 < 1 || eleccionE2 > 4){ // damos el mensaje
                            System.out.println("\n\nOpción no válida, intente de nuevo...");
                            // Invocamos la siguiente iteración
                            continue;
                        }
                        // Si está bien, cortamos el while
                        break;
                    }
                    // Cerramos el while
                    break;
                }
                if(eleccionE2 == 2){ // Si va a usar un objeto
                    // Primero verificamos que todavía tenga objetos
                    if(e2.listaObjetos[0].cantidad != 0 && e2.listaObjetos[1].cantidad != 0 && e2.listaObjetos[2].cantidad != 0){
                        
                        while (true) {                            
                            
                            System.out.println("\n\n¿Qué objeto quieres usar? 1. Pocion, 2. Ataque X, 3. Defensa X: ");
                            eleccionE2 = sc.nextInt();
                            // Si ya no hay de ese objeto o elige un numero diferente a 1, 2 o 3
                            if(e1.listaObjetos[eleccionE2-1].cantidad == 0 || eleccionE2 < 1 || eleccionE2 > 3){ 
                                System.out.println("\n\nNo te quedan objetos de ese tipo, intenta con otro...");
                                // Saltamos a la siguiente iteracion
                                continue;
                            }
                            // Se aplica el efecto del objeto
                            e1.listaPokes[actualE2].aplicarObjeto(eleccionE2-1);
                            // Se resta uno al inventario
                            e1.listaObjetos[eleccionE2-1].usar();
                            // Cerramos el while
                            break;
                        }
                        
                    }else{ // si no hay, lo mandamos de regreso a elegir qué hacer
                        System.out.println("\n\nNo te quedan objetos, intenta otra cosa...");
                        continue;
                    }
                    
                    // Ponemos eleccion en 0, para decir que no se lanzó un movimiento
                    eleccionE2 = 0;
                    // cerramos el while
                    break;
                }
                if(eleccionE2 == 3){ // Si va a cambiar de pokemon
                    // Primero verificamos que todavía tenga pokemon listos
                    // con un metodo que nos diga si los otros dos pokes estan debilitados
                    if(!e2.otrosDebilitados(actualE2)){
                        
                        // imprimimos sus pokes
                        e2.getEstadoPokes();
                        
                        while (true) {                            
                            System.out.println("\n\nElige tu siguiente Pokémon:");
                            // Leemos
                            eleccionE2 = sc.nextInt();
                            // revisamos si esta vivo
                            if(!e2.pokeDebilitado(eleccionE2-1)){
                                System.out.println("\n\nHas cambiado de Pokémon");
                                actualE2 = eleccionE2 - 1;
                                // Rompemos el while
                                break;
                            }
                            System.out.println("\n\nPokemon no valido, intenta de nuevo");
                        }
                        
                    }else{ // si no hay, lo mandamos de regreso a elegir qué hacer
                        System.out.println("\n\nNo te quedan pokemon, intenta otra cosa...");
                        continue;
                    }
                    
                    // Ponemos eleccion en 0, para decir que no se lanzó un movimiento
                    eleccionE2 = 0;
                    // Cerramos el while
                    break;
                }

                // Avisamos que su eleccion no es posible y que lo intente de nuevo
                System.out.println("\n\nOpción no válida, intente de nuevo...");
            }
            
            // Revisamos cual poke es más rápido
            
            if(e1.listaPokes[actualE1].velocidad > e2.listaPokes[actualE2].velocidad){ // Si el poke del entrenador 1 es mas rapido
                
                // Si el entrenador 1 y 2 ordenaron movimientos
                if(eleccionE1 != 0 && eleccionE2 != 0){
                    
                    // Se realiza el ataque del primero al segundo
                    e2.listaPokes[actualE2] = e1.listaPokes[actualE1].calculoDaniar(eleccionE1, e2.listaPokes[actualE2]);
                    // Checamos si esta debilitado y ataca si sigue vivo
                    if(!e2.pokeDebilitado(actualE2)){
                        
                        // Ataca
                        e1.listaPokes[actualE1] = e2.listaPokes[actualE2].calculoDaniar(eleccionE2, e1.listaPokes[actualE1]);
                        
                        if(e1.pokeDebilitado(actualE1)){ // Si se debilitó
                            
                            // Si ya no le quedan pokemon
                            if(e1.equipoDebilitado()){

                                System.out.println("\n\n\t\t"+e2.nombre+" ha ganado");
                                return;

                            }
                            // Si todavia tiene pokemon, elige un nuevo pokemon
                            e1.getEstadoPokes();
                            while (true) {                            
                                System.out.println("\n\nElige tu siguiente Pokémon:");
                                // Leemos
                                int cambio;
                                cambio = sc.nextInt();
                                // revisamos si esta vivo
                                if(!e1.pokeDebilitado(cambio-1)){
                                    System.out.println("\n\nHas cambiado de Pokémon");
                                    actualE1 = cambio-1;
                                    // Rompemos el while
                                    break;
                                }
                                System.out.println("\n\nPokemon no valido, intenta de nuevo");
                            }
                            
                        }
                        
                    }else{ // Si se debilitó
                        
                        // Si ya no le quedan pokemon
                        if(e2.equipoDebilitado()){
                            
                            System.out.println("\n\n\t\t"+e1.nombre+" ha ganado");
                            return;
                            
                        }
                        // Si todavia tiene pokemon, elige un nuevo pokemon
                        e2.getEstadoPokes();
                        while (true) {                            
                            System.out.println("\n\nElige tu siguiente Pokémon:");
                            // Leemos
                            int cambio;
                            cambio = sc.nextInt();
                            // revisamos si esta vivo
                            if(!e2.pokeDebilitado(cambio-1)){
                                System.out.println("\n\nHas cambiado de Pokémon");
                                actualE2 = cambio-1;
                                // Rompemos el while
                                break;
                            }
                            System.out.println("\n\nPokemon no valido, intenta de nuevo");
                        }
                    }
                    
                }
                // Si el entrenador 1 no ordenó atacar
                if(eleccionE1 == 0 && eleccionE2 != 0){
                    // Se realiza el ataque del segundo al primero
                    e1.listaPokes[actualE1] = e1.listaPokes[actualE2].calculoDaniar(eleccionE2, e1.listaPokes[actualE1]);
                    
                    if(e1.pokeDebilitado(actualE1)){ // Si se debilitó
                            
                            // Si ya no le quedan pokemon
                            if(e1.equipoDebilitado()){

                                System.out.println("\n\n\t\t"+e2.nombre+" ha ganado");
                                return;

                            }
                            e1.getEstadoPokes();
                            // Si todavia tiene pokemon, elige un nuevo pokemon
                            while (true) {                            
                                System.out.println("\n\nElige tu siguiente Pokémon:");
                                // Leemos
                                int cambio;
                                cambio = sc.nextInt();
                                // revisamos si esta vivo
                                if(!e1.pokeDebilitado(cambio-1)){
                                    System.out.println("\n\nHas cambiado de Pokémon");
                                    actualE1 = cambio-1;
                                    // Rompemos el while
                                    break;
                                }
                                System.out.println("\n\nPokemon no valido, intenta de nuevo");
                            }
                            
                        }
                    
                }
                // Si el entrenador 2 no ordenó atacar
                if(eleccionE1 != 0 && eleccionE2 == 0){
                    // Ataca
                    e2.listaPokes[actualE2] = e1.listaPokes[actualE1].calculoDaniar(eleccionE1, e2.listaPokes[actualE2]);
                    
                    if(e2.pokeDebilitado(actualE2)){ // Si se debilitó
                            
                            // Si ya no le quedan pokemon
                            if(e2.equipoDebilitado()){

                                System.out.println("\n\n\t\t"+e1.nombre+" ha ganado");
                                return;

                            }
                            // Si todavia tiene pokemon, elige un nuevo pokemon
                            e2.getEstadoPokes();
                            while (true) {                            
                                System.out.println("\n\nElige tu siguiente Pokémon:");
                                // Leemos
                                int cambio;
                                cambio = sc.nextInt();
                                // revisamos si esta vivo
                                if(!e2.pokeDebilitado(cambio-1)){
                                    System.out.println("\n\nHas cambiado de Pokémon");
                                    actualE2 = cambio-1;
                                    // Rompemos el while
                                    break;
                                }
                                System.out.println("\n\nPokemon no valido, intenta de nuevo");
                            }
                            
                        }
                    
                }
                
            } else{ // Si es más rápido el del segundo entrenador
                /*
                
                
                    Si el segundo es más rápido
                
                
                */
                // Si los dos atacaron
                if(eleccionE1 != 0 && eleccionE2 != 0){
                    
                    // Se realiza el ataque del segundo al primero
                    e1.listaPokes[actualE1] = e2.listaPokes[actualE2].calculoDaniar(eleccionE2, e1.listaPokes[actualE1]);
                    // Checamos si esta debilitado y ataca si sigue vivo
                    if(!e1.pokeDebilitado(actualE1)){
                        
                        // Ataca
                        e2.listaPokes[actualE2] = e1.listaPokes[actualE1].calculoDaniar(eleccionE1, e2.listaPokes[actualE2]);
                        
                        if(e2.pokeDebilitado(actualE2)){ // Si se debilitó
                            
                            // Si ya no le quedan pokemon
                            if(e2.equipoDebilitado()){

                                System.out.println("\n\n\t\t"+e1.nombre+" ha ganado");
                                return;

                            }
                            // Si todavia tiene pokemon, elige un nuevo pokemon
                            e2.getEstadoPokes();
                            while (true) {                            
                                System.out.println("\n\nElige tu siguiente Pokémon:");
                                // Leemos
                                int cambio;
                                cambio = sc.nextInt();
                                // revisamos si esta vivo
                                if(!e2.pokeDebilitado(cambio-1)){
                                    System.out.println("\n\nHas cambiado de Pokémon");
                                    actualE2 = cambio-1;
                                    // Rompemos el while
                                    break;
                                }
                                System.out.println("\n\nPokemon no valido, intenta de nuevo");
                            }
                            
                        }
                        
                    }else{ // Si se debilitó
                        
                        // Si ya no le quedan pokemon
                        if(e1.equipoDebilitado()){
                            
                            System.out.println("\n\n\t\t"+e2.nombre+" ha ganado");
                            return;
                            
                        }
                        // Si todavia tiene pokemon, elige un nuevo pokemon
                        e1.getEstadoPokes();
                        while (true) {                            
                            System.out.println("\n\nElige tu siguiente Pokémon:");
                            // Leemos
                            int cambio;
                            cambio = sc.nextInt();
                            // revisamos si esta vivo
                            if(!e1.pokeDebilitado(cambio-1)){
                                System.out.println("\n\nHas cambiado de Pokémon");
                                actualE1 = cambio-1;
                                // Rompemos el while
                                break;
                            }
                            System.out.println("\n\nPokemon no valido, intenta de nuevo");
                        }
                    }
                    
                    // Si el entrenador 1 no ordenó atacar
                if(eleccionE1 == 0 && eleccionE2 != 0){
                    // Se realiza el ataque del segundo al primero
                    e1.listaPokes[actualE1] = e1.listaPokes[actualE2].calculoDaniar(eleccionE2, e1.listaPokes[actualE1]);
                    
                    if(e1.pokeDebilitado(actualE1)){ // Si se debilitó
                            
                            // Si ya no le quedan pokemon
                            if(e1.equipoDebilitado()){

                                System.out.println("\n\n\t\t"+e2.nombre+" ha ganado");
                                return;

                            }
                            // Si todavia tiene pokemon, elige un nuevo pokemon
                            e1.getEstadoPokes();
                            while (true) {                            
                                System.out.println("\n\nElige tu siguiente Pokémon:");
                                // Leemos
                                int cambio;
                                cambio = sc.nextInt();
                                // revisamos si esta vivo
                                if(!e1.pokeDebilitado(cambio-1)){
                                    System.out.println("\n\nHas cambiado de Pokémon");
                                    actualE1 = cambio-1;
                                    // Rompemos el while
                                    break;
                                }
                                System.out.println("\n\nPokemon no valido, intenta de nuevo");
                            }
                            
                        }
                    
                }
                // Si el entrenador 2 no ordenó atacar
                if(eleccionE1 != 0 && eleccionE2 == 0){
                    // Ataca
                    e2.listaPokes[actualE2] = e1.listaPokes[actualE1].calculoDaniar(eleccionE1, e2.listaPokes[actualE2]);
                    
                    if(e2.pokeDebilitado(actualE2)){ // Si se debilitó
                            
                            // Si ya no le quedan pokemon
                            if(e2.equipoDebilitado()){

                                System.out.println("\n\n\t\t"+e1.nombre+" ha ganado");
                                return;

                            }
                            // Si todavia tiene pokemon, elige un nuevo pokemon
                            e2.getEstadoPokes();
                            while (true) {                            
                                System.out.println("\n\nElige tu siguiente Pokémon:");
                                // Leemos
                                int cambio;
                                cambio = sc.nextInt();
                                // revisamos si esta vivo
                                if(!e2.pokeDebilitado(cambio-1)){
                                    System.out.println("\n\nHas cambiado de Pokémon");
                                    actualE2 = cambio-1;
                                    // Rompemos el while
                                    break;
                                }
                                System.out.println("\n\nPokemon no valido, intenta de nuevo");
                            }
                            
                        }
                    
                    }
                    
                }
                
            }
            
        }
        
    }
    
}
