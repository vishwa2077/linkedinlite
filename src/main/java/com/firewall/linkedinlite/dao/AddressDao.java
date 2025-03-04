package com.firewall.linkedinlite.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firewall.linkedinlite.entity.Address;
import com.firewall.linkedinlite.repository.AddressRepository;

@Component
public class AddressDao {
	@Autowired
	private AddressRepository repo;

	public Address saveAddress(Address a) {
		return repo.save(a);
	}

	public List<Address> findAllAddress() {
		return repo.findAll();
	}

	public Optional<Address> findAddressById(int adid) {
		return repo.findById(adid);
	}

}
