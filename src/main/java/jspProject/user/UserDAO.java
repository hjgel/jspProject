package jspProject.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jspProject.domain.BookInfo;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs; 
	
	public UserDAO() {
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
	
	public int login(String username, String password) {
		String SQL = "SELECT password FROM USER WHERE username = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  username);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(password)) {
					return 1; // 로그인 성공
				}
				else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디 없음.
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO USER  (username, password, name) VALUES (?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public User getUserByUsername(String username) {
	    String sql = "SELECT * FROM USER WHERE username = ?";
	    try {
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            User user = new User();
	            user.setUsername(rs.getString("username"));
	            user.setName(rs.getString("name"));
	            return user;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	// username으로 user_id 조회
    public int getUserIdByUsername(String username) {
        String sql = "SELECT user_id FROM user WHERE username = ?";

        try {
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id"); // user_id 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 조회 실패 시 -1 반환
    }
	
	// 유저의 book_info_id 업데이트
    public boolean updateUserBookInfo(int userId, int bookInfoId) {
        String sql = "UPDATE user SET book_info_id = ? WHERE user_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookInfoId);
            pstmt.setInt(2, userId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	public BookInfo getUserBookInfo(int user_id) {
		String sql = "SELECT bi.book_info_id, bi.pages, bi.diary_id, b.title, b.author, b.title_url " + 
					 "FROM user u " + "JOIN book_info bi ON u.book_info_id = bi.book_info_id " + 
				     "JOIN book b ON bi.book_id = b.book_id " + "WHERE u.user_id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				BookInfo bookInfo = new BookInfo();
				bookInfo.setBook_info_id(rs.getInt("book_info_id"));
				bookInfo.setPages(rs.getInt("pages"));
				bookInfo.setDiary_id(rs.getInt("diary_id"));
				bookInfo.setTitle(rs.getString("title"));
				bookInfo.setAuthor(rs.getString("author"));
				bookInfo.setTitleUrl(rs.getString("title_url"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
