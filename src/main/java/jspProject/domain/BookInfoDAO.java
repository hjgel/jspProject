package jspProject.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "SELECT bi.book_info_id, bi.pages, bi.diary_id, b.title, b.author, b.title_url " +
                     "FROM book_info bi " +
                     "JOIN book b ON bi.book_id = b.book_id " +
                     "WHERE bi.book_info_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, book_info_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                BookInfo bookInfo = new BookInfo();
                bookInfo.setBook_info_id(rs.getInt("book_info_id"));
                bookInfo.setPages(rs.getInt("pages"));
                bookInfo.setDiary_id(rs.getInt("diary_id"));
                bookInfo.setTitle(rs.getString("title"));
                bookInfo.setAuthor(rs.getString("author"));
                bookInfo.setTitleUrl(rs.getString("title_url"));
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
}

