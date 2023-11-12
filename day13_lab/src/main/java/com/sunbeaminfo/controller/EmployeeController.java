package com.sunbeaminfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	//display emp by selected dept
	//../emps/list?dept_id=10
	@GetMapping("/list")
	public String listEmpByDepartment(Model map,@RequestParam Long deptId)
	{
		//SC : Long deptId=Long.parseLong(request.getParameter("deptId"))
		System.out.println("before setting map "+map+
				"Param val through annot"+deptId);
		System.out.println("list by dept");
		map.addAttribute("emp_list",empService.getAllEmpsByDeprtment(deptId));
		System.out.println("after"+map);
		return "/emps/list";
	}
}
