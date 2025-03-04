package com.firewall.linkedinlite.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.firewall.linkedinlite.entity.Employee;
import com.firewall.linkedinlite.repository.EmployeeRepository;

@Component
public class EmployeeDao {
	@Autowired
	private EmployeeRepository repo;

	public Employee saveEmployee(Employee e) throws DataIntegrityViolationException {

		return repo.save(e);
	}

	public List<Employee> findAllEmployee() {
		return repo.findAll();
	}

	public Optional<Employee> findEmployeeById(int id) {
		return repo.findById(id);
	}

	public List<Employee> findAllActiveEmployee() {
		return repo.findAllActiveEmployee();
	}

	public List<Employee> findAllInactiveEmployees() {
		return repo.findAllInactiveEmployees();
	}

	public List<Employee> findAllBlockedEmployees() {
		return repo.findAllBlockedEmployees();
	}

	public Optional<Employee> changePasswordBasedOnEmail(String email) {
		return repo.findByEmail(email);
	}

	public List<Employee> searchDateOfJoin(LocalDate joindate) {
		return repo.searchByJoinDate(joindate);
	
	}

}
