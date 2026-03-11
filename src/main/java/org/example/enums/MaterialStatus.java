package org.example.enums;

public enum MaterialStatus {

    AVAILABLE ("Disponible"),
    LOANED ("Prestado");

    private final String label;

    MaterialStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
