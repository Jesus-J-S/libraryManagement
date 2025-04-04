package es.ing.tomillo.library.service;

import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;
import es.ing.tomillo.library.database.DatabaseManager;
import es.ing.tomillo.library.database.UserDAO;
import es.ing.tomillo.library.database.BookDAO;
import es.ing.tomillo.library.database.DataInitializer;
import es.ing.tomillo.library.config.LibraryConfig;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

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
        
        // Inicializar la base de datos y los datos de ejemplo si está habilitada
        if (LibraryConfig.isUseDatabase()) {
            try {
                DatabaseManager.initializeDatabase();
                System.out.println("Base de datos H2 inicializada correctamente");
                
                DataInitializer.initializeSampleData();
                System.out.println("Datos de ejemplo cargados correctamente");
            } catch (SQLException e) {
                System.err.println("Error al inicializar la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
            
            if (LibraryConfig.isUseDatabase()) {
                try {
                    BookDAO.insertBook(book);
                } catch (SQLException e) {
                    System.err.println("Error al añadir libro a la base de datos: " + e.getMessage());
                }
            }
        } else {
            System.out.println("ERROR: No se pueden añadir más libros. Límite alcanzado.");
        }
    }

    public void addUser(User user) {
        if (userCount < users.length) {
            users[userCount] = user;
            userCount++;
            
            if (LibraryConfig.isUseDatabase()) {
                try {
                    UserDAO.insertUser(user);
                } catch (SQLException e) {
                    System.err.println("Error al añadir usuario a la base de datos: " + e.getMessage());
                }
            }
        } else {
            System.out.println("ERROR: No se pueden añadir más usuarios. Límite alcanzado.");
        }
    }

    public void borrowBook(User user, Book book) {
        if (LibraryConfig.isUseDatabase()) {
            try {
                UserDAO.borrowBook(user.getId(), book.getId());
            } catch (SQLException e) {
                System.err.println("Error al prestar libro en la base de datos: " + e.getMessage());
            }
        }
        user.borrowBook(book);
    }

    public void returnBook(User user, Book book) {
        if (LibraryConfig.isUseDatabase()) {
            try {
                UserDAO.returnBook(user.getId(), book.getId());
            } catch (SQLException e) {
                System.err.println("Error al devolver libro en la base de datos: " + e.getMessage());
            }
        }
        user.returnBook(book);
    }

    public Book searchBookByTitle(String title) {
        if (LibraryConfig.isUseDatabase()) {
            try {
                List<Book> books = BookDAO.searchBooksByTitle(title);
                if (!books.isEmpty()) {
                    return books.get(0);
                }
            } catch (SQLException e) {
                System.err.println("Error al buscar libro en la base de datos: " + e.getMessage());
            }
        }
        
        for (Book book : books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        if (LibraryConfig.isUseDatabase()) {
            try {
                List<Book> books = BookDAO.searchBooksByAuthor(author);
                if (!books.isEmpty()) {
                    return books.get(0);
                }
            } catch (SQLException e) {
                System.err.println("Error al buscar libro en la base de datos: " + e.getMessage());
            }
        }
        
        for (Book book : books) {
            if (book != null && book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Configurar si se usa la base de datos
        System.out.print("¿Desea usar la base de datos? (s/n): ");
        Scanner configScanner = new Scanner(System.in);
        String useDB = configScanner.nextLine().toLowerCase();
        LibraryConfig.setUseDatabase(useDB.equals("s"));
        configScanner.close();

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String title;
        String isbn;
        Book book = null;
        User user = null;
        int id=0;

        try {
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
        } finally {
            scanner.close();
            if (LibraryConfig.isUseDatabase()) {
                try {
                    DatabaseManager.closeConnection();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}

