package com.sunbeaminfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
	public HomePageController() {
		System.out.println("in ctor " + getClass());
	}
	//add req handling method to forward clnt ---> index page
	@GetMapping("/")//=@RequestMapping(method=GET)
	public String showHomePage() {
		System.out.println("in show hm page");
		return "/index";//AVN : /WEB-INF/views/index.jsp
	}
}
