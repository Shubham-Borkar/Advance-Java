package com.sunbeaminfo.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "depts")
public class Department extends BaseEntity {
	@Column(unique = true, length = 100)
	private String name;
	@Column(length = 30)
	private String location;
	// one to many : bi dir
	@OneToMany(mappedBy = "selectedDept", cascade = CascadeType.ALL/*
																	 * , orphanRemoval = true
																	 */) //mappedBy : mandatory only bi dir asso.
	//whr : appears in inverse side , what : name of asso. prop in the owning side.
	private List<Employee> empList = new ArrayList<>();// to avoid NPExc.

	public Department() {
		// TODO Auto-generated constructor stub
	}

	public Department(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	//add helper methods to establish n remove a bi dir link
	public void addEmployee(Employee e)
	{
		empList.add(e);//parent --> child link
		e.setSelectedDept(this);//child --> parent
	}
	public void removeEmployee(Employee e)
	{
		empList.remove(e);//parent ---X----> child link
		e.setSelectedDept(null);//child ---X---> parent
	}

	// NEVER add association field toString: to avoid recursion
	@Override
	public String toString() {
		return "Department " + getId() + " [name=" + name + ", location=" + location + "]";
	}

}
