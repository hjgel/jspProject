package jspProject;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspProject.domain.BookDTO;
import jspProject.domain.BookInfoDAO;
import jspProject.user.UserDAO;

public class MainHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException {
		HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username"); // 세션에서 user_id 가져오기

        if (username == null) {
            request.setAttribute("error", "로그인이 필요합니다.");
            return "login"; // 로그인 페이지로 이동
        }
        int userId = new UserDAO().getUserIdByUsername(username);

        BookInfoDAO bookInfoDAO = new BookInfoDAO();
        List<BookDTO> bookList = bookInfoDAO.getBooksByUserId(userId); // 사용자별 책 정보 가져오기
        int bookCount = bookInfoDAO.countBooksByUserId(userId);
        int diaryCount = bookInfoDAO.countDiariesByUserId(userId);
        System.out.println(bookList);
        request.setAttribute("bookList", bookList); // JSP에 전달
        request.setAttribute("bookCount", bookCount);
        request.setAttribute("diaryCount", diaryCount);
        return "main"; // main.jsp로 포워딩
    }
	

}
