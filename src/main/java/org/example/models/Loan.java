package org.example.models;

import org.example.enums.MaterialStatus;
import org.example.exceptions.InvalidLoanException;
import org.example.interfaces.Loanable;

public class Loan {

    private User user;
    private Material material;
    private int days;

    public Loan(User user, Material material, int days) {

        if (days <= 0) {
            throw new InvalidLoanException("La cantidad de días debe ser mayor a 0");
        }

        if (!(material instanceof Loanable)) {
            throw new InvalidLoanException("El material no es prestable");
        }

        if (material.getStatus().equals(MaterialStatus.LOANED)) {
            throw new InvalidLoanException("El material no está disponible para préstamo");
        }

        this.user = user;
        this.material = material;
        this.days = days;
    }

    public Material getMaterial() {
        return material;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "\nPéstamo: \n" +
                " Usuario: " + user.getName() + " | ID: " + user.getId() +
                "\n Material: " + material.getTitle() + " | " +
                "Dias: " + days;
    }
}
