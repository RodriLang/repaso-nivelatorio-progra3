package org.example.models;

import org.example.exceptions.LoanLimitExceededException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private static int countId = 1;

    private final int id;
    private String name;
    private final List<Loan> loans;

    public User(String name) {
        this.id = countId++;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    public void addLoan(Loan loan) throws LoanLimitExceededException {

        if (loans.size() >= 3) {
            throw new LoanLimitExceededException("El usuario no puede tener más de 3 préstamos activos.");
        }

        loans.add(loan);
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "Nombre: " + name + "\n" +
                "Préstamos: " + loans.size() + "\n";
    }
}
