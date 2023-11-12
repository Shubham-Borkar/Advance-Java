package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/authenticate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			// create dao instance
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			// centralized exc handling in servlet
			// How to inform WC , that init has failed?
			// Ans : by throwing ServletExc to the WC
			// ServletException(String mesg , Throwable rootCause)
			throw new ServletException("init of " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			userDao.cleanUp();
		} catch (Exception e) {
			System.out.println("err in destroy " + getClass());
			throw new RuntimeException("err in destroy " + getClass(), e);// not necessary !
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set resp cont type
		response.setContentType("text/html");
		// 2. PW
		try (PrintWriter pw = response.getWriter()) {
			// 3. read req params : em , pass
			String userEmail = request.getParameter("em");
			String userPwd = request.getParameter("pass");
			// 4. invoke dao's method
			User user = userDao.autheticateUser(userEmail, userPwd);

			// chk for valid login
			if (user == null) // => invalid login
				pw.print("<h4> Invalid Login !!!! Pls <a href='login.html'>Retry</a></h4>");
			else {
				// pw.print("in login servlet: succesful login !!!");
				// pw.flush(); un comment in lab session , to understand issue !
				// login successful --> continue to role based authorization
				// Cookie related steps 1.
				// 1. create a cookie
				Cookie c1 = new Cookie("user_details", user.toString());
				// 2. add the cookie to resp header
				response.addCookie(c1);
				if (user.getRole().equals("admin")) {
					response.sendRedirect("admin_main");
				} else {
					// = voter
					if (user.isStatus()) // => voted alrdy
						response.sendRedirect("logout");
					else // => voter : not yet voted
						response.sendRedirect("candidate_list");
					/*
					 * WC : DISCARDS resp buffer --> sends temp redirect resp SC 302 ,
					 * location=candidate_list , 
					 * Set-Cookie : user_details : user 's tostring
					 * Resp body : EMPTY
					 * clnt browser next req : 
					 * http://host:port/day2/candidate_list ,
					 * method=GET , hdr :Cookie : nm ,val
					 */
				}

			}
		} catch (Exception e) {
			throw new ServletException("err in do-post :" + getClass(), e);
		}
	}

}
