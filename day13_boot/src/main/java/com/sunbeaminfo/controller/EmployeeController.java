package com.sunbeaminfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunbeaminfo.service.EmployeeService;

@Controller
@RequestMapping("/emps")
public class EmployeeController {
	//dependency : emp service i/f
	@Autowired
	private EmployeeService empService;
	
	public EmployeeController() {
		System.out.println("in ctor of " + getClass());
	}
}
