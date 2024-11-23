package jspProject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jspProject.domain.BookInfoDAO;
import jspProject.user.UserDAO;

public class BookInfoHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        // 1. 세션에서 username 가져오기
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            request.setAttribute("error", "로그인이 필요합니다.");
            return "login"; // 로그인 페이지로 이동
        }

        // 2. username으로 user_id 조회
        UserDAO userDAO = new UserDAO(); // UserDAO 초기화
        int userId = userDAO.getUserIdByUsername(username); // user_id 조회
        if (userId <= 0) { // 유효하지 않은 user_id
            request.setAttribute("error", "사용자 정보를 확인할 수 없습니다.");
            return "login";
        }

        // 3. 요청 데이터 가져오기
        String bookIdParam = request.getParameter("book_id");
        String pagesParam = request.getParameter("pages");

        // 입력 데이터 유효성 검사
        if (bookIdParam == null || pagesParam == null || bookIdParam.isEmpty() || pagesParam.isEmpty()) {
            request.setAttribute("error", "모든 필드를 입력해야 합니다.");
            return "book_detail";
        }

        int bookId = Integer.parseInt(bookIdParam);
        int pages = Integer.parseInt(pagesParam);

        // 4. DAO 인스턴스 생성
        BookInfoDAO bookInfoDAO = new BookInfoDAO(); // BookInfoDAO 초기화

        try {
            // 5. book_info 추가
            int bookInfoId = bookInfoDAO.addBookInfo(bookId, pages); // diaryId는 0으로 초기화
            if (bookInfoId > 0) {
                // 6. users 테이블의 book_info_id 업데이트
                boolean isUpdated = userDAO.updateUserBookInfo(userId, bookInfoId);

                if (isUpdated) {
                    return "bookshelf"; // 서재 페이지로 리다이렉트
                } else {
                    request.setAttribute("error", "서재 등록 중 문제가 발생했습니다.");
                    return "book_detail";
                }
            } else {
                request.setAttribute("error", "책 정보를 등록할 수 없습니다.");
                return "book_detail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "서버 오류가 발생했습니다.");
            return "book_detail";
        }
    }
}