package com.sunbeaminfo.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeaminfo.pojos.Student;

/**
 * Servlet implementation class AdmissionResult
 */
@WebServlet("/result")
public class AdmissionResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set cont type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h4> Student score "+request.getParameter("score")+"</h4>");//not null !
			// get student details from request scope
			Student student = (Student) request.getAttribute("student_dtls");
			if (student != null) {
				pw.print("<h5>Hello , " + student.getFirstName() + " " + student.getLastName() + "</h5>");
				if (student.isAdmissionStatus())
					pw.print("<h5> You have been enrolled in Course " + student.getSelectedCourse() + "</h5>");
				else
					pw.print("<h5> Sorry !!! Admission denied for  Course " + student.getSelectedCourse() + "</h5>");
			} else
				pw.print("RD failed !!!!!");
		}
	}

}
