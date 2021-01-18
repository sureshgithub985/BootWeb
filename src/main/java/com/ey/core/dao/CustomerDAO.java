package com.ey.core.dao;

import java.util.List;

import com.ey.core.entity.Customer;

public interface CustomerDAO {

	public void createCustomer(Customer cust);

	public List<Customer> getAllCustomers();

	public Customer findByEmail(String email);

	public void deleteByEmail(String email);

	public void updateCustomer(Customer cust, String email);

}
