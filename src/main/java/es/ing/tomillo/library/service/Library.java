package es.ing.tomillo.library.service;

import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;
import es.ing.tomillo.library.util.SampleData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    // Lista de usuarios
    private final List<User> users;
    // Lista de libros
    private final List<Book> books;

    public Library() {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();

        // Cargar datos de ejemplo
        loadSampleData();
    }

    private void loadSampleData() {
        // Cargar usuarios de ejemplo
        users.addAll(SampleData.SAMPLE_USERS);

        // Cargar libros de ejemplo
        books.addAll(SampleData.SAMPLE_BOOKS);

        System.out.println("Datos de ejemplo cargados:");
        System.out.println("- " + users.size() + " usuarios");
        System.out.println("- " + books.size() + " libros");
    }

    // Mostrar por pantalla todos los usuarios registrados en la biblioteca
    public void listUsers() {
        for (User user : users) {
            System.out.println("ID: " + user.getId());
            System.out.println("Nombre: " + user.getName());
            System.out.println("Número de libros reservados: " + user.getReservedBooks().size());

            if (!user.getReservedBooks().isEmpty()) {
                System.out.print("Libros reservados: ");
                for (Book book : user.getReservedBooks()) {
                    System.out.print(book.getTitulo() + ", ");
                }
                System.out.println();
            } else {
                System.out.println("Libros reservados: Ninguno");
            }
            System.out.println("___________________");
        }
    }
    // Añadir usuario
    public void addUser(User user) {
        for (User existingUser : users) {
            if (existingUser.getId() == user.getId()) {
                System.out.println("El ID ya está en uso");
                return;
            }
        }
        users.add(user);
        System.out.println("Usuario agregado correctamente");
    }

    // añadir libro
    public void addBook(Book book) {
        books.add(book);
    }

    // Metodo prestar libros
    public void borrowBook(User user, Book book) {
        if (book != null) {
            if (book.isAvailable()) {
                book.borrowBook(user);
            } else {
                System.out.println("El libro ya ha sido prestado por " + book.getBorrowedBy().getName());
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }
    // Metodo devolver libros
    public void returnBook(User user, Book book) {
        if (book != null && book.getBorrowedBy() == user) {
            user.getBorrowedBooks().remove(book);
            user.getReservedBooks().remove(book);
            book.setAvailable(true);
            book.setBorrowedBy(null);
            System.out.println(user.getName() + " ha devuelto el libro '" + book.getTitulo() + "'");
        } else {
            System.out.println("No puedes devolver un libro que no has tomado prestado.");
        }
    }

    // Metodo buscar libros por titulo
    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitulo().equalsIgnoreCase(title)) {
                System.out.println("Libro: " + book.getTitulo());
                System.out.println("autor: " + book.getAutor());
                System.out.println("ISBN: " + book.getIsbn());
                return book;
            }
        }
        return null;
    }

    // Metodo buscar libros por autor
    public Book searchBookByAuthor(String author) {
for (Book book : books) {
    if (book.getAutor().equalsIgnoreCase(author)) {
        System.out.println("Libro: " + book.getTitulo());
        System.out.println("autor: " + book.getAutor());
        System.out.println("ISBN: " + book.getIsbn());
        return book;
    }
    }
        return null;
    }

    // Metodo buscar usuario por id
    public void searchUserById(int id) {
        User user = getUserById(id);
        if (user != null) {
            System.out.println("ID: " + user.getId());
            System.out.println("Nombre: " + user.getName());
            System.out.println("Número de libros reservados: " + user.getReservedBooks().size());

            if (!user.getReservedBooks().isEmpty()) {
                System.out.print("Libros reservados: ");
                for (Book book : user.getReservedBooks()) {
                    System.out.print(book.getTitulo() + ", ");
                }
                System.out.println();
            } else {
                System.out.println("Libros reservados: Ninguno");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    // Metodo listar libros disponibles
    public void listAvailableBooks() {
        System.out.println("Listado de libros disponibles:");
for (Book book : books) {
    if (book.isAvailable()) {
        System.out.println("Libro: " + book.getTitulo());
        System.out.println("autor: " + book.getAutor());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("___________________");
    }
}
    }

    // Metodo buscar usuario por id
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // Metodo listar libros reservados
    public void listReservedBooks() {
        System.out.println("Listado de libros reservados:");

        for (Book book : books) {
            for (User user : users) {
                if (user.getReservedBooks().contains(book)) {
                    System.out.println("Libro: " + book.getTitulo());
                    System.out.println("Autor: " + book.getAutor());
                    System.out.println("ISBN: " + book.getIsbn());
                    System.out.println("Reservado por:");
                    System.out.println("ID: " + user.getId());
                    System.out.println("Nombre: " + user.getName());
                    System.out.println("Número de libros reservados: " + user.getReservedBooks().size());
                    System.out.print("Libros reservados: ");
                    for (Book reservedBook : user.getReservedBooks()) {
                        System.out.print(reservedBook.getTitulo() + ", ");
                    }
                    System.out.println("\n__________________________");
                }
            }
        }
        if (users.stream().allMatch(user -> user.getReservedBooks().isEmpty())) {
            System.out.println("No hay libros reservados.");
        }
    }

    //Punto de entrada del programa
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String title;
        String isbn;
        Book book = null;
        User user = null;
        int id = 0;

        // Menu en pantalla
        while (!exit) {
            System.out.println("__Librería Santana__");
            System.out.println("Menu:");
            System.out.println("1. Añadir libro");
            System.out.println("2. Añadir usuario");
            System.out.println("3. Prestamo de libro");
            System.out.println("4. devolver libro");
            System.out.println("5. Buscar libro por el titulo");
            System.out.println("6. Buscar libro por el autor");
            System.out.println("7. Buscar usuario por el ID");
            System.out.println("8. Lista de libros reservados");
            System.out.println("9. Lista de libros disponibles");
            System.out.println("10. Lista de usuarios");
            System.out.println("11. Salir");
            System.out.print("Elija una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            // Configuración de opciones
            switch (option) {
                case 1:
                    System.out.print("Introduce el titulo del libro: ");
                    title = scanner.nextLine();
                    System.out.print("Introduce el autor del libro: ");
                    String author = scanner.nextLine();
                    System.out.print("Introduce el ISBN del libro: ");
                    isbn = scanner.nextLine();
                    book = new Book(title, author, isbn);
                    library.addBook(book);
                    System.out.println("Libro agregado correctamente");
                    break;
                case 2:
                    System.out.print("Introduce el nombre del usuario: ");
                    String name = scanner.nextLine();
                    System.out.print("Introduce el ID del usuario: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    User newUser = new User(name, id);
                    library.addUser(newUser);
                    break;
                case 3:
                    System.out.print("Introduce el ID del usuario: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Introduce el título del libro: ");
                    title = scanner.nextLine();

                    user = library.getUserById(id);
                    book = library.searchBookByTitle(title);

                    if (user == null) {
                        System.out.println("Usuario no encontrado");
                    } else if (book == null) {
                        System.out.println("Libro no encontrado");
                    } else {
                        user.borrowBook(book);
                    }
                    break;
                case 4:
                    System.out.print("Introduce el ID del usuario: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Introduce el titulo del libro: ");
                    title = scanner.nextLine();
                    user = library.getUserById(id);
                    book = library.searchBookByTitle(title);
                    if (user != null && book != null) {
                        library.returnBook(user, book);
                    } else {
                        System.out.println("Usuario o libro no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Introduce el titulo del libro: ");
                    title = scanner.nextLine();
                    book = library.searchBookByTitle(title);
                    if (book == null) {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 6:
                    System.out.print("Introduce el autor del libro: ");
                    author = scanner.nextLine();
                    book = library.searchBookByAuthor(author);
                    if (book == null) {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 7:
                    System.out.print("Introduce el ID del usuario que deseas buscar: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    library.searchUserById(id);
                    break;
                case 8:
                    library.listReservedBooks();
                    break;
                case 9:
                    library.listAvailableBooks();
                    break;
                case 10:
                    library.listUsers();
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Error: Opcion no valida.");
            }
        }

        scanner.close();
    }
}

