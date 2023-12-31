package com.sunbeaminfo.dto;

import java.time.LocalDate;

import com.sunbeaminfo.entities.Designation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpResp {	
	private Long id;
	private String firstName;	
	private String lastName;		
	private LocalDate joinDate;
	private double salary;	
	private String location;	
	private String department;
	private Designation designation;
	private String email;
}
