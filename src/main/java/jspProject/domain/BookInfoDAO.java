package jspProject.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInfoDAO {
	private Connection conn;
	
	public BookInfoDAO() {
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
	
	// 1. book_info 추가
	public int addBookInfo(int book_id, int pages) {
        String sql = "INSERT INTO book_info (book_id, pages) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, book_id);
            pstmt.setInt(2, pages);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                return rows; // 영향을 받은 행 수 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 실패 시 -1 반환
    }

    // 2. 특정 book_info 조회
    public BookInfo getBookInfo(int book_info_id) {
        String sql = "SELECT bi.book_info_id, bi.pages, b.title, b.author, b.title_url " +
                     "FROM book_info bi " +
                     "JOIN book b ON bi.book_id = b.bookId " +
                     "WHERE bi.book_info_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, book_info_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                BookInfo bookInfo = new BookInfo();
                bookInfo.setBook_info_id(rs.getInt("book_info_id"));
                bookInfo.setPages(rs.getInt("pages"));
                bookInfo.setTitle(rs.getString("title"));
                bookInfo.setAuthor(rs.getString("author"));
                bookInfo.setTitle_url(rs.getString("title_url"));
                return bookInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    // 책 정보를 저장하는 메서드
    public boolean saveBookInfo(int userId, int bookId, int pages) {
        String sql = "INSERT INTO Book_Info (user_id, book_id, pages) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.setInt(3, pages);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // 회원이 이미 서재에 등록한 책이면 등록 불가 반환하는 메서드
    public boolean isBookAlreadyRegistered(int userId, int bookId) {
        String sql = "SELECT COUNT(*) FROM Book_Info WHERE user_id = ? AND book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // 해당 데이터가 존재하면 true 반환
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // 예외 발생 시 false 반환
    }
    
 // 특정 사용자에 대한 책 목록 가져오기
    public List<BookDTO> getBooksByUserId(int userId) {
        List<BookDTO> bookList = new ArrayList<>();
        String sql = "SELECT bi.book_info_id, b.title_url, b.title, b.author, bi.pages "
                   + "FROM Book b "
                   + "JOIN Book_Info bi ON b.bookId = bi.book_id "
                   + "WHERE bi.user_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BookDTO bookDTO = new BookDTO();
                    bookDTO.setBook_info_id(rs.getInt("book_info_id"));
                    bookDTO.setTitleUrl(rs.getString("title_url"));
                    bookDTO.setTitle(rs.getString("title"));
                    bookDTO.setAuthor(rs.getString("author"));
                    bookDTO.setPages(rs.getInt("pages"));
                    bookList.add(bookDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }
    
    public int countBooksByUserId(int userId) {
        String sql = "SELECT COUNT(*) AS bookCount FROM book_info WHERE user_id = ?";
        int count = 0;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("bookCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    
    public int countDiariesByUserId(int userId) {
        String sql = "SELECT COUNT(d.diary_id) AS diaryCount " +
                     "FROM book_info b " +
                     "LEFT JOIN diary d ON b.book_info_id = d.book_info_id " +
                     "WHERE b.user_id = ?";
        int count = 0;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("diaryCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    
}

