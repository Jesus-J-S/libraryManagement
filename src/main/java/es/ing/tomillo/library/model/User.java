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
    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for borrowedBooks
    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }

    // Setter for borrowedBooks
    public void setBorrowedBooks(Book[] borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    // Getter for bookCount
    public int getBookCount() {
        return bookCount;
    }

    // Setter for bookCount
    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
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


