package org.example.models;

import org.example.enums.MaterialStatus;
import org.example.exceptions.InvalidLoanException;
import org.example.interfaces.Loanable;

import java.util.Objects;

public class Book extends Material implements Loanable {

    private String author;

    public Book(String title, String author, int year) {
        super(title, year);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void loan() throws InvalidLoanException {
        if (status.equals(MaterialStatus.LOANED)) {
            throw new InvalidLoanException("El libro ya está prestado.");
        }
        status = MaterialStatus.LOANED;
    }

    @Override
    public void giveBack() {
        if (status.equals(MaterialStatus.AVAILABLE)) {
            throw new InvalidLoanException("El libro no se encuentra prestado.");
        }
        status = MaterialStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "Tipo: Libro" +
                " | Título: " + title +
                " | Autor: " + author +
                " | Año: " + year +
                " | Estado: " + status.getLabel();
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        if (!super.equals(o)) return false;

        return author.equalsIgnoreCase(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author.toLowerCase());
    }
}
