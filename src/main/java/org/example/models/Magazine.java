package org.example.models;

import java.util.Objects;

public class Magazine extends Material {

    private int edition;

    public Magazine(String title, int year, int edition) {
        super(title, year);
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Tipo: Revista" +
                " | Título: " + title +
                " | Año: " + year +
                " | Estado: " + getStatus().getLabel();
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Magazine magazine)) return false;
        if (!super.equals(o)) return false;
        return edition == magazine.edition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), edition);
    }
}