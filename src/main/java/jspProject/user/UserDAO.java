package jspProject.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}
