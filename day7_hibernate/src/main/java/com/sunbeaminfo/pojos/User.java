package com.sunbeaminfo.pojos;

import java.time.LocalDate;
import javax.persistence.*; //java

@Entity  //mandatory class level annotation to tell hibernate foll is an entitty class,whose life 
//cycle must be managed by hibernate
@Table(name="users")  //table name
public class User {
	
//	User props : userId (Long) , firstName ,lastName , email,password,
	//role(Enum : UserRole - ADMIN,CUSTOMER,SELLER,MANAGER,BUYER...) ,regDate: LocalDate ,
	//regAmount:double
	@Id  //=> PK constraint
	//auto id generation based on auto increment seq maintained by hibernate
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")  //to supply column name
	private Long userId; //Mandatory requirement of Hibernate is:ID property must be serializable
	@Column(name="first_name",length = 20)
	private String firstName;
	@Column(name="last_name",length = 20)
	private String lastName;
	@Column(length = 25,unique = true)
	private String email;
	@Column(length = 20,nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	@Column(name = "reg_date")
	private LocalDate regDate;
	@Column(name = "reg_amount")
	private double regAmount;
	
	public User() {
		
	}

	public User(String firstName, String lastName, String email, String password, UserRole role, LocalDate regDate,
			double regAmount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.regDate = regDate;
		this.regAmount = regAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}
	
	
	
	
	
	
}
