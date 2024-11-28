package jspProject.domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {
    private Connection conn;

    public DiaryDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/dokjung?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
            String dbUser = "root";
            String dbPassword = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 특정 책(book_info_id)에 연결된 독후감 가져오기
    public List<Diary> getDiariesByBookInfoId(int book_info_id) {
        List<Diary> diaries = new ArrayList<>();
        String sql = "SELECT diary_id, content, created_at FROM diary WHERE book_info_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, book_info_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Diary diary = new Diary();
                    diary.setDiary_id(rs.getInt("diary_id"));
                    diary.setContent(rs.getString("content"));
                    diary.setCreatedAt(rs.getTimestamp("created_at"));
                    diaries.add(diary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diaries;
    }

    // 새로운 독후감 추가
    public boolean addDiary(int bookInfoId, String content) {
        String sql = "INSERT INTO diary (book_info_id, content, created_at) VALUES (?, ?, NOW())";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookInfoId);
            pstmt.setString(2, content);
            int rows = pstmt.executeUpdate();
            return rows > 0; // 1 이상이면 성공
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}