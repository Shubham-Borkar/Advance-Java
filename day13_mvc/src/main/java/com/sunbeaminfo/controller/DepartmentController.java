package com.sunbeaminfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sunbeaminfo.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	//dependency : service layer i/f
	@Autowired
	private DepartmentService deptService;
	public DepartmentController() {
		System.out.println("in ctor of "+getClass());
	}
	//add req handling method , to list all depts
	@RequestMapping("/display")
	public String listAllDepts(Model modelMap)
	{
		System.out.println("in list all depts "+modelMap);//{}
		//Model API : Model addAttribute(String nm,Object val)
		modelMap.addAttribute("dept_list", deptService.getAllDepts());
		System.out.println("after "+modelMap);//{populated : 1attr}
		return "/dept/list";
		//Controller explicitly rets LVN to D.S
		//SC implicitly rets model attr map to D.S --> sends LVN --> V.R --> AVN
		//D.S chks for model attrs ---present --adds the model attr under req scope
		//--> forwards the clnt to the view layer.
	}

}
