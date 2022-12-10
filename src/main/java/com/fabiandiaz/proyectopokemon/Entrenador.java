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

    // Método getEntrenador (prints)
    public void getEntrenador(){
        System.out.println("\n\nNombre: "+this.nombre);
        System.out.println("Primer Pokemon: "+this.listaPokes[0].apodo);
        System.out.println("Segundo Pokemon: "+this.listaPokes[1].apodo);
        System.out.println("Tercer Pokemon: "+this.listaPokes[2].apodo);
        System.out.println("Objetos:"+this.listaObjetos[0].nombre+"*"+this.listaObjetos[0].cantidad);
        System.out.println("\t"+this.listaObjetos[1].nombre+"*"+this.listaObjetos[1].cantidad);
        System.out.println("\t"+this.listaObjetos[2].nombre+"*"+this.listaObjetos[2].cantidad);
        
    }
    
    public void getEstadoPokes(){
        // Dependiendo del estado del pokemon
        switch (this.listaPokes[0].estado) {
            // Si esta debilitado solo su apodo y que esta debilitado
            case "Debilitado" -> System.out.println("\n\n\t1."+this.listaPokes[0].apodo+" "+this.listaPokes[0].estado);
            // Si no esta debilitado ni un estado alterado, su apodo y vida
            case "Preparado" -> System.out.println("\n\n\t1."+this.listaPokes[0].apodo+" "+this.listaPokes[0].hp+"/"+this.listaPokes[0].vida+" HP");
            // Si tiene estado alterado, imprime su apodo, estado y vida
            default -> System.out.println("\n\n\t1."+this.listaPokes[0].apodo+" "+this.listaPokes[0].estado+" "+this.listaPokes[0].hp+"/"+this.listaPokes[0].vida+" HP");
        }
        // Dependiendo del estado del pokemon
        switch (this.listaPokes[1].estado) {
            // Si esta debilitado solo su apodo y que esta debilitado
            case "Debilitado" -> System.out.println("\n\n\t2."+this.listaPokes[1].apodo+" "+this.listaPokes[1].estado);
            // Si no esta debilitado ni un estado alterado, su apodo y vida
            case "Preparado" -> System.out.println("\n\n\t2."+this.listaPokes[1].apodo+" "+this.listaPokes[1].hp+"/"+this.listaPokes[1].vida+" HP");
            // Si tiene estado alterado, imprime su apodo, estado y vida
            default -> System.out.println("\n\n\t2."+this.listaPokes[1].apodo+" "+this.listaPokes[1].estado+" "+this.listaPokes[1].hp+"/"+this.listaPokes[1].vida+" HP");
        }
        // Dependiendo del estado del pokemon
        switch (this.listaPokes[2].estado) {
            // Si esta debilitado solo su apodo y que esta debilitado
            case "Debilitado" -> System.out.println("\n\n\t3."+this.listaPokes[2].apodo+" "+this.listaPokes[2].estado);
            // Si no esta debilitado ni un estado alterado, su apodo y vida
            case "Preparado" -> System.out.println("\n\n\t3."+this.listaPokes[2].apodo+" "+this.listaPokes[2].hp+"/"+this.listaPokes[2].vida+" HP");
            // Si tiene estado alterado, imprime su apodo, estado y vida
            default -> System.out.println("\n\n\t3."+this.listaPokes[2].apodo+" "+this.listaPokes[2].estado+" "+this.listaPokes[2].hp+"/"+this.listaPokes[2].vida+" HP");
        }
    }
    
    public void getCantObj(){
        if(this.listaObjetos[0].cantidad > 0){
            System.out.println("\n\n\t"+this.listaObjetos[0].nombre+" cantidad: "+this.listaObjetos[0].cantidad);
        }
        
        if(this.listaObjetos[1].cantidad > 0){
            System.out.println("\n\n\t"+this.listaObjetos[1].nombre+" cantidad: "+this.listaObjetos[1].cantidad);
        }
        
        if(this.listaObjetos[2].cantidad > 0){
            System.out.println("\n\n\t"+this.listaObjetos[2].nombre+" cantidad: "+this.listaObjetos[2].cantidad);
        }
    }
    
    public boolean pokeDebilitado(int nPoke){
        // Retornamos verdadero si está debilitado, falso si está vivo
        return "Debilitado".equals(this.listaPokes[nPoke].estado);
    }
    
    public boolean otrosDebilitados(int actual){
        
        // Si el poke actual es el 0 y los otros dos están debilitados
        if(actual == 0 && "Debilitado".equals(this.listaPokes[1].estado) && "Debilitado".equals(this.listaPokes[2].estado)){
            // Retornamos verdadero
            return true;
        }
        
        if(actual == 1 && "Debilitado".equals(this.listaPokes[0].estado) && "Debilitado".equals(this.listaPokes[2].estado)){
            // Retornamos verdadero
            return true;
        }
        
        if(actual == 2 && "Debilitado".equals(this.listaPokes[1].estado) && "Debilitado".equals(this.listaPokes[0].estado)){
            // Retornamos verdadero
            return true;
        }
        
        return false;
        
    }
    
    public boolean equipoDebilitado(){
        // Retornamos verdadero si todos están debilitados, falso si al menos uno sigue vivo
        return "Debilitado".equals(this.listaPokes[0].estado) && "Debilitado".equals(this.listaPokes[1].estado) && "Debilitado".equals(this.listaPokes[2].estado);
        
    }
    
}
