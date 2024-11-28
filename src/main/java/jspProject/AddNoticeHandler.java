package jspProject;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspProject.domain.NoticeDAO;
import jspProject.user.UserDAO;

public class AddNoticeHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException {
		// 세션에서 user_id 가져오기
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            request.setAttribute("error", "로그인이 필요합니다.");
            return "login";
        }
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.getUserIdByUsername(username);
        
        if (userId <= 0) {
            request.setAttribute("error", "사용자 정보를 확인할 수 없습니다.");
            return "login";
        }

        // 폼 데이터 가져오기
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (title == null || content == null || title.isEmpty() || content.isEmpty()) {
            request.setAttribute("error", "제목과 내용을 모두 입력해야 합니다.");
            return "notice_write";
        }

        // 데이터베이스에 저장
        NoticeDAO noticeDAO = new NoticeDAO();
        boolean success = noticeDAO.addNotice(title, content, userId);

        if (success) {
            request.setAttribute("message", "공지사항이 성공적으로 등록되었습니다.");
            return "notice_write"; // 공지사항 목록 페이지로 리다이렉트
        } else {
            request.setAttribute("error", "공지사항 등록에 실패했습니다.");
            return "notice_write";
        }
	}

}
