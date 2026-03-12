package org.example.app;

import org.example.enums.MaterialStatus;
import org.example.exceptions.InvalidLoanException;
import org.example.exceptions.MaterialNotFoundException;
import org.example.exceptions.UserNotFoundException;
import org.example.models.*;
import org.example.services.LibraryService;
import org.example.utils.InputUtil;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings("java:S106")
public class MenuController {

    private final LibraryService library;
    private final Scanner scanner;

    public MenuController() {
        this.library = new LibraryService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        int option;

        do {

            showMainMenu();
            option = InputUtil.readInt(scanner, "Seleccione una opción: ");

            try {

                switch (option) {

                    case 1 -> userMenu();
                    case 2 -> materialMenu();
                    case 3 -> loanMenu();
                    case 4 -> showSystemInfo();
                    case 0 -> System.out.println("Saliendo del sistema...");

                    default -> System.out.println("Opción inválida");
                }

            } catch (UserNotFoundException | MaterialNotFoundException | InvalidLoanException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            }

        } while (option != 0);
    }

    private void showMainMenu() {

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("1 - Gestión de usuarios");
        System.out.println("2 - Gestión de materiales");
        System.out.println("3 - Gestión de préstamos");
        System.out.println("4 - Información del sistema");
        System.out.println("0 - Salir");
    }

    // =============================
    // USER MENU
    // =============================

    private void userMenu() {

        int option;

        do {

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("--- GESTIÓN DE USUARIOS ---");
            System.out.println("1 - Registrar usuario");
            System.out.println("2 - Listar usuarios");
            System.out.println("3 - Buscar usuario por ID");
            System.out.println("4 - Buscar usuarios por nombre");
            System.out.println("5 - Usuario con más préstamos");
            System.out.println("0 - Volver");

            option = InputUtil.readInt(scanner, "Seleccione una opción: ");

            switch (option) {

                case 1 -> registerUser();
                case 2 -> viewUsers();
                case 3 -> searchUserById();
                case 4 -> searchUsersByName();
                case 5 -> viewUserWithMostLoans();
            }

            if (option < 6 && option != 0) {
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            }

        } while (option != 0);
    }

    // =============================
    // MATERIAL MENU
    // =============================

    private void materialMenu() {

        int option;

        do {

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("--- GESTIÓN DE MATERIALES ---");
            System.out.println("1 - Registrar material");
            System.out.println("2 - Listar materiales");
            System.out.println("3 - Listar por estado");
            System.out.println("4 - Buscar por título");
            System.out.println("5 - Libros por autor");
            System.out.println("6 - Material más antiguo");
            System.out.println("0 - Volver");

            option = InputUtil.readInt(scanner, "Seleccione una opción: ");

            switch (option) {

                case 1 -> registerMaterial();
                case 2 -> viewAllMaterials();
                case 3 -> viewMaterialsByAvailable();
                case 4 -> searchMaterials();
                case 5 -> booksByAuthor();
                case 6 -> showOldestMaterial();
            }

            if (option < 7 && option != 0) {
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            }

        } while (option != 0);
    }

    // =============================
    // LOAN MENU
    // =============================

    private void loanMenu() {

        int option;

        do {

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("--- GESTIÓN DE PRÉSTAMOS ---");
            System.out.println("1 - Prestar material");
            System.out.println("2 - Devolver material");
            System.out.println("3 - Listar préstamos");
            System.out.println("4 - Préstamos por usuario");
            System.out.println("0 - Volver");

            option = InputUtil.readInt(scanner, "Seleccione una opción: ");

            switch (option) {

                case 1 -> loanMaterial();
                case 2 -> returnMaterial();
                case 3 -> viewLoans();
                case 4 -> viewLoansByUser();
            }

            if (option < 5 && option != 0) {
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            }

        } while (option != 0);
    }

    // =============================
    // USER ACTIONS
    // =============================

    private void registerUser() {

        System.out.print("Nombre del usuario: ");
        String nombre = scanner.nextLine();

        library.registerUser(nombre);

        System.out.println("Usuario registrado correctamente");
    }

    private void viewUsers() {

        List<User> users = library.getAllUsers();

        System.out.println("\n--- Usuarios ---");

        if (users.isEmpty()) {
            System.out.println("No se encontraron usuarios.");
            return;
        }

        for (User user : users) {
            System.out.println(user);
        }
    }

    private void searchUserById() {

        int userId = InputUtil.readInt(scanner, "Ingrese el ID: ");

        User user = library.findUserById(userId);

        System.out.println("\nUsuario encontrado:");
        System.out.println(user);
    }

    private void searchUsersByName() {

        System.out.print("Ingrese nombre o parte del nombre: ");
        String nombre = scanner.nextLine();

        List<User> resultados = library.searchUsersByName(nombre);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron usuarios.");
            return;
        }

        for (User u : resultados) {
            System.out.println(u);
        }
    }

    private void viewUserWithMostLoans() {

        User user = library.getUserWithMostLoans();

        if (user == null) {
            System.out.println("No se encontraron usuarios.");
            return;
        }

        System.out.println("\nUsuario con más préstamos:");
        System.out.println(user);
    }

    // =============================
    // MATERIAL ACTIONS
    // =============================

    private void registerMaterial() {

        System.out.println("Tipo de material:");
        System.out.println("1 - Libro");
        System.out.println("2 - Revista");

        int type = InputUtil.readInt(scanner, "Seleccione un tipo de material: ");

        if (type < 1 || type > 2) {
            System.out.println("Opción inválida");
            return;
        }

        System.out.print("Título: ");
        String title = scanner.nextLine();

        int year = InputUtil.readInt(scanner, "Año de publicación: ");

        switch (type) {

            case 1 -> {

                System.out.print("Autor: ");
                String autor = scanner.nextLine();

                library.registerMaterial(new Book(title, autor, year));
            }

            case 2 -> {

                int edition = InputUtil.readInt(scanner, "Número de edición: ");

                library.registerMaterial(new Magazine(title, year, edition));
            }

            default -> {
                System.out.println("Tipo inválido");
                return;
            }
        }

        System.out.println("Material registrado correctamente");
    }

    private void viewAllMaterials() {

        List<Material> materials = library.getAllMaterials();

        if (materials.isEmpty()) {
            System.out.println("No se encontraron materiales.");
            return;
        }

        for (Material material : materials) {
            System.out.println(material);
        }
    }

    private void viewMaterialsByAvailable() {

        System.out.println("Estado:");
        System.out.println("1 - Disponible");
        System.out.println("2 - Prestado");

        int type = InputUtil.readInt(scanner, "Seleccione un estado: ");

        if (type < 1 || type > 2) {
            System.out.println("Opción inválida");
            return;
        }

        MaterialStatus status = (type == 1)
                ? MaterialStatus.AVAILABLE
                : MaterialStatus.LOANED;

        List<Material> materials = library.getMaterialsByStatus(status);

        for (Material material : materials) {
            System.out.println(material);
        }
    }

    private void searchMaterials() {

        System.out.print("Ingrese título o parte del título: ");
        String titulo = scanner.nextLine();

        List<Material> resultados = library.searchMaterialsByTitle(titulo);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron materiales.");
            return;
        }

        for (Material m : resultados) {
            System.out.println(m);
        }
    }

    private void booksByAuthor() {

        System.out.print("Ingrese autor: ");
        String author = scanner.nextLine();

        List<Book> books = library.getBooksByAuthor(author);

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void showOldestMaterial() {

        Material material = library.getOldestMaterial();

        if (material == null) {
            System.out.println("No hay materiales.");
            return;
        }

        System.out.println("Material más antiguo:");
        System.out.println(material);
    }

    // =============================
    // LOAN ACTIONS
    // =============================

    private void loanMaterial()
            throws InvalidLoanException, UserNotFoundException, MaterialNotFoundException {

        int userId = InputUtil.readInt(scanner, "ID del usuario: ");

        System.out.print("Título del material: ");
        String title = scanner.nextLine();

        int days = InputUtil.readInt(scanner, "Cantidad de días: ");

        library.loanMaterial(userId, title, days);

        System.out.println("Préstamo realizado correctamente");
    }

    private void returnMaterial()
            throws InvalidLoanException, MaterialNotFoundException {

        System.out.print("Título del material: ");
        String title = scanner.nextLine();

        library.returnMaterial(title);

        System.out.println("Material devuelto correctamente");
    }

    private void viewLoans() {

        List<Loan> loans = library.getAllLoans();

        if (loans.isEmpty()) {
            System.out.println("No se encontraron préstamos.");
            return;
        }

        for (Loan loan : loans) {
            System.out.println(loan);
        }
    }

    private void viewLoansByUser() {

        int userId = InputUtil.readInt(scanner, "Ingrese ID del usuario: ");

        List<Loan> loans = library.getLoansByUser(userId);

        if (loans.isEmpty()) {
            System.out.println("El usuario no tiene préstamos.");
            return;
        }

        for (Loan loan : loans) {
            System.out.println(loan);
        }
    }

    private void showSystemInfo() {

        SystemInfo info = library.getSystemInfo();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("--- INFORMACIÓN DEL SISTEMA ---");
        System.out.println("Usuarios: " + info.getTotalUsers());
        System.out.println("Materiales: " + info.getTotalMaterials());
        System.out.println("Préstamos: " + info.getTotalLoans());
        System.out.println("Materiales disponibles: " + info.getAvailableMaterials());

        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}