package com.sunbeaminfo.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeaminfo.dao.CandidateDaoImpl;
import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/authenticate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private CandidateDaoImpl candidateDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			// create dao instance
			//centralized db handling
			userDao = new UserDaoImpl();
			candidateDao = new CandidateDaoImpl();
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
			candidateDao.cleanUp();
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
			if (user == null) // => invalid login Client pull I : for the navigation , user will have click on
								// retry link
				pw.print("<h4> Invalid Login !!!! Pls <a href='login.html'>Retry</a></h4>");
			else {
				// login successful --> continue to role based authorization
				// 1. get session from WC
				//get new or existing object of httpsession
				//wc add a cookie to reso header :jsessionid:alphanumeric value
				HttpSession session = request.getSession();
				System.out.println("is new " + session.isNew());// true
				System.out.println("sesison id " + session.getId());// random unique alphanum. string generated by WC
																	// one / clnt
				System.out.println("session imple class name" + session.getClass());
				// 2. save user details under session scope
				session.setAttribute("user_dtls", user);
				// 3. save daos under sesssion
				session.setAttribute("user_dao", userDao);
				session.setAttribute("candidate_dao", candidateDao);
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
					 * location=candidate_list , Set-Cookie : JSESSIONID : fdgfdhsf5647468 Resp body
					 * : EMPTY web broswer chks privacy settings -- cookies blocked : cookie won't
					 * be stored. cookies are accepted : chks age (expiry) = -1 cookie gets stored
					 * in cache. next req : http://host:port/day3/candidate_list , method=GET , hdr
					 * :Cookie : nm ,val
					 */
				}

			}
		} catch (Exception e) {
			throw new ServletException("err in do-post :" + getClass(), e);
		}
	}

}
