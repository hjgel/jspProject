//package jspProject;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletResponse;
//
//public class BookShelfViewHandler implements CommandHandler{
//	@Override
//	public String process(HttpServlet request, HttpServletResponse response) {
//	    // 세션에서 username 가져오기
//	    String username = (String) request.getSession().getAttribute("username");
//
//	    if (username == null) {
//	        request.setAttribute("error", "로그인이 필요합니다.");
//	        return "login"; // 로그인 페이지로 이동
//	    }
//
//	    // username으로 user_id 조회
//	    UserDAO userDAO = new UserDAO();
//	    int userId = userDAO.getUserIdByUsername(username);
//
//	    if (userId <= 0) {
//	        request.setAttribute("error", "사용자 정보를 확인할 수 없습니다.");
//	        return "login";
//	    }
//
//	    // 사용자 책 정보 가져오기
//	    BookInfoDAO bookInfoDAO = new BookInfoDAO();
//	    List<BookInfo> bookList = bookInfoDAO.getBooksByUserId(userId);
//
//	    // 조회된 데이터를 JSP로 전달
//	    request.setAttribute("bookList", bookList);
//
//	    return "bookshelf"; // JSP 파일 이름 (bookshelf.jsp)
//	}
//}
