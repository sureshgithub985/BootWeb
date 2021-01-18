package com.ey.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.core.dao.CustomerDAO;
import com.ey.core.entity.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO custDAO;

	@Override
	public void addCustomer(Customer cust) {
		log.debug("Add Customer Service ");
		custDAO.createCustomer(cust);
	}

	@Override
	public List<Customer> getAllCustomers() {
		log.debug("GetAll Customer Service ");
		return custDAO.getAllCustomers();
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		log.debug("GetByEmail Customer Service ");
		return custDAO.findByEmail(email);
	}

	@Override
	public void deleteCustomerByEmail(String email) {
		log.debug("Delete Customer Service ");
		custDAO.deleteByEmail(email);
	}

	@Override
	public void updateCustomer(Customer cust, String email) {
		log.debug("Update Customer Service ");
		custDAO.updateCustomer(cust, email);

	}

}
