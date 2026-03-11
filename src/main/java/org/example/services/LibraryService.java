package org.example.services;

import org.example.enums.MaterialStatus;
import org.example.exceptions.InvalidLoanException;
import org.example.exceptions.MaterialNotFoundException;
import org.example.exceptions.UserNotFoundException;
import org.example.interfaces.Loanable;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private final Catalog<User> users;
    private final Catalog<Material> materials;
    private final List<Loan> loans;

    public LibraryService() {
        users = new Catalog<>();
        materials = new Catalog<>();
        loans = new ArrayList<>();
    }

    // ================= USUARIOS =================

    public void registerUser(String name) {
        users.add(new User(name));
    }

    public User findUserById(int id) throws UserNotFoundException {

        for (User user : users.getAll()) {
            if (user.getId() == id) {
                return user;
            }
        }

        throw new UserNotFoundException("No se encontró un usuario con el ID " + id);
    }

    public List<User> getAllUsers() {
        return users.getAll();
    }

    public User getUserWithMostLoans() {

        User top = null;
        int max = -1;

        for (User user : users.getAll()) {

            int loanCount = user.getLoans().size();

            if (loanCount > max) {
                max = loanCount;
                top = user;
            }
        }

        return top;
    }

    public List<User> searchUsersByName(String name) {

        List<User> result = new ArrayList<>();

        for (User u : users.getAll()) {
            if (u.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(u);
            }
        }

        return result;
    }

    // ================= MATERIALES =================

    public void registerMaterial(Material material) {
        materials.add(material);
    }

    public Material findMaterialByTitle(String title) throws MaterialNotFoundException {

        for (Material material : materials.getAll()) {
            if (material.getTitle().equalsIgnoreCase(title)) {
                return material;
            }
        }

        throw new MaterialNotFoundException("No se encontró un material con el título: " + title);
    }

    public List<Material> getAllMaterials() {
        return materials.getAll();
    }

    public List<Material> getMaterialsByStatus(MaterialStatus status) {

        List<Material> filtered = new ArrayList<>();

        for (Material material : materials.getAll()) {

            if (material.getStatus().equals(status)) {
                filtered.add(material);
            }
        }

        return filtered;
    }

    public List<Material> searchMaterialsByTitle(String title) {

        List<Material> result = new ArrayList<>();

        for (Material m : materials.getAll()) {
            if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(m);
            }
        }

        return result;
    }

    public int countAvailableMaterials() {

        int count = 0;

        for (Material material : materials.getAll()) {
            if (material.getStatus().equals(MaterialStatus.AVAILABLE)) {
                count++;
            }
        }

        return count;
    }

    public Material getOldestMaterial() {

        List<Material> list = materials.getAll();

        if (list.isEmpty()) {
            return null;
        }

        Material oldest = list.getFirst();

        for (Material m : list) {
            if (m.getYear() < oldest.getYear()) {
                oldest = m;
            }
        }

        return oldest;
    }

    public List<Book> getBooksByAuthor(String author) {

        List<Book> result = new ArrayList<>();

        for (Material material : materials.getAll()) {

            if (material instanceof Book book && book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    // ================= PRÉSTAMOS =================

    public void loanMaterial(int userId, String title, int days)
            throws InvalidLoanException, UserNotFoundException, MaterialNotFoundException {

        User user = findUserById(userId);
        Material material = findMaterialByTitle(title);

        validateLoanable(material);

        Loan loan = new Loan(user, material, days);

        Loanable loanableMaterial = (Loanable) material;

        loanableMaterial.loan();

        user.addLoan(loan);

        loans.add(loan);
    }

    public void returnMaterial(String title)
            throws InvalidLoanException, MaterialNotFoundException {

        Material material = findMaterialByTitle(title);

        validateLoanable(material);

        Loanable loanableMaterial = (Loanable) material;

        loanableMaterial.giveBack();
    }

    public List<Loan> getAllLoans() {
        return loans;
    }

    public List<Loan> getLoansByUser(int userId) {
        List<Loan> result = new ArrayList<>();

        for (Loan loan : loans) {
            if (loan.getUser().getId() == userId) {
                result.add(loan);
            }
        }
        return result;
    }

    // ================= VALIDACIONES =================

    private void validateLoanable(Material material) throws InvalidLoanException {

        if (!(material instanceof Loanable)) {
            throw new InvalidLoanException("El material '" + material.getTitle() + "' no es prestable.");
        }
    }

    // ================= INFORMACIÓN DEL SISTEMA =================

    public SystemInfo getSystemInfo() {

        int totalUsers = users.getAll().size();
        int totalMaterials = materials.getAll().size();
        int totalLoans = loans.size();

        int available = this.countAvailableMaterials();

        return new SystemInfo(
                totalUsers,
                totalMaterials,
                totalLoans,
                available
        );
    }
}