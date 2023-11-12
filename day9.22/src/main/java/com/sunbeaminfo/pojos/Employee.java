package com.sunbeaminfo.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
	// many to one
	@ManyToOne(fetch = FetchType.LAZY) // mandatory , o.w MappingException
	@JoinColumn(name = "dept_id")
	private Department selectedDept;
	// bi dir many-many , configure this as inverse side
	@ManyToMany(mappedBy = "employees")
	private Set<Project> projects = new HashSet<>();
	// entity ---> Value (composite value type)
	@Embedded // OPTIONAL
	private Passport myPassport;
	// Emp ---> multiple skills , eg of collection of basic value type
	@ElementCollection // mandatory , o.w MappingExc
	@CollectionTable(name = "emp_skills",
	joinColumns = @JoinColumn(name = "emp_id"))
	@Column(name="skill",length = 30)
	private List<String> skills = new ArrayList<>();

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String firstName, String lastName, String email, String password, LocalDate joinDate,
			EmploymentType empType, double salary) {
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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Passport getMyPassport() {
		return myPassport;
	}

	public void setMyPassport(Passport myPassport) {
		this.myPassport = myPassport;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", joinDate=" + joinDate + ", empType=" + empType + ", salary=" + salary + "]";
	}

	// override hashCode n equals as per the contract
	// CONTRACT : Equal objs MUST produce SAME hashCode
	// For const perf. : Un equal objs SHOULD produce distinct hashCode

	// equality testing criterial : email

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
