package com.sunbeaminfo.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeaminfo.dao.CandidateDaoImpl;
import com.sunbeaminfo.pojos.Candidate;
import com.sunbeaminfo.pojos.User;

/**
 * Servlet implementation class AdminMainPage
 */
@WebServlet("/candidate_list")
public class CandidateLIst extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// print a greeting to admin
			pw.print("<h4>In candidate list page </h4>");
			// retrieve clnt details from a HttpSession
			// 1. get session from WC
			HttpSession hs = request.getSession();
			System.out.println("is new " + hs.isNew());// false
			System.out.println("sesison id " + hs.getId());// same session id
			// 2. get user details from session scope
			User user = (User) hs.getAttribute("user_dtls");
			if (user != null) {
				pw.print("<h4>Hello , " + user.getFirstName() + " " + user.getLastName() + "</h4>");
				// get candidate dao from session scope
				CandidateDaoImpl dao = (CandidateDaoImpl) hs.getAttribute("candidate_dao");
				// get candidate list from dao
				List<Candidate> list = dao.getAllCandidates();
				// dyna mic form generation n rendering
				pw.print("<form action='logout'>");
				for (Candidate c : list)
					pw.print("<h5><input type='radio' name='cid' value='" + c.getCandidateId() + "'/>" + c.getName()
							+ "</h5>");
				pw.print("<h5><input type='submit' value='Cast A Vote'/></h5>");
				pw.print("</form>");
			}

			else // if cookies are blocked !
				pw.print("<h4> Session Tracking Failed !!!!! , No Cookies !!! </h4>");

		} catch (Exception e) {
			// re throw the exc to the caller : WC
			throw new ServletException("err in do-get" + getClass(), e);
		}
	}

}
