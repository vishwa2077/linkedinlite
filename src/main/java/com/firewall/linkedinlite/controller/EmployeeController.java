package com.firewall.linkedinlite.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firewall.linkedinlite.entity.Employee;
import com.firewall.linkedinlite.responseStructure.ResponseStructure;
import com.firewall.linkedinlite.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@Operation(summary = "To save an employee", description = "This API is used to save an Employee Object , this API accepts the Request Body of Employee Object which should contains the fields as Follows firstName,lastName,email,phone,password,salary in the requested JSON Object and it should not have id field in it ,if id present it perform update operation,if id not their means it performs save operation")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Employee saved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping
	public ResponseStructure<Employee> saveEmployee(@RequestBody Employee e) {
		return service.saveEmployee(e);
	}

	@Operation(summary = "To Fetch All Employees", description = "This API fetches all available employees and returns them as a list wrapped inside a structured response.Retrieves a list of all employees available in the system. This API is used to fetch employee details such as ID, name, email, salary,phone,password,experience and status. The response includes a structured format with status codes and messages.")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "200", description = "Successful response") })
	@GetMapping
	public ResponseStructure<List<Employee>> findAllEmployee() {
		return service.findAllEmployee();
	}

	@Operation(summary = "To Fetch Employee Based on ID", description = "This API allows users to fetch details of an employee by passing their unique ID  If the employee exists, their details will be returned inside a structured response, If the employee is not found, an appropriate error message is returned")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee found successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request") })
	@GetMapping("/{id}")
	public ResponseStructure<Employee> findEmployeeById(@PathVariable int id) {
		return service.findEmployeeById(id);
	}

	@Operation(summary = "Update employee status to Active", description = "Sets the status of a specific employee to Active ,If the employee exists, their status is updated , If the employee does not exist, an error message is returned.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee Status active is updated"),
			@ApiResponse(responseCode = "400", description = "unable to update status active") })
	@PatchMapping("/status/active/{emid}")
	public ResponseStructure<Employee> updateStatusActive(@PathVariable int emid) {
		return service.updateStatusActive(emid);
	}

	@Operation(summary = "Update employee status to Inactive", description = "Marks an employee as Inactive in the system.Inactive employees cannot perform actions but are still registered. If the employee does not exist, an error response is returned.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee Status Inactive is updated"),
			@ApiResponse(responseCode = "400", description = "unable to update status Inactive") })
	@PatchMapping("/status/inactive/{emid}")
	public ResponseStructure<Employee> updateStatusInactive(@PathVariable int emid) {
		return service.updateStatusInactive(emid);
	}

	@Operation(summary = "Update employee status to Blocked", description = "Marks an employee as Blocked in the system. A blocked employee may not be able to access company resources. If the employee does not exist, an error response is returned.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee Status blocked is updated"),
			@ApiResponse(responseCode = "400", description = "unable to update status blocked") })
	@PatchMapping("/status/blocked/{emid}")
	public ResponseStructure<Employee> updateStatusBlocked(@PathVariable int emid) {
		return service.updateStatusBlocked(emid);
	}

	@Operation(summary = "Get all active employees", description = " Fetching currently employed and available staff. Generating reports for active employees. Filtering employees who are not inactive or blocked. Retrieves a list of employees with 'Active' status , If no active employees are found, an empty list is returned.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Active Employee found successfully"),
			@ApiResponse(responseCode = "400", description = "No Active Employyes Found in Database") })
	@GetMapping("/status/active")
	public ResponseStructure<List<Employee>> findAllActiveEmployees() {
		return service.findAllActiveEmployees();
	}

	@Operation(summary = "Get all inactive employees", description = " Checking employees who are temporarily unavailable , Generating reports for HR regarding employees on leave ,Filtering employees who are not active but not blocked. Retrieves a list of employees with 'Inactive' status.If no inactive employees are found, an empty list is returned.")
	@GetMapping("/status/inactive")
	public ResponseStructure<List<Employee>> findAllInactiveEmployees() {
		return service.findAllInactiveEmployees();
	}

	@Operation(summary = "Get all blocked employees", description = "HR or Admin wants to review blocked employees.Generating security reports regarding restricted employees.Filtering employees who are not active due to policy violationsRetrieves a list of employees who are currently blocked in the system.If no blocked employees are found, an empty list is returned.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of blocked employees retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "No blocked employees found") })
	@GetMapping("/status/blocked")
	public ResponseStructure<List<Employee>> findAllBlockedEmployees() {
		return service.findAllBlockedEmployees();
	}

	@Operation(summary = "Update employee phone number", description = "Updates the phone number of an employee based on their employee ID. The new phone number must be a valid long number format.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Phone number updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid phone number format") })
	@PatchMapping("/phone/{phone}/{emid}")
	public ResponseStructure<Employee> updatePhoneNumber(@PathVariable int emid, @PathVariable long phone) {
		return service.updatePhoneNumber(emid, phone);
	}

	@Operation(summary = "Update employee email", description = "Updates the email address of an employee based on their employee ID. The new email must be in a valid format (e.g., example@domain.com).")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Email updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid email format"),
			@ApiResponse(responseCode = "404", description = "Employee not found") })
	@PatchMapping("/email/{email}/{emid}")
	public ResponseStructure<Employee> updateEmail(@PathVariable String email, @PathVariable int emid) {
		return service.updateEmail(email, emid);
	}

	@Operation(summary = "Change employee password", description = "Updates the password of an employee based on their registered email. The new password should meet security standards.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Password updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid password format") })
	@PatchMapping("/password/{email}/{password}")
	public ResponseStructure<Employee> changePasswordBasedOnEmail(@PathVariable String email,
			@PathVariable String password) {
		return service.changePasswordBasedOnEmail(email, password);
	}

	@Operation(summary = "Search employees by joining date", description = "Retrieves a list of employees who joined on the specified date.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees found"),
			@ApiResponse(responseCode = "404", description = "No employees found for the given date") })
	@GetMapping("/search/dateofjoin/{joindate}")
	public ResponseStructure<List<Employee>> searchDateOfJoin(@PathVariable LocalDate joindate) {
		return service.searchDateOfJoin(joindate);
	}

	@Operation(summary = "Update employee experience status", description = "Marks the employee as 'experienced' in the system.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Experience updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid employee ID") })
	@PatchMapping("/experience/experienced/{emid}")
	public ResponseStructure<Employee> updateExperience(@PathVariable int emid) {
		return service.updateExperience(emid);
	}

}
