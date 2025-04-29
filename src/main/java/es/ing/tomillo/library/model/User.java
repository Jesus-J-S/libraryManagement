package es.ing.tomillo.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    // - nombre (String)
    // - id (int)
    // - librosPrestados (List de Libro)
    private String name;
    private int id;
    private final List<Book> borrowedBooks;
    private static final int MAX_BORROWED_BOOKS = 5;

    // Constructor con un maximo de 5 libros prestados
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

    // TODO: Implementar método prestarLibro según el ejercicio 2
    // Debe añadir un libro a la lista de libros prestados
    public void borrowBook(Book book) {

    }

    // TODO: Implementar método devolverLibro según el ejercicio 2
    // Debe eliminar un libro a lista  de libros prestados
    public void returnBook(Book book) {

    }

    // TODO: Implementar método reservarLibro según el ejercicio 2
    // Debe permitir reservar libros que no están disponibles
    public void reserveBook(Book book) {

    }

    // TODO: Implementar método toString para mostrar la información del usuario
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", borrowedBooks=" + borrowedBooks.size() +
                '}';
    }

    // TODO: Implementar método equals para comparar usuarios por ID
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


