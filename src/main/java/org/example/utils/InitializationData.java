package org.example.utils;

import org.example.models.Book;
import org.example.models.Magazine;
import org.example.services.LibraryService;

public class InitializationData {

    private InitializationData() {
    }

    public static void initData(LibraryService library) {

        library.registerUser("Ana");
        library.registerUser("Luis");
        library.registerUser("Carlos");
        library.registerUser("María");

        library.registerMaterial(new Book("Clean Code", "Robert Martin", 2008));
        library.registerMaterial(new Book("Effective Java", "Joshua Bloch", 2018));
        library.registerMaterial(new Book("Design Patterns", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 1994));
        library.registerMaterial(new Book("Refactoring", "Martin Fowler", 1999));
        library.registerMaterial(new Book("The Pragmatic Programmer", "Andrew Hunt & David Thomas", 1999));
        library.registerMaterial(new Book("Introduction to Algorithms", "Thomas H. Cormen", 2009));
        library.registerMaterial(new Book("Head First Design Patterns", "Eric Freeman", 2004));
        library.registerMaterial(new Book("Domain-Driven Design", "Eric Evans", 2003));

        library.registerMaterial(new Magazine("National Geographic", 2023, 450));
        library.registerMaterial(new Magazine("Science Today", 2022, 120));
        library.registerMaterial(new Magazine("MIT Technology Review", 2024, 78));

        library.loanMaterial(1, "Clean Code", 7);
        library.loanMaterial(2, "Effective Java", 10);
        library.loanMaterial(1, "Domain-Driven Design", 3);
        library.loanMaterial(3, "Design Patterns", 5);
    }
}
