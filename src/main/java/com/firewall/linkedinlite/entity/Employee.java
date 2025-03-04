package com.firewall.linkedinlite.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.firewall.linkedinlite.util.EmployeeStatus;
import com.firewall.linkedinlite.util.Experience;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	
	@CreationTimestamp
	private LocalDate dateOfJoin;
	
	@Column(unique = true,nullable = false)
	private String email;
	
	private double salary;
	private long phone;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Experience experience = Experience.FRESHER; // enum -> experienced | fresher
	
	@Enumerated(EnumType.STRING)
	private EmployeeStatus status = EmployeeStatus.ACTIVE; // enum -> active | in_active |blocked
	
	
}

/*
 * POST		--> /employees				json Obj employee{data} --> save an employee
 * 
 * GET		--> /employyes										--> fetch all employees
 * 
 * GET		--> /employees/{empId}								==> fetch employee by ID
 * 
 * POST		--> /employees/login		json Obj login{ }		==> login verification by email and password
 * 
 * PATCH 	--> /employee/status/{empid}/{status} 				==> change the status for employee by ID
 */












