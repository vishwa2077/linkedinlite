package com.firewall.linkedinlite.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.firewall.linkedinlite.dao.EmployeeDao;
import com.firewall.linkedinlite.entity.Employee;
import com.firewall.linkedinlite.exception.InvalidEmployeeIdException;
import com.firewall.linkedinlite.exception.NoEmployeeFoundException;
import com.firewall.linkedinlite.responseStructure.ResponseStructure;
import com.firewall.linkedinlite.util.EmployeeStatus;
import com.firewall.linkedinlite.util.Experience;

@Component
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;

//----------------------------------------------------------------------------------------------------

	public ResponseStructure<Employee> saveEmployee(Employee e) {

		ResponseStructure<Employee> structure = new ResponseStructure<>();
		try {
			e = dao.saveEmployee(e);
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Employee Inserted Success Done .... ");
			structure.setBody(e);

		} catch (DataIntegrityViolationException exp) {
			structure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			structure.setMessage("This Email is Already Used , Please use Different Email");
			structure.setBody(null);

		} finally {
			return structure;
		}

	}

//------------------------------------------------------------------------------------------------------	

	public ResponseStructure<List<Employee>> findAllEmployee() {

		List<Employee> listemp = dao.findAllEmployee();

		if (listemp.isEmpty())

			throw new NoEmployeeFoundException("No Employee Found in DataBase");

		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Employees Found ");
		structure.setBody(listemp);

		return structure;
	}

//------------------------------------------------------------------------------------------------------	

	public ResponseStructure<Employee> findEmployeeById(int id) {

		ResponseStructure<Employee> structure = new ResponseStructure<>();

		Optional<Employee> optional = dao.findEmployeeById(id);

		if (optional.isEmpty())

			throw new InvalidEmployeeIdException("Wrong Employee Id , please give correct ID");

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Found ");
		structure.setBody(optional.get());

		return structure;
	}

//------------------------------------------------------------------------------------------------------	

	public ResponseStructure<List<Employee>> findAllActiveEmployees() {

		List<Employee> listemp = dao.findAllActiveEmployee();

		if (listemp.isEmpty())

			throw new NoEmployeeFoundException("No Employee Found in DataBase");

		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Active  Employees Found Done ... ");
		structure.setBody(listemp);

		return structure;

	}

//------------------------------------------------------------------------------------------------------	

	public ResponseStructure<Employee> updateStatusInactive(int emid) {

		Optional<Employee> optional = dao.findEmployeeById(emid);

		if (optional.isEmpty())
			throw new InvalidEmployeeIdException("Wrong Employee ID , Please Give Correct Employee ID");

		Employee employee = optional.get();

		employee.setStatus(EmployeeStatus.IN_ACTIVE);

		employee = dao.saveEmployee(employee);

		ResponseStructure<Employee> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage(" Employee Status updated to Inactive ");
		structure.setBody(employee);

		return structure;
	}


//------------------------------------------------------------------------------------------------------		

	public ResponseStructure<Employee> updateStatusActive(int emid) {

		Optional<Employee> optional = dao.findEmployeeById(emid);

		if (optional.isEmpty())

			throw new InvalidEmployeeIdException("Incorrect Employee ID , please Enter Valid Employee ID");

		Employee employee = optional.get();
		employee.setStatus(EmployeeStatus.ACTIVE);
		employee = dao.saveEmployee(employee);

		ResponseStructure<Employee> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Status updated to active  ");
		structure.setBody(employee);

		return structure;
	}

//------------------------------------------------------------------------------------------------------	

	public ResponseStructure<List<Employee>> findAllInactiveEmployees() {

		List<Employee> list = dao.findAllInactiveEmployees();

		if (list.isEmpty())

			throw new NoEmployeeFoundException("No Employees Found In DataBase");

		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Inactive Employees Found Done ... ");
		structure.setBody(list);

		return structure;
	}

//------------------------------------------------------------------------------------------------------	

	public ResponseStructure<Employee> updateStatusBlocked(int emid) {

		Optional<Employee> optional = dao.findEmployeeById(emid);

		if (optional.isEmpty())
			throw new InvalidEmployeeIdException("Incorrect Employee ID, please enter Valid Employee Id");

		Employee employee = optional.get();
		employee.setStatus(EmployeeStatus.BLOCKED);
		employee = dao.saveEmployee(employee);

		ResponseStructure<Employee> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Status updated to Blocked  ");
		structure.setBody(employee);

		return structure;
	}

//------------------------------------------------------------------------------------------------------	

	public ResponseStructure<List<Employee>> findAllBlockedEmployees() {

		List<Employee> list = dao.findAllBlockedEmployees();

		if (list.isEmpty())
			throw new NoEmployeeFoundException("No Blocked Employees Found in DataBase");

		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Blocked Employees Found Done ... ");
		structure.setBody(list);

		return structure;
	}

//------------------------------------------------------------------------------------------------------	

	public ResponseStructure<Employee> updatePhoneNumber(int emid, long phone) {

		Optional<Employee> optional = dao.findEmployeeById(emid);

		if (optional.isEmpty())
			throw new InvalidEmployeeIdException("Wrong Employee ID , Enter Correct ID");

		Employee employee = optional.get();
		employee.setPhone(phone);
		dao.saveEmployee(employee);

		ResponseStructure<Employee> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage(" Employee Phone Number Updated Done .... ");
		structure.setBody(employee);

		return structure;
	}

//------------------------------------------------------------------------------------------------------	

	public ResponseStructure<Employee> updateEmail(String email, int emid) {
		Optional<Employee> optional = dao.findEmployeeById(emid);

		if (optional.isEmpty())
			throw new InvalidEmployeeIdException("Wrong Employee ID , Enter Correct ID");

		Employee employee = optional.get();
		employee.setEmail(email);

		ResponseStructure<Employee> structure = new ResponseStructure<>();
		
		try {
			dao.saveEmployee(employee);
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Employee Email Updated Done ....");
			structure.setBody(employee);
		} catch (DataIntegrityViolationException e) {
			structure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			structure.setMessage("This Email is Already Used , Please use Different Email");
			structure.setBody(null);
		} finally {
			return structure;

		}
	}

	public ResponseStructure<Employee> changePasswordBasedOnEmail(String email, String password) {

		Optional<Employee> optional = dao.changePasswordBasedOnEmail(email);

		if (optional.isEmpty())
			throw new InvalidEmployeeIdException("Wrong Employee Email , Enter Correct Email");

		Employee employee = optional.get();
		employee.setPassword(password);
		dao.saveEmployee(employee);

		ResponseStructure<Employee> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee Password Updated Done ....");
		structure.setBody(employee);

		return structure;

	}

	public ResponseStructure<List<Employee>> searchDateOfJoin(LocalDate joindate) {

	List<Employee> list = dao.searchDateOfJoin(joindate);
	
	if (list.isEmpty())

		throw new NoEmployeeFoundException("No Employees Found In DataBase");

	ResponseStructure<List<Employee>> structure = new ResponseStructure<>();

	structure.setStatus(HttpStatus.OK.value());
	structure.setMessage(" Matching Records Found ");
	structure.setBody(list);

	return structure;
	}

	public ResponseStructure<Employee> updateExperience(int emid) {
		Optional<Employee> optional = dao.findEmployeeById(emid);

		if (optional.isEmpty())
			throw new InvalidEmployeeIdException("Wrong Employee ID , Enter Correct ID");

		Employee employee = optional.get();
		employee.setExperience(Experience.EXPERIENCED);
		dao.saveEmployee(employee);

		ResponseStructure<Employee> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage(" Employee Experienced  Updated Done .... ");
		structure.setBody(employee);

		return structure;
	}
		
		
		
	

//------------------------------------------------------------------------------------------------------	

}
