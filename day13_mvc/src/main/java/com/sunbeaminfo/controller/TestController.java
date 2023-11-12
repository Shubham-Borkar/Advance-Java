package com.sunbeaminfo.controller;
//com.sunbeaminfo

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // mandatory cls level annotation
//spring bean : singleton , eager
public class TestController {
	public TestController() {
		System.out.println("in ctor of " + getClass());
	}

	// req handling method to display static welcome mesg
	@RequestMapping("/test1")
	public String testMe() {
		System.out.println("in testMe");
		return "/welcome"; // Handler(controller) rets --->
		// LVN : logical/forward view name --> D.S (DispatcherServlet)
		// ---> sends it to V.R(View Resolver) --> AVN : actual view name :
		// prefix+LVN+suffix ---> /WEB-INF/views/welcome.jsp --> D.S
		//--> D.S forwards the clnt to the view layer --> /WEB-INF/views/welcome.jsp
	}
}
