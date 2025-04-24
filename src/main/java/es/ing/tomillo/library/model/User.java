package es.ing.tomillo.library.model;

public class User {

    // - nombre (String)
    // - id (int)
    // - librosPrestados (Array de Libro)
    private String name;
    private int id;
    private Book[] borrowedBooks;
    private int bookCount;

    // Constructor con un maximo de 5 libros prestados
    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new Book[5]; // Maximum 5 borrowed books
        this.bookCount = 0;
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

    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Book[] borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    // TODO: Implementar método prestarLibro según el ejercicio 2
    // Debe añadir un libro al array de libros prestados
    public void borrowBook(Book book) {

    }

    // TODO: Implementar método devolverLibro según el ejercicio 2
    // Debe eliminar un libro del array de libros prestados
    public void returnBook(Book book) {

    }

    // TODO: Implementar método reservarLibro según el ejercicio 2
    // Debe permitir reservar libros que no están disponibles
    public void reserveBook(Book book) {
        // reservarlo o no dependiendo de si está disponible el libro

    }


    // TODO: Implementar método toString para mostrar la información del usuario
    @Override
    public String toString() {
        return "";
    }

    // TODO: Implementar método equals para comparar usuarios por ID
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}


