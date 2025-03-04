package com.firewall.linkedinlite.entity;

import com.firewall.linkedinlite.util.EducationStatus;
import com.firewall.linkedinlite.util.HighestQualification;

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
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String universityName;
	private String qualification;
	private String stream;
	private double percentage;
	private int yearOfPassedOut;
	
	@Enumerated(EnumType.STRING)
	private HighestQualification highestQualification=HighestQualification.YES;  // enum -> yes | no
	
	@Enumerated(EnumType.STRING)
	private EducationStatus status = EducationStatus.ACTIVE; // enum --> active || in_active
	
	@ManyToOne
	private Employee employee;
}
