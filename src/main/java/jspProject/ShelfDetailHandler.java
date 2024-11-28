package jspProject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspProject.domain.BookDTO;
import jspProject.domain.BookInfo;
import jspProject.domain.BookInfoDAO;
import jspProject.domain.Diary;
import jspProject.domain.DiaryDAO;

public class ShelfDetailHandler implements CommandHandler {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        int book_info_id = Integer.parseInt(request.getParameter("book_info_id"));


        // 독후감 목록 가져오기
        DiaryDAO diaryDAO = new DiaryDAO();
        List<Diary> diaryList = diaryDAO.getDiariesByBookInfoId(book_info_id);
        BookInfoDAO bookInfoDAO = new BookInfoDAO();
        BookInfo book = bookInfoDAO.getBookInfo(book_info_id);
        
        
        System.out.println(book);
        // 데이터 전달
        request.setAttribute("diaryList", diaryList);
        request.setAttribute("book_info_id", book_info_id);
        request.setAttribute("book", book);

        return "shelf_detail";
    }
}