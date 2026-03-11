package org.example.utils;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class InputUtil {

    private InputUtil() {
    }

    public static int readInt(Scanner scanner, String prompt) {

        while (true) {

            System.out.print(prompt);

            if (scanner.hasNextInt()) {

                int input = scanner.nextInt();
                scanner.nextLine();
                return input;

            } else {

                System.out.println("Entrada inválida. Ingrese un número.");
                scanner.nextLine();
            }
        }
    }
}