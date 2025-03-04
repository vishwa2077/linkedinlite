package com.firewall.linkedinlite.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.firewall.linkedinlite.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	
	@Query("select e from Employee e where e.status='ACTIVE'")
	List<Employee> findAllActiveEmployee();

	@Query("select e from Employee e where e.status='IN_ACTIVE'")
	List<Employee> findAllInactiveEmployees();

	@Query("select e from Employee e where e.status='BLOCKED'")
	List<Employee> findAllBlockedEmployees();

	
	Optional<Employee> findByEmail(String email);

	@Query("select e from Employee e where e.dateOfJoin = :year")
	List<Employee> searchByJoinDate(@Param("year") LocalDate date);

}
