package jspProject;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspProject.domain.BookDTO;
import jspProject.domain.BookInfoDAO;
import jspProject.user.UserDAO;

public class BookShelfHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		 String username = (String) request.getSession().getAttribute("username");
	        if (username == null) {
	            request.setAttribute("error", "로그인이 필요합니다.");
	            return "login";
	        }

	        // user_id 조회 (UserDAO 사용)
	        int userId = new UserDAO().getUserIdByUsername(username);
	        try {
		        BookInfoDAO bookInfoDAO = new BookInfoDAO();
	            List<BookDTO> bookList = bookInfoDAO.getBooksByUserId(userId);
	            // JSP로 전달
	            request.setAttribute("bookList", bookList);
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "서버 오류가 발생했습니다.");
	            return "error.jsp";
	        }

	        return "bookshelf";
		
	}
	
	

}
