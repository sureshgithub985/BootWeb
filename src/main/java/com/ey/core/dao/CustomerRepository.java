package com.ey.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.model.Customer;
import com.ey.core.model.Enterprise;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	public Customer findByfirstName(String firstName);

}
