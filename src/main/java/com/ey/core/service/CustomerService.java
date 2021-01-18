package com.ey.core.service;

import java.util.List;

import com.ey.core.entity.Customer;

public interface CustomerService {

	public void addCustomer(Customer cust);

	public List<Customer> getAllCustomers();

	public Customer getCustomerByEmail(String email);

	public void deleteCustomerByEmail(String email);

	public void updateCustomer(Customer cust, String email);

}
