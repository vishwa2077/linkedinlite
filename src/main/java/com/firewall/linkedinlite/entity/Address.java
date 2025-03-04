package com.firewall.linkedinlite.entity;

import com.firewall.linkedinlite.util.AddressStatus;
import com.firewall.linkedinlite.util.AddressType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String doorNo;
	
	@Column(nullable = false)
	private String addressLine1;
	
	@Column(nullable = true)
	private String addressLine2;  // optional
	
	private String landmark;
	private String city;
	private String state;
	private String country;
	private int pinCode;
	
	@Enumerated(EnumType.STRING)
	private AddressType type= AddressType.PRESENT; // enum -> permanent || present || work || home 
	
	@Enumerated(EnumType.STRING)
	private AddressStatus status = AddressStatus.ACTIVE; // enum --> active || in_active
	
	@ManyToOne
	private Employee employee;
}

/*
 * POST   -->    /addresses/{empId}      address json Obj { }   ==> save address for a employee
 * GET    -->    /addresses/{empId} 						    ==> fetch all address of an employee
 * GET	  --> 	 /addresses/{addId}							    ==> fetch a address by its ID
 * PATCH  --> 	 /addresses/type/{addId}/{type}  				==> change the address type by ID
 * PATCH  --> 	 /addresses/status/{addid}/{status} 			==> change the status by the address by ID
 * 
 * 
 	save address for particular employee
 	find the address for specific employee
 	update landmark
 * 
 */

