package com.ey.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	public Customer findByfirstName(String firstName);

}
