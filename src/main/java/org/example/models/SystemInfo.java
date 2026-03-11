package org.example.models;

public class SystemInfo {

    private final int totalUsers;
    private final int totalMaterials;
    private final int totalLoans;
    private final int availableMaterials;

    public SystemInfo(int totalUsers, int totalMaterials, int totalLoans, int availableMaterials) {
        this.totalUsers = totalUsers;
        this.totalMaterials = totalMaterials;
        this.totalLoans = totalLoans;
        this.availableMaterials = availableMaterials;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public int getTotalMaterials() {
        return totalMaterials;
    }

    public int getTotalLoans() {
        return totalLoans;
    }

    public int getAvailableMaterials() {
        return availableMaterials;
    }
}