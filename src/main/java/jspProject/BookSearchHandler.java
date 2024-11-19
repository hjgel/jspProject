package jspProject;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookSearchHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return "book_search";
	}
	

}
