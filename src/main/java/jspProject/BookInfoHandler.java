package jspProject;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jspProject.domain.BookInfoDAO;
import jspProject.user.UserDAO;

public class BookInfoHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	// 세션에서 username 가져오기
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            request.setAttribute("error", "로그인이 필요합니다.");
            return "login"; // 로그인 페이지로 이동
        }

        // username으로 user_id 조회
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.getUserIdByUsername(username);

        if (userId <= 0) {
            request.setAttribute("error", "사용자 정보를 확인할 수 없습니다.");
            return "login";
        }

        // 폼 데이터 가져오기
        String bookIdParam = request.getParameter("book_id");
        String pagesParam = request.getParameter("pages");

        if (bookIdParam == null || pagesParam == null || bookIdParam.isEmpty() || pagesParam.isEmpty()) {
            request.setAttribute("error", "모든 필드를 입력해야 합니다.");
            return "book_detail";
        }

        try {
            int bookId = Integer.parseInt(bookIdParam);
            int pages = Integer.parseInt(pagesParam);
            
            
            // Book_Info 저장 처리
            BookInfoDAO bookInfoDAO = new BookInfoDAO();
            
            
            // Book_Info 중복 확인
            if (bookInfoDAO.isBookAlreadyRegistered(userId, bookId)) {
            	System.out.println("중복 여부 확인: " + bookInfoDAO.isBookAlreadyRegistered(userId, bookId));
                // 이미 등록된 책인 경우
                request.setAttribute("error", "이미 등록되어 있는 책입니다.");
                return "book_detail";
            }
            
           
            boolean success = bookInfoDAO.saveBookInfo(userId, bookId, pages);
            if (success) {
                request.setAttribute("message", "책이 성공적으로 등록되었습니다.");
                return "book_detail"; // 서재 페이지로 리다이렉트
            } else {
                request.setAttribute("error", "책 등록에 실패했습니다.");
                return "book_detail";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "입력 데이터 형식이 잘못되었습니다.");
            return "book_detail";
        }

    }
}