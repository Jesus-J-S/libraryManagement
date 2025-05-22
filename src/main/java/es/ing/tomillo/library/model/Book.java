package es.ing.tomillo.library.model;

import java.util.Objects;

public class Book {
private String titulo;
private String autor;
private String isbn;
private boolean isAvailable;
private User borrowedBy;
public User getBorrowedBy() {
        return borrowedBy;
    }

    // Constructor
    public Book(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.isAvailable = true;
        this.borrowedBy = null;
    }
    // getters y setters
public String getTitulo() {
        return titulo;
}
public String getAutor() {
        return autor;
}
public void setAutor(String autor) {
        this.autor = autor;
}
public String getIsbn() {
        return isbn;
}
public void setIsbn(String isbn) {
        this.isbn = isbn;
}
public boolean isAvailable() {
        return isAvailable;
}
public void borrowBook(User user) {
        if (isAvailable) {
            this.isAvailable = false;
            this.borrowedBy = user;
            System.out.println(user.getName() + " ha tomado prestado el libro " + titulo);
        } else {
            if (this.borrowedBy != null) {
                System.out.println("Este libro ya ha sido prestado por " + borrowedBy.getName());
            }
            }
}
public void setBorrowedBy(User user) {
    this.borrowedBy = user;
}
public String getTitle() {
        return titulo;
}
public String getAuthor() {
        return autor;
}
public String getISBN() {
        return isbn;
}
public void setAvailable(boolean available) {
        this.isAvailable = available;
}
    // Metodo to String
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + ", Disponible: " + (isAvailable ? "Sí" : "No");
}
    // Metodo equals para comparar libros por ISBN
public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
    return Objects.equals(isbn, book.isbn) && Objects.equals(titulo, book.titulo);}
}

