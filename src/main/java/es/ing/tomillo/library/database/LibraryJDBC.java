//import es.ing.tomillo.library.model.Book;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class LibraryJDBC {
//    private Connection connection;
//
//    public LibraryJDBC() {
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "user", "password");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void addBook(Book book) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO books (title, author, isbn, available) VALUES (?, ?, ?, ?)");
//            ps.setString(1, book.getTitle());
//            ps.setString(2, book.getAuthor());
//            ps.setString(3, book.getIsbn());
//            ps.setBoolean(4, book.isAvailable());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Book searchBookByTitle(String title) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE title = ?");
//            ps.setString(1, title);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Book(rs.getString("title"), rs.getString("author"), rs.getString("isbn"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
