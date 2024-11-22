package jspProject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspProject.domain.Book;
import jspProject.domain.BookDAO;

public class SearchBooksHandler implements CommandHandler{


    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
    	String query = request.getParameter("query"); // 검색어 가져오기

        BookDAO bookDAO = new BookDAO();
        List<Book> bookList = bookDAO.searchBooks(query); // 검색어가 null이면 전체 리스트 반환

        if (bookList.isEmpty()) {
            request.setAttribute("message", "출력할 책이 없습니다.");
        } else {
            request.setAttribute("bookList", bookList);
        }
       
        return "book_search"; // JSP로 포워딩
    }
}
