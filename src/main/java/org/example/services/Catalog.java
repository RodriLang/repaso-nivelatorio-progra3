package org.example.services;

import java.util.ArrayList;
import java.util.List;

public class Catalog<T> {

    private final List<T> items;

    public Catalog() {
        items = new ArrayList<>();
    }

    public void add(T item){
        items.add(item);
    }

    public List<T> getAll(){
        return items;
    }
}