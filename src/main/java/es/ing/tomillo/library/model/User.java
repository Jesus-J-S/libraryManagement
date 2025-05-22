package es.ing.tomillo.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Configuración de user
public class User {
    private String name;
    private int id;
    private final List<Book> borrowedBooks;
    private static final int MAX_BORROWED_BOOKS = 5;
    public List<Book> getReservedBooks() {
        return reservedBooks;
    }

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
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

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getBookCount() {
        return borrowedBooks.size();
    }

    // Metodo para prestar libros
    public void borrowBook(Book book) {
        if (borrowedBooks.size() < MAX_BORROWED_BOOKS) {
            borrowedBooks.add(book);
            reservedBooks.add(book);
            book.setAvailable(false);
            book.setBorrowedBy(this);
            System.out.println(name + " ha tomado prestado el libro '" + book.getTitulo() + "'");
        } else {
            System.out.println("El usuario " + name + " ya ha alcanzado el límite de préstamos.");
        }
    }

    // Metodo para devolver libros
    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
            book.setBorrowedBy(null);
            System.out.println(name + " ha devuelto el libro '" + book.getTitulo() + "'");
        } else {
            System.out.println(name + " no tiene prestado el libro '" + book.getTitulo() + "'");
        }
    }

    // Metodo para reservar libros
    private final List<Book> reservedBooks = new ArrayList<>();

    public void reserveBook(Book book) {
        if (reservedBooks.size() < MAX_BORROWED_BOOKS) {
            if (!book.isAvailable()) {
                reservedBooks.add(book);
                System.out.println(name + " ha reservado el libro '" + book.getTitulo() + "'");
                System.out.println("Lista actual de reservas para " + name + ": " + reservedBooks); // Depuración
            } else {
                System.out.println("El libro '" + book.getTitulo() + "' está disponible, no necesitas reservarlo.");
            }
        } else {
            System.out.println("El usuario " + name + " ya ha alcanzado el límite de reservas.");
        }
    }

    // To string para mostrar información de usuario
    @Override
    public String toString() {
        return "Usuario{" +
                "Nombre='" + name + '\'' +
                ", ID=" + id +
                ", Libros reservados=" + (reservedBooks.isEmpty() ? "Ninguno" : reservedBooks.size() + ": " + reservedBooks) +
                '}';
    }

    // Metodo equals para comparar usuarios por id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


