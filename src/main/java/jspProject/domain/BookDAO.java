package jspProject.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BookDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/dokjung?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
            String dbID = "root";
            String dbPassword = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            if (conn != null) {
                System.out.println("데이터베이스 연결 성공!");
            } else {
                System.out.println("데이터베이스 연결 실패!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> searchBooks(String query) {
        List<Book> books = new ArrayList<>();
        String sql;

        try {
            if (query == null || query.trim().isEmpty()) {
                // 검색어가 없을 경우: 모든 책 출력
                sql = "SELECT bookId, title, author, publisher, publish_predate, subject, book_introduction, title_url FROM book";
                pstmt = conn.prepareStatement(sql);
            } else {
                // 검색어가 있을 경우: 조건에 맞는 책 출력
                sql = "SELECT bookId, title, author, publisher, publish_predate, subject, book_introduction, title_url FROM book WHERE title LIKE ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "%" + query + "%");
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishDate(rs.getString("publish_predate"));
                book.setSubject(rs.getString("subject"));
                book.setIntroduction(rs.getString("book_introduction"));
                book.setTitleUrl(rs.getString("title_url"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public Book getBookById(int bookId) {
        String sql = "SELECT bookId, title, author, publisher, publish_predate, subject, book_introduction, title_url FROM book WHERE bookId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setBookId(rs.getInt("bookId"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setPublishDate(rs.getString("publish_predate"));
                    book.setSubject(rs.getString("subject"));
                    book.setIntroduction(rs.getString("book_introduction"));
                    book.setTitleUrl(rs.getString("title_url"));
                    return book;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}