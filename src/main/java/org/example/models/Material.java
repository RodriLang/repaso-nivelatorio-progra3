package org.example.models;

import org.example.enums.MaterialStatus;

import java.util.Objects;

public abstract class Material implements Comparable<Material> {

    protected String title;
    protected int year;
    protected MaterialStatus status;

    protected Material(String title, int year) {
        this.title = title;
        this.year = year;
        this.status = MaterialStatus.AVAILABLE;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public MaterialStatus getStatus() {
        return status;
    }

    @Override
    public int compareTo(Material other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return title + " (" + year + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material material)) return false;
        return Objects.equals(title, material.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}