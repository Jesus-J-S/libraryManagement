package es.ing.tomillo.library.util;

import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;

import java.util.Arrays;
import java.util.List;

public class SampleData {
    
    public static final List<Book> SAMPLE_BOOKS = Arrays.asList(
            new Book("Los pilares de la tierra", "Ken Follett", "978-84-376-0495-0"),
            new Book("Rayuela", "Julio Cortázar", "978-84-376-0495-1"),
            new Book("El hobbit", "J.R.R. Tolkien", "978-84-376-0495-2"),
            new Book("Matar a un ruiseñor", "Harper Lee", "978-84-376-0495-3"),
            new Book("Anna Karenina", "León Tolstói", "978-84-376-0495-4"),
            new Book("La vuelta al mundo en 80 días", "Julio Verne", "978-84-376-0495-5"),
            new Book("Drácula", "Bram Stoker", "978-84-376-0495-6"),
            new Book("La carretera", "Cormac McCarthy", "978-84-376-0495-7"),
            new Book("El guardián entre el centeno", "J.D. Salinger", "978-84-376-0495-8"),
            new Book("La ciudad y los perros", "Mario Vargas Llosa", "978-84-376-0495-9")
    );

    public static final List<User> SAMPLE_USERS = Arrays.asList(
        new User("Juan Pérez", 1),
        new User("María García", 2),
        new User("Carlos López", 3),
        new User("Ana Martínez", 4),
        new User("Pedro Sánchez", 5),
        new User("Laura Fernández", 6),
        new User("David González", 7),
        new User("Sofía Rodríguez", 8),
        new User("Javier Díaz", 9),
        new User("Elena Ruiz", 10)
    );

} 