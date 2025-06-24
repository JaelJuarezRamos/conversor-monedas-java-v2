package com.Jael;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== CONVERSOR DE MONEDAS ===");
            System.out.println("1. USD a MXN");
            System.out.println("2. MXN a USD");
            System.out.println("3. Ver historial de conversiones");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            int opcion;

            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(" Ingresa solo números del 1 al 4.");
                scanner.nextLine(); // limpiar buffer
                continue;
            }

            if (opcion == 4) {
                System.out.println("¡Hasta pronto!");
                break;
            }


            if (opcion != 1 && opcion != 2) {
                System.out.println(" Opción inválida.");
                continue;
            }

            System.out.print("Ingresa la cantidad: ");
            double cantidad;

            try {
                cantidad = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println(" Ingresa una cantidad válida.");
                scanner.nextLine(); // limpiar buffer
                continue;
            }

            String origen = (opcion == 1) ? "USD" : "MXN";
            String destino = (opcion == 1) ? "MXN" : "USD";

            double tasa = ApiService.obtenerTasaCambio(origen, destino);

            if (tasa > 0) {
                double resultado = cantidad * tasa;
                System.out.printf("%.2f %s equivale a %.2f %s%n", cantidad, origen, resultado, destino);

            } else {
                System.out.println("No se pudo obtener la tasa de cambio.");
            }

            System.out.println(); // salto de línea
        }

        scanner.close();
    }
}


