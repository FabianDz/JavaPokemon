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
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Fabián Josafat Díaz Silleros
 */
public class Pokemon {
    // Atributos
    private String nombre;
    public String apodo;
    public int hp;
    private int ataque;
    private int defensa;
    private int velocidad;
    public String estado;
    public String tipo;
    public Movimiento m1;
    public Movimiento m2;
    public Movimiento m3;
    public Movimiento m4;
    
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
        this.hp = this.obtenerStat(urlCompleta, 0); // Indice 0 para la vida
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
                this.m1 = new Movimiento("Danza Petalo", 110, null, "grass");
                // El segundo movimiento es uno de estado
                this.m2 = new Movimiento("Somnifero", 0, "dormir", "grass");
                // Tercer movimiento para rellenar
                this.m3 = new Movimiento("Puño Trueno", 75, null, "electric");
                
                // Cuarto Movimiento dependiendo del poke
                switch (this.nombre) {
                    case "sceptile":
                        // Si es sceptile
                        this.m4 = new Movimiento("Hoja Aguda", 90, null, "grass");
                        break;
                    case "rillaboom":
                        // Si es Rillaboom
                        this.m4 = new Movimiento("Mazazo", 120, null, "grass");
                        break;
                    default: // Si es cualquier otro poke (como venusaur)
                        this.m4 = new Movimiento("Rayo Solar", 120, null, "grass");
                        break;
                }
            }
            case "fire" -> { // Movimientos Fuego
                // Primer y segundo movimiento definido por su tipo
                this.m1 = new Movimiento("Llamarada", 110, null, "fire");
                // El segundo movimiento es uno de estado
                this.m2 = new Movimiento("Fuego Fatuo", 0, "quemar", "fire");
                // Tercer movimiento para cubrir debilidades
                this.m3 = new Movimiento("Puño Trueno", 75, null, "electric");
                
                // Cuarto Movimiento dependiendo del poke
                switch (this.nombre) {
                    case "typhlosion":
                        // Si es typhlosion
                        this.m4 = new Movimiento("Sofoco", 130, null, "fire");
                        break;
                    case "cinderace":
                        // Si es Cinderace
                        this.m4 = new Movimiento("Balon Igneo", 120, null, "fire");
                        break;
                    default: // Si es cualquier otro poke (como Darmanitan)
                        this.m4 = new Movimiento("Envite Igneo", 120, null, "fire");
                        break;
                }
                
            }
            case "water" -> { // Movimientos Agua
                // Primer y segundo movimiento definido por su tipo
                this.m1 = new Movimiento("Hidrobomba", 110, null, "water");
                // El segundo movimiento es uno de estado
                this.m2 = new Movimiento("Toxico", 0, "envenenar", "poison");
                // Tercer movimiento para cubrir debilidades
                this.m3 = new Movimiento("Puño Fuego", 75, null, "fire");
                
                // Cuarto Movimiento dependiendo del poke
                switch (this.nombre) {
                    case "blastoise":
                        // Si es blastoise
                        this.m4 = new Movimiento("Hidrocañon", 150, null, "water");
                        break;
                    case "milotic":
                        // Si es Milotic
                        this.m4 = new Movimiento("Salpicar", 150, null, "water");
                        break;
                    default: // Si es cualquier otro poke (como Vaporeon)
                        this.m4 = new Movimiento("Surf", 150, null, "water");
                        break;
                }
                
            }
            case "electric" -> { // Movimientos Electrico
                // Primer y segundo movimiento definido por su tipo
                this.m1 = new Movimiento("Trueno", 110, null, "electric");
                // El segundo movimiento es uno de estado
                this.m2 = new Movimiento("Onda Trueno", 0, "paralizar", "electric");
                // Tercer movimiento para rellenar
                this.m3 = new Movimiento("Puño Fuego", 75, null, "electric");
                
                // Cuarto Movimiento dependiendo del poke
                switch (this.nombre) {
                    case "luxray":
                        // Si es luxray
                        this.m4 = new Movimiento("Voltio Cruel", 120, null, "electric");
                        break;
                    case "electivire":
                        // Si es electivire
                        this.m4 = new Movimiento("Electrocañon", 120, null, "electric");
                        break;
                    default: // Si es cualquier otro poke (como Jolteon)
                        this.m4 = new Movimiento("Placaje Electrico", 120, null, "electric");
                        break;
                }
                
            }
            default -> { // Si es cualquier pokemon no contemplado
                // Rellenamos con ataques random, dos de estado y dos de daño
                this.m1 = new Movimiento("Fuego Fatuo", 0, "quemar", "fire");
                this.m2 = new Movimiento("Toxico", 0, "envenenar", "poison");
                this.m3 = new Movimiento("Puño Trueno", 75, null, "electric");
                this.m4 = new Movimiento("Puño Fuego", 75, null, "fire");
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
    
}
