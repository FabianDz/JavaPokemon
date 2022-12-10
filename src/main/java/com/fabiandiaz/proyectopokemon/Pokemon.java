/*
 * Proyecto del curso Prebecario Gen. 43 de JAVA | PROTECO
 * Clase Pokemon
 */
package com.fabiandiaz.proyectopokemon;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Fabián Josafat Díaz Silleros
 */
public class Pokemon {
    // Atributos
    private String nombre;
    public String apodo;
    public int vida; // Este es la estadistica de vida (vida total)
    public int hp; // Esta vida es la que se modifica en batalla
    private int ataque;
    private int defensa;
    public int velocidad;
    public String estado;
    public String tipo;
    public Movimiento movimientos[] = new Movimiento[4];
    
    // Constructor
    public Pokemon(String nombreP, String apodo){
        // String base de la URL de la API
        String baseURL = "https://pokeapi.co/api/v2/pokemon/";
        // Hacemos todas minusculas el nombre del pokemon y se lo agregamos a la URL
        nombreP = nombreP.toLowerCase();
        String urlCompleta = baseURL+nombreP;
        
        
        // Asignamos los valores de los atributos
        this.nombre = nombreP;
        this.apodo = apodo;
        this.vida = this.obtenerStat(urlCompleta, 0); // Indice 0 para la vida
        this.hp = this.vida;
        // Los ataques y defensas se van a sacar del promedio de la fisica y especial
        // Indice 2 para el ataque, 4 para el ataque especial
        this.ataque = (this.obtenerStat(urlCompleta, 1) + this.obtenerStat(urlCompleta, 3)) / 2; 
        // Indice 3 para la defensa, 5 para el ataque especial
        this.defensa = (this.obtenerStat(urlCompleta, 2) + this.obtenerStat(urlCompleta, 4)) / 2;
        this.velocidad = this.obtenerStat(urlCompleta, 5); // Indice 5 para la velocidad
        this.estado = "Preparado";
        this.tipo = this.obtenerTipo(urlCompleta);
        
        // Asignamos los movimientos
        switch (this.tipo) {
            case "grass" -> { // Movimientos Planta
                // Primer y segundo movimiento definido por su tipo
                this.movimientos[0] = new Movimiento("Danza Petalo", 110, null, "grass");
                // El segundo movimiento es uno de estado
                this.movimientos[1] = new Movimiento("Somnifero", 0, "dormir", "grass");
                // Tercer movimiento para rellenar
                this.movimientos[2] = new Movimiento("Puño Trueno", 75, null, "electric");
                
                // Cuarto Movimiento dependiendo del poke
                switch (this.nombre) {
                    case "sceptile":
                        // Si es sceptile
                        this.movimientos[3] = new Movimiento("Hoja Aguda", 90, null, "grass");
                        break;
                    case "rillaboom":
                        // Si es Rillaboom
                        this.movimientos[3] = new Movimiento("Mazazo", 120, null, "grass");
                        break;
                    default: // Si es cualquier otro poke (como venusaur)
                        this.movimientos[3] = new Movimiento("Rayo Solar", 120, null, "grass");
                        break;
                }
            }
            case "fire" -> { // Movimientos Fuego
                // Primer y segundo movimiento definido por su tipo
                this.movimientos[0] = new Movimiento("Llamarada", 110, null, "fire");
                // El segundo movimiento es uno de estado
                this.movimientos[1] = new Movimiento("Fuego Fatuo", 0, "quemar", "fire");
                // Tercer movimiento para cubrir debilidades
                this.movimientos[2] = new Movimiento("Puño Trueno", 75, null, "electric");
                
                // Cuarto Movimiento dependiendo del poke
                switch (this.nombre) {
                    case "typhlosion":
                        // Si es typhlosion
                        this.movimientos[3] = new Movimiento("Sofoco", 130, null, "fire");
                        break;
                    case "cinderace":
                        // Si es Cinderace
                        this.movimientos[3] = new Movimiento("Balon Igneo", 120, null, "fire");
                        break;
                    default: // Si es cualquier otro poke (como Flareon)
                        this.movimientos[3] = new Movimiento("Envite Igneo", 120, null, "fire");
                        break;
                }
                
            }
            case "water" -> { // Movimientos Agua
                // Primer y segundo movimiento definido por su tipo
                this.movimientos[0] = new Movimiento("Hidrobomba", 110, null, "water");
                // El segundo movimiento es uno de estado
                this.movimientos[1] = new Movimiento("Toxico", 0, "envenenar", "poison");
                // Tercer movimiento para cubrir debilidades
                this.movimientos[2] = new Movimiento("Puño Fuego", 75, null, "fire");
                
                // Cuarto Movimiento dependiendo del poke
                switch (this.nombre) {
                    case "blastoise":
                        // Si es blastoise
                        this.movimientos[3] = new Movimiento("Hidrocañon", 150, null, "water");
                        break;
                    case "milotic":
                        // Si es Milotic
                        this.movimientos[3] = new Movimiento("Salpicar", 150, null, "water");
                        break;
                    default: // Si es cualquier otro poke (como Vaporeon)
                        this.movimientos[3] = new Movimiento("Surf", 150, null, "water");
                        break;
                }
                
            }
            case "electric" -> { // Movimientos Electrico
                // Primer y segundo movimiento definido por su tipo
                this.movimientos[0] = new Movimiento("Trueno", 110, null, "electric");
                // El segundo movimiento es uno de estado
                this.movimientos[1] = new Movimiento("Onda Trueno", 0, "paralizar", "electric");
                // Tercer movimiento para rellenar
                this.movimientos[2] = new Movimiento("Puño Fuego", 75, null, "electric");
                
                // Cuarto Movimiento dependiendo del poke
                switch (this.nombre) {
                    case "luxray":
                        // Si es luxray
                        this.movimientos[3] = new Movimiento("Voltio Cruel", 120, null, "electric");
                        break;
                    case "electivire":
                        // Si es electivire
                        this.movimientos[3] = new Movimiento("Electrocañon", 120, null, "electric");
                        break;
                    default: // Si es cualquier otro poke (como Jolteon)
                        this.movimientos[3] = new Movimiento("Placaje Electrico", 120, null, "electric");
                        break;
                }
                
            }
            default -> { // Si es cualquier pokemon no contemplado
                // Rellenamos con ataques random, dos de estado y dos de daño
                this.movimientos[0] = new Movimiento("Fuego Fatuo", 0, "quemar", "fire");
                this.movimientos[1] = new Movimiento("Toxico", 0, "envenenar", "poison");
                this.movimientos[2] = new Movimiento("Puño Trueno", 75, null, "electric");
                this.movimientos[3] = new Movimiento("Puño Fuego", 75, null, "fire");
            }
        }
        
    }
    
    // Método para obtener una Estadística del poke
    private int obtenerStat(String urlCompleta, int indice){
        
        // Hacemos un try catch
        try {
            // Creamos la url del pokemon y hacemos la conexión GET con la API
            URL url = new URL(urlCompleta);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            // Comprobamos el codigo de respuesta
            int responseCode = conn.getResponseCode();
            // Si no se pudo hacer la conexion
            if(responseCode != 200){
                
                throw new RuntimeException("\n\n\tNo se puedo establecer conexión: "+responseCode);
                
            } else{ // Si se pudo realizar la conexion
                
                StringBuilder informationString = new StringBuilder();
                // Leemos todo y lo guaradmos en information String
                try ( // Leemos el flujo de datos que da la API
                        Scanner sc = new Scanner(url.openStream())) {
                    // Leemos todo y lo guaradmos en information String
                    while (sc.hasNext()) {
                        informationString.append(sc.nextLine());
                    }
                    // Cerramos el scanner
                }
                        
                // Convertimos a String normal la informacion
                String json = informationString.toString();
                // Hacemos JSONObject a la String
                JSONObject jsonObject = new JSONObject(json);
                // Obtenemos el arreglo del JSONObject valor de la llave types
                JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("stats"));
                // Asignamos de regreso el JSON contenido en el Arreglo (el stat deseado)
                jsonObject = jsonArray.getJSONObject(indice);
                // Retornamos el valor del stat
                return jsonObject.getInt("base_stat");
            }
            
        } catch (IOException | RuntimeException e) {
            System.err.println("\n\n\t\tOcurrió un error: "+e);
        }
        
        return 0;
        
    }
    
    // Método para obtener el tipo por medio de la API
    private String obtenerTipo(String urlCompleta){
        
        // Hacemos un try catch
        try {
            // Creamos la url del pokemon y hacemos la conexión GET con la API
            URL url = new URL(urlCompleta);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            // Comprobamos el codigo de respuesta
            int responseCode = conn.getResponseCode();
            // Si no se pudo hacer la conexion
            if(responseCode != 200){
                
                throw new RuntimeException("\n\n\tNo se puedo establecer conexión: "+responseCode);
                
            } else{ // Si se pudo realizar la conexion
                
                StringBuilder informationString = new StringBuilder();
                // Leemos todo y lo guaradmos en information String
                try ( // Leemos el flujo de datos que da la API
                        Scanner sc = new Scanner(url.openStream())) {
                    // Leemos todo y lo guaradmos en information String
                    while (sc.hasNext()) {
                        informationString.append(sc.nextLine());
                    }
                    // Cerramos el scanner
                }
                        
                // Convertimos a String normal la informacion
                String json = informationString.toString();
                // Hacemos JSONObject a la String
                JSONObject jsonObject = new JSONObject(json);
                // Obtenemos el arreglo del JSONObject valor de la llave types
                JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("types"));
                // Asignamos de regreso el JSON contenido en el Arreglo
                jsonObject = jsonArray.getJSONObject(0);
                // Asignamos dentro del mismo objeto el objeto tipo
                jsonObject = jsonObject.getJSONObject("type");
                // Asignamos dentro del mismo objeto el nombre del tipo
                return jsonObject.getString("name");
            }
            
        } catch (IOException | RuntimeException e) {
            System.err.println("\n\n\t\tOcurrió un error: "+e);
        }
        
        return null;
    }
    
    public void mostrarMovimientos(){
        // Imprimimos el nombre del movimiento, daño y el efecto
        System.out.println("\n\nMovimientos: \t1. "+this.movimientos[0].nombre+" Daño: "+this.movimientos[0].danio+" Efecto: "+this.movimientos[0].efecto);
        System.out.println("\n\nMovimientos: \t2. "+this.movimientos[1].nombre+" Daño: "+this.movimientos[1].danio+" Efecto: "+this.movimientos[1].efecto);
        System.out.println("\n\nMovimientos: \t3. "+this.movimientos[2].nombre+" Daño: "+this.movimientos[2].danio+" Efecto: "+this.movimientos[2].efecto);
        System.out.println("\n\nMovimientos: \t4. "+this.movimientos[3].nombre+" Daño: "+this.movimientos[3].danio+" Efecto: "+this.movimientos[3].efecto);
        
    }
    
    public void aplicarObjeto(int obj){
        // Variables para hacer los cálculas
        double cantidad;
        int truncar;
        switch (obj) {
            case 0 -> { // Caso poción
                // Vamos a curarle 75% de la vida
                cantidad = this.vida * 0.75; // almacenamos el 75 %
                truncar = (int) cantidad; // lo truncamos
                this.hp += truncar; // le sumamos la vida actual más el 75%
                if(this.hp > this.vida){ // Si rebasó el limite de vida
                    this.hp = this.vida; // Le asignamos el máximo de vida
                }
                System.out.println("\n\n\tSe ha curado a "+this.apodo+"\n");
                return; // Retornamos
            }
            case 1 -> { // Caso Ataque X
                // Vamos a subirle el ataque por 1.5
                cantidad = this.ataque * 1.5; // almacenamos el 1.5 %
                truncar = (int) cantidad; // lo truncamos
                this.ataque = truncar; // Asignamos el nuevo ataque
                System.out.println("\n\n\tEl ataque de "+this.apodo+" ha subido\n");
                return; // Retornamos
            }
            case 2 -> { // Caso Defensa X
                // Vamos a subirle la defensa por 1.5
                cantidad = this.defensa * 1.5; // almacenamos el 1.5 %
                truncar = (int) cantidad; // lo truncamos
                this.defensa = truncar; // Asignamos el nuevo ataque
                System.out.println("\n\n\tLa defensa de "+this.apodo+" ha subido\n");
                return; // Retornamos
            }
            default -> throw new AssertionError();
        }  
    }
    
    public Pokemon calculoDaniar(int mov, Pokemon atacado){
        mov -= 1; // Le restamos uno para no generar errores en arreglos
        // Comprobamos si está paralizado (se baja la presicion)
        if("Paralizado".equals(this.estado)){
            this.movimientos[mov].presicion = 60;
        }
        // Primero comprobamos si el golpe fue acertado
        double prob = (double) this.movimientos[mov].presicion / 100;
        double rand = Math.random();
        if(rand > prob){
            System.out.println("\n\n\t"+this.apodo+" falló el movimiento");
            return atacado;
        }
        // Creamos la variable del daño a realizar
        double danio;
        // Asignamos el daño del movimiento a danio
        // la formula que voy a usar va a ser:
        // (((Daño Movimiento + Daño Pokemon) / 2) * STAB[1.5])
        // - Defensa del otro * (debilidad, neutro o fortaleza)
        // Si está quemado, se le restan 20 de daño
        danio = (double) (this.movimientos[mov].danio + this.ataque) / 2;
        if("Quemado".equals(this.estado)){
            danio -= 20;
        }
        // Si tiene STAB (si el poke es del mismo tipo del movimiento)
        if(this.tipo.equals(this.movimientos[mov].tipo)){
            danio *= 1.5;
        }
        // llamamos la funcion que recibe el daño al otro poke
        return this.calculoRecibirDanio(this.movimientos[mov], danio, atacado);
    }
    
    public Pokemon calculoRecibirDanio(Movimiento mov, double danio, Pokemon atacado){
        // Hacemos el calculo de daño recibido
        // al danio recibido le restamos la defensa y * el tipo
        danio -= atacado.defensa;
        // Si esta dormdo, se le suman 10 de daño
        if("Dormido".equals(atacado.estado)){
            danio += 10;
        }
        
        // Si el poke es tipo plata, grass
        if("grass".equals(atacado.tipo)){
            // Swtich para cada caso de movimiento
            switch (mov.tipo) {
                case "grass" -> { // Caso tipo planta
                    danio *= 0.5; // Resistente
                }
                case "fire" -> { // Caso tipo fuego
                    danio *= 2; // Debil
                }
                case "water" -> { // Caso tipo awa
                    danio *= 0.5; // Resistente
                }
            }
        }
        // Si es tipo fuego
        if("fire".equals(mov.tipo)){
            // Swtich para cada caso de movimiento
            switch (tipo) {
                case "grass" -> { // Caso tipo planta
                    danio *= 0.5; // Resistente
                }
                case "fire" -> { // Caso tipo fuego
                    danio *= 0.5; // Resistente
                }
                case "water" -> { // Caso tipo awa
                    danio *= 2; // Debil
                }
            }
        }
        // Si es tipo awa
        if("water".equals(mov.tipo)){
            // Swtich para cada caso de movimiento
            switch (tipo) {
                case "water" -> { // Caso tipo water
                    danio *= 0.5; // Resistente
                }
                case "fire" -> { // Caso tipo fuego
                    danio *= 0.5; // Resistente
                }
                case "grass" -> { // Caso tipo planta
                    danio *= 2; // Debil
                }
                case "electric" -> { // Caso tipo electrico
                    danio *= 2; // Debil
                }
            }
        }
        // Si es tipo electrico, no hacemos calculo, todo le pega x1
        if(mov.efecto != null){
            if("envenenar".equals(mov.efecto)){
                atacado.estado = "Envenedado";
                System.out.println("\n\n\t"+atacado.apodo+" se ha envenenado");
            }
            if("quemar".equals(mov.efecto)){
                atacado.estado = "Quemado";
                System.out.println("\n\n\t"+atacado.apodo+" se ha quemado");
            }
            if("paralizar".equals(mov.efecto)){
                atacado.estado = "Paralizado";
                System.out.println("\n\n\t"+atacado.apodo+" se ha paralizado");
            }
            if("dormir".equals(mov.efecto)){
                atacado.estado = "Dormido";
                System.out.println("\n\n\t"+atacado.apodo+" se ha dormido");
            }
            return atacado;
        }
        // Truncamos el daño
        int truncado = (int) danio;
        // Ahora sí restamos la vida quitada
        atacado.hp = atacado.hp - truncado;
        
        System.out.println("\n\t"+atacado.apodo+" ha sido dañado");
        
        if(atacado.hp < 1){
            atacado.estado = "Debilitado";
            System.out.println("\n\n\t"+atacado.apodo+" se ha debilitado");
        }
        
        return atacado;
    }
}
