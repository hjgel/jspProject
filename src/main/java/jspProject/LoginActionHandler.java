package jspProject;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspProject.user.UserDAO;

public class LoginActionHandler implements CommandHandler{

	@Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException {

        // 1. 클라이언트에서 넘어온 데이터 가져오기
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. 입력 데이터 검증
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "아이디와 비밀번호를 모두 입력해야 합니다.");
            return "/WEB-INF/views/login.jsp"; // 다시 로그인 폼으로 이동
        }

        // 3. UserDAO 호출하여 로그인 처리
        UserDAO userDAO = new UserDAO();
        int result = userDAO.login(username, password);

        // 4. 결과 처리
        if (result == 1) {
            // 로그인 성공
        	HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return "main"; // 메인 페이지로 리다이렉트
        } else if (result == 0) {
            // 비밀번호 오류
            request.setAttribute("error", "비밀번호가 틀립니다.");
            return "login"; // 다시 로그인 폼으로 이동
        } else if (result == -1) {
            // 아이디 없음
            request.setAttribute("error", "존재하지 않는 아이디입니다.");
            return "login";
        } else if (result == -2) {
            // 데이터베이스 오류
            request.setAttribute("error", "데이터베이스 오류가 발생했습니다.");
            return "login";
        }

        // 기타 예외 처리
        request.setAttribute("error", "알 수 없는 오류가 발생했습니다.");
        return "login";
    }

}
