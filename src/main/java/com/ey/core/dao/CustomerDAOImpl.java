package com.ey.core.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.model.Address;
import com.ey.core.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private CustomerRepository custRepository;

	@Override
	public void createCustomer(Customer cust) {
		log.debug(" Create Customer DAO ");

		List<Address> addList = cust.getAddress();
		for (Address add : addList) {
			add.setCustomer(cust);
		}

		custRepository.save(cust);
	}

	@Override
	public List<Customer> getAllCustomers() {

		log.debug(" GETALL Customer DAO ");

		return custRepository.findAll();
	}

	@Override
	public Customer findByCustomerName(String name) {

		log.debug(" GET Customer DAO ");
		return custRepository.findByfirstName(name);
	}

	@Override
	public Customer deleteByName(String name) {

		log.debug(" Delete Customer DAO ");
		return null;
	}

	@Override
	public Customer updateEnterpise(String name, Customer cust) {

		log.debug(" Update Customer DAO ");

		return null;
	}

}
