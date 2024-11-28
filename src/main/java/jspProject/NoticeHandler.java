package jspProject;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspProject.domain.Notice;
import jspProject.domain.NoticeDAO;

public class NoticeHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		NoticeDAO noticeDAO = new NoticeDAO();
        List<Notice> noticeList = noticeDAO.getAllNotices(); // 모든 공지사항 조회
        request.setAttribute("noticeList", noticeList);
		return "notice";
	}
	

}
