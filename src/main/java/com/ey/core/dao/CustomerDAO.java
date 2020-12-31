package com.ey.core.dao;

import java.util.List;

import com.ey.core.model.Customer;

public interface CustomerDAO {

	public void createCustomer(Customer cust);

	public List<Customer> getAllCustomers();

	public Customer findByCustomerName(String name);

	public Customer deleteByName(String name);

	public Customer updateEnterpise(String name, Customer cust);

}
