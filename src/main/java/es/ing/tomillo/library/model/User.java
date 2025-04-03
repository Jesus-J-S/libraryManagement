package es.ing.tomillo.library.model;

public class User {
    private String name;
    private int id;
    private Book[] borrowedBooks;
    private int bookCount;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new Book[5]; // Maximum 5 borrowed books
        this.bookCount = 0;
    }

    // Getters and Setters

    public void borrowBook(Book book) {
        if (bookCount < borrowedBooks.length) {
            borrowedBooks[bookCount] = book;
            bookCount++;
            book.setAvailable(false);
        } else {
            //TODO: ¿Por qué se ha producido este error? Añade una descripción al mensaje de error que explique por qué se ha producido
            System.out.println("?? ");
        }
    }

    public void returnBook(Book book) {
        for (int i = 0; i < bookCount; i++) {
            if (borrowedBooks[i].equals(book)) {
                borrowedBooks[i] = null;
                bookCount--;
                book.setAvailable(true);
                break;
            }
        }
    }

    public void reserveBook(Book book) {
        if (!book.isAvailable()) {
            //TODO: ¿Qué sucede en este caso? Añade un mensaje que lo explique
            System.out.println("?? " + book.getTitle());
        } else {
            //TODO: ¿Qué sucede en este caso? Añade un mensaje que lo explique
            System.out.println("?? ");
        }
    }
}


