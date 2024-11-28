package jspProject;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspProject.domain.Notice;
import jspProject.domain.NoticeDAO;

public class NoticeDetailHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException {
		String noticeIdParam = request.getParameter("notice_id");
        if (noticeIdParam == null || noticeIdParam.isEmpty()) {
            request.setAttribute("error", "유효하지 않은 요청입니다.");
            return "/WEB-INF/views/error.jsp";
        }

        int noticeId = Integer.parseInt(noticeIdParam);

        NoticeDAO noticeDAO = new NoticeDAO();
        Notice notice = noticeDAO.getNoticeById(noticeId);

        if (notice == null) {
            request.setAttribute("error", "해당 공지사항을 찾을 수 없습니다.");
            return "error";
        }

        request.setAttribute("notice", notice);
        return "notice_detail";
	}
}