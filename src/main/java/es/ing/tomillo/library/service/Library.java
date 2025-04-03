package es.ing.tomillo.library.service;

import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;

import java.util.Scanner;

public class Library {

    private final Book[] books;
    private final User[] users;
    private int bookCount;
    private int userCount;

    public Library() {
        this.books = new Book[100]; // Maximum 100 books
        this.users = new User[50]; // Maximum 50 users
        this.bookCount = 0;
        this.userCount = 0;
    }

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
        } else {
            //TODO: ¿Por qué se ha producido este error? Añade una descripción al mensaje de error que explique por qué se ha producido
            System.out.println("ERROR: ??");
        }
    }

    public void addUser(User user) {
        if (userCount < users.length) {
            users[userCount] = user;
            userCount++;
        } else {
            //TODO: ¿Por qué se ha producido este error? Añade una descripción al mensaje de error que explique por qué se ha producido
            System.out.println("ERROR ??");
        }
    }

    public void borrowBook(User user, Book book) {
        user.borrowBook(book);
    }

    public void returnBook(User user, Book book) {
        user.returnBook(book);
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        for (Book book : books) {
            if (book != null && book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String title;
        String isbn;
        Book book = null;
        User user = null;
        int id=0;

        while (!exit) {
            System.out.println("Menu Options:");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Search Book by Author");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    isbn = scanner.nextLine();
                    book = new Book(title, author, isbn);
                    library.addBook(book);
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    id = scanner.nextInt();
                    user = new User(name, id);
                    library.addUser(user);
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    user = library.users[id];
                    book = library.searchBookByTitle(title);
                    if (user != null && book != null) {
                        library.borrowBook(user, book);
                    } else {
                        System.out.println("User or book not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    user = library.users[id];
                    book = library.searchBookByTitle(title);
                    if (user != null && book != null) {
                        library.returnBook(user, book);
                    } else {
                        System.out.println("User or book not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    book = library.searchBookByTitle(title);
                    if (book != null) {
                        System.out.println(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter book author: ");
                    author = scanner.nextLine();
                    book = library.searchBookByAuthor(author);
                    if (book != null) {
                        System.out.println(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}

