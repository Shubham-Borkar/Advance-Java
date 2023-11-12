package com.sunbeaminfo.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeaminfo.dao.CandidateDaoImpl;
import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.User;

/**
 * Servlet implementation class AdminMainPage
 */
@WebServlet("/logout")
public class LogoutPage extends HttpServlet {
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
			pw.print("<h4>In logout page </h4>");
			// get user details from session
			// get session
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user_dtls");
			if (user != null) {
				// get daos
				CandidateDaoImpl candidateDao = (CandidateDaoImpl) session.getAttribute("candidate_dao");
				UserDaoImpl userDao=(UserDaoImpl) session.getAttribute("user_dao");
				pw.print("<h4> Hello , " + user.getFirstName() 
				+ " " + user.getLastName() + "</h4>");
				//chk if voted or not
				if(user.isStatus())
				{
					//alrdy voted
					pw.print("<h4> You have already Voted !!!!!</h4>");
				} else {
					//http://localhost:8080/day3.1/logout?cid=6
					//update voting sts n incr votes
					pw.print("<h5> "
					+userDao.updateVotingStatus(user.getUserId())+"</h4>");
					int candidateId=Integer.parseInt(request.getParameter("cid"));
					pw.print("<h5> "+candidateDao.updateVotes
							(candidateId)+"</h5>");
				}
				
			} else // if cookies are blocked !
				pw.print("<h4> Session Tracking Failed !!!!! , No Cookies !!! </h4>");
			//invalidate new OR existing session
			session.invalidate(); 
			pw.print("<h5> You have logged out ...</h5>");

		} catch (Exception e) {
			throw new ServletException("err in do-get : "+getClass(), e);
		}
	}

}
