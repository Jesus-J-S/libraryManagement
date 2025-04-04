package es.ing.tomillo.library.model;

public class Book {
    // TODO: Implementar los atributos según el ejercicio 1
    // - titulo (String)
    // - autor (String)
    // - isbn (String)
    // - disponible (boolean)

    // TODO: Implementar constructor según el ejercicio 1
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    // TODO: Implementar getters y setters según el ejercicio 1

    // TODO: Implementar método toString según el ejercicio 1
    @Override
    public String toString() {
        return "";
    }

    // TODO: Implementar método equals para comparar libros por ISBN
}

