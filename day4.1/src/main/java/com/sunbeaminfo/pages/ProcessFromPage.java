package com.sunbeaminfo.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame.Scope;
import com.sunbeaminfo.pojos.Course;
import com.sunbeaminfo.pojos.Student;

/**
 * Servlet implementation class ProcessFromPage
 */
@WebServlet("/process_form")
public class ProcessFromPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set cont type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) 
		//above 2 steps will NOT be required : since in forward
		//WC is going to discard resp buffer.
		{
			// create student instance from req params
			String firstName = request.getParameter("fn");
			String lastName = request.getParameter("ln");
			int score1 = Integer.parseInt(request.getParameter("score"));
			Course chosenCourse = Course.valueOf(request.getParameter("course").toUpperCase());
			Student student = new Student(firstName, lastName, score1, chosenCourse);
			if (student.getScore() > chosenCourse.getMinScore())
				student.setAdmissionStatus(true);
			//save student details under curnt request scope
			request.setAttribute("student_dtls", student);
			pw.print("<h5>from 1st page ....</h5>");
			pw.flush();//commits resp
			//server pull => Request dispatching tech : forward
			//1. get RD 
			RequestDispatcher rd=request.getRequestDispatcher("result");
			//2. forward 
			rd.forward(request, response);
			System.out.println("control came back....");//displayed on server side console
			pw.print("contents after forward...");//discarded !

		}
	}

}
