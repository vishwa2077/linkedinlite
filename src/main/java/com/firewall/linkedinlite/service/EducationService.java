package com.firewall.linkedinlite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.firewall.linkedinlite.dao.EducationDao;
import com.firewall.linkedinlite.dao.EmployeeDao;
import com.firewall.linkedinlite.entity.Education;
import com.firewall.linkedinlite.entity.Employee;
import com.firewall.linkedinlite.exception.InvalidEducationIdException;
import com.firewall.linkedinlite.exception.InvalidEmployeeIdException;
import com.firewall.linkedinlite.exception.NoEducationFoundException;
import com.firewall.linkedinlite.responseStructure.ResponseStructure;
import com.firewall.linkedinlite.util.EducationStatus;
import com.firewall.linkedinlite.util.HighestQualification;

@Component
public class EducationService {

	@Autowired
	private EmployeeDao empdao;
	@Autowired
	private EducationDao edudao;

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<Education> saveEducation(Education edu, int emid) {
		Optional<Employee> optional = empdao.findEmployeeById(emid);
		if (optional.isEmpty())

			throw new InvalidEmployeeIdException(" Wrong Employee ID , so Please give Correct Employee ID");

		Employee employee = optional.get();
		edu.setEmployee(employee);
		edu = edudao.saveEducation(edu);

		ResponseStructure<Education> structure = new ResponseStructure<>();
		structure.setStatus(200);
		structure.setMessage("Education Saved Success Done ....");
		structure.setBody(edu);

		return structure;
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<List<Education>> findAllEducation() {

		List<Education> list = edudao.findAllEducation();

		if (list.isEmpty())

			throw new NoEducationFoundException(" Education Details not there in DataBase");

		ResponseStructure<List<Education>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage(" Education Found Success Done ....");
		structure.setBody(list);

		return structure;
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<Education> findEducationById(int edid) {

		Optional<Education> optional = edudao.findEduccationById(edid);

		if (optional.isEmpty())

			throw new InvalidEducationIdException("Wrong Education ID, Please Give Correct Education ID");

		ResponseStructure<Education> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage(" Education Found Success Done ....");
		structure.setBody(optional.get());

		return structure;
	}

//----------------------------------------------------------------------------------------------------

//	public ResponseStructure<List<Education>> findAllEducationByEmpId(int empId) {
//	    List<Education> edulist = edudao.findAllEducation()
//	            .stream()
//	            .filter(edu -> edu.getEmployee().getId() == empId)
//	            .toList();
//
//	    if (edulist.isEmpty()) {
//	        throw new NoEducationFoundException("No Education Found in Database");
//	    }
//
//	    return new ResponseStructure<>(
//	            HttpStatus.OK.value(),
//	            "Education Found Successfully for Employee ID: " + empId,
//	            edulist
//	    );
//	}

	public ResponseStructure<List<Education>> findAllEducationByEmpId(int emid) {

		List<Education> edulist = edudao.findAllEducation();

		if (edulist.isEmpty())

			throw new NoEducationFoundException("No Education Found in DataBase");

		ArrayList<Education> fel = new ArrayList<>();

		for (Education edu : edulist) {
			Employee emp = edu.getEmployee();

			if (emp.getId() == emid) {
				fel.add(edu);
			}
		}
		if (fel.isEmpty())

			throw new NoEducationFoundException("No Education Found in DataBase");

		ResponseStructure<List<Education>> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage(" Education Found Success from Employee ID :  " + emid);
		structure.setBody(fel);

		return structure;
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<Education> updateStatusToInactive(int eduid) {

		Optional<Education> optional = edudao.findEduccationById(eduid);

		if (optional.isEmpty())
			throw new InvalidEducationIdException("No Education found in DataBase");

		Education edu = optional.get();

		edu.setStatus(EducationStatus.IN_ACTIVE);
		edu = edudao.saveEducation(edu);

		ResponseStructure<Education> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage(" Update success InActive to Inactive");
		structure.setBody(edu);

		return structure;
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<Education> updateStatusToactive(int eduid) {

		Optional<Education> optional = edudao.findEduccationById(eduid);

		if (optional.isEmpty())
			throw new InvalidEducationIdException("No Education found in DataBase");

		Education edu = optional.get();

		edu.setStatus(EducationStatus.ACTIVE);
		edu = edudao.saveEducation(edu);

		ResponseStructure<Education> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage(" Update success Active to Inactive");
		structure.setBody(edu);

		return structure;
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<Education> updateHQtoNo(int edid) {

		Optional<Education> optional = edudao.findEduccationById(edid);

		if (optional.isEmpty())
			throw new InvalidEducationIdException("invalid Education ID , Enter Correct ID");

		Education education = optional.get();
		education.setHighestQualification(HighestQualification.NO);
		education = edudao.saveEducation(education);

		return new ResponseStructure<>(HttpStatus.OK.value(), "Edcation HQ 'NO' Updated Done", education);
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<Education> updateHQtoYes(int edid) {

		Optional<Education> optional = edudao.findEduccationById(edid);

		if (optional.isEmpty())
			throw new InvalidEducationIdException("invalid Education ID , Enter Correct ID");

		Education education = optional.get();
		education.setHighestQualification(HighestQualification.YES);
		education = edudao.saveEducation(education);

		return new ResponseStructure<>(HttpStatus.OK.value(), "Edcation HQ 'YES' Updated Done", education);
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<List<Education>> searchMorethanpercentage(double percen) {

		List<Education> list = edudao.searchMorethanpercentage(percen);

		if (list.isEmpty())
			throw new NoEducationFoundException(
					"Education Percentage More than < " + percen + "No Matched Education Found in DataBase");

		return new ResponseStructure<>(HttpStatus.OK.value(), "Matched Records Found -- morethan > " + percen + " %",
				list);
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<List<Education>> searchLessthanPercentage(double percen) {

		List<Education> list = edudao.searchLessthanPercentage(percen);

		if (list.isEmpty())
			throw new NoEducationFoundException(
					"Education Percentage Less than < " + percen + "No Matched Education Found in DataBase");

		return new ResponseStructure<>(200, "Matched records found -- lessthan < " + percen + " %", list);
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<List<Education>> searchPassedoutYear(int year) {
		List<Education> list = edudao.searchPassedoutYear(year);

		if (list.isEmpty())
			throw new NoEducationFoundException(
					"Year Of Passed Out " + year + " No Matched Education Found in Database");

		return new ResponseStructure<>(200, " Matched Records Found , Year Of Passed Out " + year, list);
	}
//----------------------------------------------------------------------------------------------------

	public ResponseStructure<List<Education>> searchUniversityName(String uniname) {

		List<Education> list = edudao.searchUniversityName(uniname);

		if (list.isEmpty())
			throw new NoEducationFoundException(" No Matched Records Found in dataBase");

		return new ResponseStructure<>(200, "Matched Records Found , University Name :" + uniname, list);
	}

//----------------------------------------------------------------------------------------------------
	public ResponseStructure<List<Education>> searchQualification(String qualifi) {

		List<Education> list = edudao.searchQualification(qualifi);

		if (list.isEmpty())
			throw new NoEducationFoundException(" No Matched Records Found in dataBase");

		return new ResponseStructure<>(200, "Matched Records Found , University Name :" + qualifi, list);
	}

//----------------------------------------------------------------------------------------------------
	public ResponseStructure<List<Education>> searchStream(String stream) {

		List<Education> list = edudao.searchStream(stream);

		if (list.isEmpty())
			throw new NoEducationFoundException(" No Matched Records Found in dataBase");

		return new ResponseStructure<>(200, "Matched Records Found , University Name :" + stream, list);
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<List<Education>> findAllActiveEducation() {
		List<Education> list = edudao.findAllActiveEducation();

		if (list.isEmpty())
			throw new NoEducationFoundException("No Active Education Found in DataBase");

		return new ResponseStructure<>(200, "All Active Education Records Found ", list);
	}

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<List<Education>> findAllInactiveEducation() {

		List<Education> list = edudao.findAllInactiveEducation();

		if (list.isEmpty())
			throw new NoEducationFoundException("No Active Education Found in DataBase");

		return new ResponseStructure<>(200, "All Inactive Education Records Found ", list);
	}

//----------------------------------------------------------------------------------------------------
	
	public ResponseStructure<List<Education>> findAllHqYesEducation() {

		List<Education> list = edudao.findAllHqYesEducation();
		
		if (list.isEmpty())
			throw new NoEducationFoundException("No Active Education Found in DataBase");

		return new ResponseStructure<>(200, "All Higher Qualification 'YES' Records Found ", list);
	}

//----------------------------------------------------------------------------------------------------
	
	public ResponseStructure<List<Education>> findAllHqNoEducation() {
		List<Education> list = edudao.findAllHqNoEducation();
		
		if (list.isEmpty())
			throw new NoEducationFoundException("No Active Education Found in DataBase");
		
		return new ResponseStructure<>(200, "All Higher Qualification 'NO' Records Found ", list);
	}

//----------------------------------------------------------------------------------------------------

}
