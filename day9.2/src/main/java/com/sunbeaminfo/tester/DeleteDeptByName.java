package com.sunbeaminfo.tester;

import java.util.Scanner;

import com.sunbeaminfo.dao.DepartmentDaoImpl;

public class DeleteDeptByName {

	public static void main(String[] args) {
		System.out.println("Enter the Dname to be Deleted");
		Scanner sc=new Scanner(System.in);
		DepartmentDaoImpl dao = new DepartmentDaoImpl();
		System.out.println(dao.deleteDept(sc.next()));

	}

}
