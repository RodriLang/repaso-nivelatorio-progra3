package org.example.app;

import org.example.services.LibraryService;
import org.example.utils.InitializationData;

public class Main {

    public static void main(String[] args) {

        InitializationData.initData();

        MenuController menu = new MenuController();

        menu.start();
    }
}