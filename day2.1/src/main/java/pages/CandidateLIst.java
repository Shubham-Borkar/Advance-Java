package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeaminfo.dao.UserDaoImpl;

/**
 * Servlet implementation class AdminMainPage
 */
@WebServlet("/candidate_list")
public class CandidateLIst extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	public void init() throws ServletException {
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			throw new ServletException("init of " + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// print a greeting to admin
			pw.print("<h4>In candidate list page </h4>");
			// retrieve clnt details from a cookie
			// 1. gett cookies from req.
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies)
					if (c.getName().equals("user_details")) {
						pw.print("<h5> User Details " + c.getValue() + "</h5>");
						break;
					}
			} else
				pw.print("<h4> Session Tracking Failed !!!!! , No Cookies !!! </h4>");

		}
	}
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// print a greeting to admin
			pw.print("<h4>In candidate list page </h4>");
			try {
				userDao.candlist();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies)
					if (c.getName().equals("user_details")) {
						pw.print("<h5> User Details " + c.getValue() + "</h5>" +"<hr>");
						break;
					}
			} else
				pw.print("<h4> Session Tracking Failed !!!!! , No Cookies !!! </h4>");

		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
