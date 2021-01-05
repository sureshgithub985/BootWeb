package com.ey.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.core.dao.CustomerDAO;
import com.ey.core.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO custDAO;

	@Override
	public void addCustomer(Customer cust) {
		log.debug("customer reached service " + cust);
		custDAO.createCustomer(cust);
	}

	@Override
	public List<Customer> getAllCustomers() {

		return custDAO.getAllCustomers();
	}

	@Override
	public Customer getCustomerByName(String name) {

		return custDAO.findByCustomerName(name);
	}

	@Override
	public Customer deleteCustomerByName(String name) {

		return null;
	}

	@Override
	public Customer updateCustomer(String name, Customer cust) {

		return null;
	}

}
