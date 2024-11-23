package jspProject;

import jspProject.domain.BookDAO;
import jspProject.domain.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookDetailHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String bookIdParam = request.getParameter("bookId");
        if (bookIdParam == null || bookIdParam.isEmpty()) {
            request.setAttribute("error", "유효하지 않은 요청입니다.");
            return "/WEB-INF/views/error.jsp";
        }

        int bookId = Integer.parseInt(bookIdParam); // bookId를 정수로 변환
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBookById(bookId);

  

        request.setAttribute("book", book);
        return "book_detail"; // 상세보기 JSP로 포워딩
    }
}