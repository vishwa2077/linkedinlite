package com.firewall.linkedinlite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.firewall.linkedinlite.dao.AddressDao;
import com.firewall.linkedinlite.dao.EmployeeDao;
import com.firewall.linkedinlite.entity.Address;
import com.firewall.linkedinlite.entity.Employee;
import com.firewall.linkedinlite.exception.InvalidAddressIdException;
import com.firewall.linkedinlite.exception.InvalidEmployeeIdException;
import com.firewall.linkedinlite.exception.NoAddressFoundException;
import com.firewall.linkedinlite.responseStructure.ResponseStructure;

@Component
public class AddressService {
	@Autowired
	private EmployeeDao empdao;

	@Autowired
	private AddressDao adddao;

	public ResponseStructure<Address> saveAddress(Address add, int emid) {

		Optional<Employee> optional = empdao.findEmployeeById(emid);

		if (optional.isEmpty())

			throw new InvalidEmployeeIdException("Wrong employee ID, please Give Correct Employee ID");

		Employee employee = optional.get();
		add.setEmployee(employee);
		add = adddao.saveAddress(add);

		ResponseStructure<Address> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Address Inserted Success Done ...");
		structure.setBody(add);

		return structure;
	}

	public ResponseStructure<List<Address>> findAllAddress() {

		List<Address> list = adddao.findAllAddress();

		if (list.isEmpty())
			
			throw new NoAddressFoundException("No Address Found in DataBase");

		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Address Found Success Done ...");
		structure.setBody(list);

		return structure;
	}

	public ResponseStructure<Address> findAddressById(int adid) {
		
		Optional<Address> optional = adddao.findAddressById(adid);
		
		if(optional.isEmpty())
			throw new InvalidAddressIdException();
		
		ResponseStructure<Address> structure = new ResponseStructure<>();
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("All Address Found Success Done ...");
		structure.setBody(optional.get());
		
		
		
		return structure;
	}

}
