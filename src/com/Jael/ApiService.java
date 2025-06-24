package com.Jael;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {

    private static final String API_KEY = "API-KEY";  // ← El que lo use debe reemplazarlo

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static double obtenerTasaCambio(String monedaBase, String monedaDestino) {
        try {
            String urlStr = BASE_URL + API_KEY + "/pair/" + monedaBase + "/" + monedaDestino;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                System.out.println("Error: código de respuesta " + conn.getResponseCode());
                return -1;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                json.append(linea);
            }

            reader.close();

            // Extraer tasa de cambio del JSON
            String respuesta = json.toString();
            int inicio = respuesta.indexOf("conversion_rate") + 17;
            int fin = respuesta.indexOf(",", inicio);
            if (fin == -1) {
                fin = respuesta.indexOf("}", inicio); // por si es el último campo
            }

            String tasaStr = respuesta.substring(inicio, fin).trim();

            return Double.parseDouble(tasaStr);

        } catch (Exception e) {
            System.out.println("Error al obtener tasa de cambio: " + e.getMessage());
            return -1;
        }
    }
}

