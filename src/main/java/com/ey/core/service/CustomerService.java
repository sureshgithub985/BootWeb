package com.ey.core.service;

import java.util.List;

import com.ey.core.model.Customer;

public interface CustomerService {

	public void addCustomer(Customer cust);

	public List<Customer> getAllCustomers();

	public Customer getCustomerByName(String name);

	public Customer deleteCustomerByName(String name);

	public Customer updateCustomer(String name, Customer cust);

}
