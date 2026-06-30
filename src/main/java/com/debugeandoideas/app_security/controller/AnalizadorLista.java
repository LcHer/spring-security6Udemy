package com.debugeandoideas.app_security.controller;

import java.util.HashMap;
import java.util.Map;

public class AnalizadorLista {
    public static void main(String[] args) {
        // Tu lista original de números
        int[] numeros = {3, 8, 5, 3, 9, 1, 8, 7, 5};

        // Mapa para almacenar la frecuencia de cada número
        Map<Integer, Integer> frecuencias = new HashMap<>();

        // 1. Contar las apariciones de cada número
        for (int num : numeros) {
            frecuencias.put(num, frecuencias.getOrDefault(num, 0) + 1);
        }

        System.out.println("--- RESULTADOS DEL ANÁLISIS ---");

        // Variables para contar el total de duplicados y únicos
        int totalDuplicados = 0;

        System.out.println("\n> VALORES DUPLICADOS Y SUS REPETICIONES:");
        for (Map.Entry<Integer, Integer> entrada : frecuencias.entrySet()) {
            if (entrada.getValue() > 1) {
                System.out.println("El número " + entrada.getKey() + " aparece " + entrada.getValue() + " veces.");
                totalDuplicados++; // Cuenta cuántos números diferentes se duplicaron
            }
        }

        System.out.println("\n> VALORES ÚNICOS (aparecen solo 1 vez):");
        for (Map.Entry<Integer, Integer> entrada : frecuencias.entrySet()) {
            if (entrada.getValue() == 1) {
                System.out.print(entrada.getKey() + " ");
            }
        }

        System.out.println("\n\n-------------------------------");
        System.out.println("Cantidad de números que tienen duplicados: " + totalDuplicados);
    }

}
