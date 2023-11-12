package com.sunbeaminfo.pojos;

import java.time.LocalDate;
import javax.persistence.*;


@Entity 
@Table(name = "emps") 
public class Employee extends BaseEntity {
	@Column(name = "first_name", length = 20) // varchar(20)
	private String firstName;
	@Column(name = "last_name", length = 20) // varchar(20)
	private String lastName;
	@Column(length = 30, unique = true) // adds unique constraint
	private String email;
	@Column(length = 20, nullable = false) // not null constraint
	private String password;
	
	@Column(name = "join_date")
	// @Temporal : needed for java.util.Date/Calendar/GregorianCalendar
	// NOT needed for modern Java APIs (eg : LocalDate / LocalTime/LocalDateTime
	private LocalDate joinDate;
	@Enumerated(EnumType.STRING) // => col type : varchar : enum constant name
	@Column(name = "emp_type", length = 20)
	private EmploymentType empType;
	private double salary;
	@Lob // large object , mysql : col type longblob
	private byte[] image;
	//many to one
	@ManyToOne //mandatory , o.w MappingException
	@JoinColumn(name="dept_id")
	private Department selectedDept;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String firstName, String lastName, String email, String password, 
			LocalDate joinDate, EmploymentType empType, double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	
		this.joinDate = joinDate;
		this.empType = empType;
		this.salary = salary;
	}

	// add overloaded paramterized ctor for JPA constr expression
	public Employee(String firstName, String lastName, double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public EmploymentType getEmpType() {
		return empType;
	}

	public void setEmpType(EmploymentType empType) {
		this.empType = empType;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	

	public Department getSelectedDept() {
		return selectedDept;
	}

	public void setSelectedDept(Department selectedDept) {
		this.selectedDept = selectedDept;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", joinDate=" + joinDate + ", empType=" + empType + ", salary=" + salary + "]";
	}

}
