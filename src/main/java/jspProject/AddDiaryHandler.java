package jspProject;

import jspProject.domain.DiaryDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDiaryHandler implements CommandHandler {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        // 요청 데이터 가져오기
        String content = request.getParameter("content");
        String bookInfoIdParam = request.getParameter("book_info_id");

        if (content == null || bookInfoIdParam == null || content.isEmpty()) {
            request.setAttribute("error", "모든 필드를 입력해야 합니다.");
            return "shelf_detail"; // 에러 메시지를 shelf_detail.jsp로 전달
        }

        int bookInfoId = Integer.parseInt(bookInfoIdParam);

        // DAO 호출
        DiaryDAO diaryDAO = new DiaryDAO();
        boolean success = diaryDAO.addDiary(bookInfoId, content);
        
        

        if (success) {
            request.setAttribute("message", "독후감이 성공적으로 저장되었습니다.");
            request.setAttribute("book_info_id", bookInfoIdParam);
        } else {
            request.setAttribute("error", "독후감 저장에 실패했습니다.");
            request.setAttribute("book_info_id", bookInfoIdParam);
        }

        return "shelf_detail"; // 성공/실패 메시지를 shelf_detail.jsp로 전달
    }
}