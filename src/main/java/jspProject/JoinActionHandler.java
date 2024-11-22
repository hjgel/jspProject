package jspProject;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspProject.user.User;
import jspProject.user.UserDAO;

public class JoinActionHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// 1. 클라이언트에서 넘어온 데이터 가져오기
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        // 2. 입력 데이터 검증
        if (username == null || password == null || name == null || 
            username.isEmpty() || password.isEmpty() || name.isEmpty()) {
            request.setAttribute("error", "입력이 안 된 사항이 있습니다.");
            return "signup"; // 다시 회원가입 폼으로 이동
        }

        // 3. User 객체 생성 및 DAO 호출
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);

        UserDAO userDAO = new UserDAO();
        int result = userDAO.join(user);

        // 4. 결과 처리
        if (result == -1) {
            // 회원가입 실패 (중복된 아이디)
            request.setAttribute("error", "이미 존재하는 아이디입니다.");
            return "signup"; // 다시 회원가입 폼으로 이동
        }

        // 5. 성공 시 리다이렉트
        return "main"; // 회원가입 성공 시 메인 페이지로 이동
    }
	

}
