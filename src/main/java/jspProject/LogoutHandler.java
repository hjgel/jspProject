package jspProject;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate();
		return "main";
	}
	

}
