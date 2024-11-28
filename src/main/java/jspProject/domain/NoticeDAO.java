package jspProject.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO {
private Connection conn;
	
	public NoticeDAO() {
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
	
	// 모든 공지사항 리스트 조회
    public List<Notice> getAllNotices() {
        List<Notice> noticeList = new ArrayList<>();
        String sql = "SELECT notice_id, title, content, created_at, user_id FROM notice ORDER BY created_at DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Notice notice = new Notice();
                notice.setNotice_id(rs.getInt("notice_id"));
                notice.setTitle(rs.getString("title"));
                notice.setContent(rs.getString("content"));
                notice.setCreated_at(rs.getTimestamp("created_at").toString());
                notice.setUserId(rs.getInt("user_id"));
                noticeList.add(notice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noticeList;
    }
    
    public Notice getNoticeById(int noticeId) {
        String sql = "SELECT notice_id, title, content, created_at FROM notice WHERE notice_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, noticeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Notice notice = new Notice();
                    notice.setNotice_id(rs.getInt("notice_id"));
                    notice.setTitle(rs.getString("title"));
                    notice.setContent(rs.getString("content"));
                    notice.setCreated_at(rs.getTimestamp("created_at").toString());
                    return notice;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addNotice(String title, String content, int user_id) {
        String sql = "INSERT INTO notice (title, content, created_at, user_id) VALUES (?, ?, NOW(), ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3, user_id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 성공 시 true 반환
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
