package es.ing.tomillo.library.database;

import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    public static void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre) VALUES (?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("nombre"), rs.getInt("id"));
            }
            return null;
        }
    }

    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(rs.getString("nombre"), rs.getInt("id")));
            }
        }
        return users;
    }

    public static void updateUser(User user) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();
        }
    }

    public static void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }

    public static void borrowBook(int userId, int bookId) throws SQLException {
        String sql = "INSERT INTO prestamos (usuario_id, libro_id) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
            
            // Actualizar disponibilidad del libro
            BookDAO.updateBookAvailability(bookId, false);
        }
    }

    public static void returnBook(int userId, int bookId) throws SQLException {
        String sql = "DELETE FROM prestamos WHERE usuario_id = ? AND libro_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
            
            // Actualizar disponibilidad del libro
            BookDAO.updateBookAvailability(bookId, true);
        }
    }

    public static List<Book> getBorrowedBooks(int userId) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = """
            SELECT l.* FROM libros l
            JOIN prestamos p ON l.id = p.libro_id
            WHERE p.usuario_id = ?
        """;
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("isbn")
                ));
            }
        }
        return books;
    }
} 