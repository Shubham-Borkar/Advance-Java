package com.sunbeaminfo.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.sunbeaminfo.entities.Designation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
/*
 * Validation rules : first name n last name : non blank email : valid n not
 * blank password : string password , not null emp designation : must be
 * supplied. join date : must be in present or future
 */
public class SignupRequest {
	@NotBlank(message = "first name can't be blank")
	private String firstName;
	@Length(min = 3, max = 20, message = "Invalid length of last name")
	private String lastName;
	@Email
	private String email;
	@NotNull
	@FutureOrPresent(message = "invalid join date!!")
	private LocalDate joinDate;
	@NotNull
	@Min(value = 30000, message = "sal must be > 30000")
	@Max(value = 150000, message = "sal must be < 30000")
	private double salary;
	private String location;
	private String department;
	@NotNull
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "invalid password format!!!!")
	private String password;
	@NotNull(message = "designation must be supplied!!!!")
	private Designation designation;
}
