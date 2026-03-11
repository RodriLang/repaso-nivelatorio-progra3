package org.example.app;

import org.example.services.LibraryService;
import org.example.utils.InitializationData;

public class Main {

    public static void main(String[] args) {

        LibraryService library = new LibraryService();

        InitializationData.initData(library);

        MenuController menu = new MenuController(library);

        menu.start();
    }
}