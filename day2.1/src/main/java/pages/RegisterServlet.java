package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Time;

import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerservlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			String fnn = request.getParameter("fn");
			String lnn = request.getParameter("ln");
			String emm = request.getParameter("em");
			String passs = request.getParameter("pass");
			String dobb = request.getParameter("dob");
			
			 Date datee=Date.valueOf(dobb);
			
		
			
			String role="voter";
			User newuser=new User(0, fnn, lnn, emm, passs, datee, false, role);
			System.out.println(newuser.toString());
			
			// 4. invoke dao's method
			String result = userDao.register(newuser);

			// chk for valid login
			if (result == null) // => invalid login
				pw.print("<h4> Registration Failed !!!! Pls <a href='login.html'>Retry</a></h4>");
			else {
				System.out.println(result);
				pw.print("<h4>" +result+" Pls <a href='login.html'>Go to Login</a></h4>");
				} 

			}
		 catch (Exception e) {
			throw new ServletException("err in do-post :" + getClass(), e);
		}
	}
}
	


