package com.Jael;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistorialService {

    private static final String ARCHIVO = "historial.json";

    public static void guardarConversion(String origen, String destino, double cantidad, double resultado) {
        try (FileWriter writer = new FileWriter(ARCHIVO, true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaHora = LocalDateTime.now().format(formatter);

            String linea = String.format("{ \"fecha\": \"%s\", \"origen\": \"%s\", \"destino\": \"%s\", \"cantidad\": %.2f, \"resultado\": %.2f }",
                    fechaHora, origen, destino, cantidad, resultado);

            writer.write(linea + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    public static void mostrarHistorial() {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("No hay historial disponible.");
            return;
        }

        try (FileReader reader = new FileReader(archivo);
             BufferedReader br = new BufferedReader(reader)) {

            String linea;
            System.out.println("\n--- HISTORIAL DE CONVERSIONES ---");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el historial: " + e.getMessage());
        }
    }
}
