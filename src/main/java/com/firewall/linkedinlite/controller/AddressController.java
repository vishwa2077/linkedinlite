package com.firewall.linkedinlite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firewall.linkedinlite.entity.Address;
import com.firewall.linkedinlite.responseStructure.ResponseStructure;
import com.firewall.linkedinlite.service.AddressService;

@RestController
@RequestMapping("addresses")
public class AddressController {
	@Autowired
	private AddressService service;

	
	@PostMapping("/{emid}")
	public ResponseStructure<Address> saveAddress(@RequestBody Address a, @PathVariable int emid) {
		return service.saveAddress(a, emid);
	}

	@GetMapping
	public ResponseStructure<List<Address>> findAllAddress() {
		return service.findAllAddress();
	}

	@GetMapping("/{adid}")
	public ResponseStructure<Address> findAddressById(@PathVariable int adid) {
		return service.findAddressById(adid);
	}

}
