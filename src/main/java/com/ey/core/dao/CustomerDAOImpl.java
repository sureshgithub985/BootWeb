package com.ey.core.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.entity.Address;
import com.ey.core.entity.Customer;
import com.ey.core.util.ResourceNotFoundException;
import com.ey.core.util.ValidationErrorException;

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

		Customer cust1 = custRepository.findByEmail(cust.getEmail());
		if (null != cust1)
			throw new ValidationErrorException("User already Exists");

		List<Address> addList = cust.getAddress();
		System.out.println("addList value is " + addList);
		if (null != addList) {
			for (Address add : addList) {
				add.setCustomer(cust);
			}

		}
		custRepository.save(cust);
	}

	@Override
	public List<Customer> getAllCustomers() {
		log.debug(" GETALL Customer DAO ");
		return custRepository.findAll();
	}

	@Override
	public Customer findByEmail(String email) {
		log.debug(" GET Customer DAO ");
		Customer cust = custRepository.findByEmail(email);

		if (cust == null)
			throw new ResourceNotFoundException("Cannot find object with given id");

		return cust;
	}

	@Override
	public void deleteByEmail(String email) {
		log.debug(" Delete Customer DAO ");
		Customer cust = custRepository.findByEmail(email);
		if (cust == null)
			throw new ResourceNotFoundException("Cannot find object with given id");

		custRepository.deleteByEmail(email);
	}

	@Override
	public void updateCustomer(Customer cust, String email) {
		log.debug(" Update Customer DAO ");
		Customer cust1 = custRepository.findByEmail(email);

		if (null != cust1) {
			if (cust.getFirstName() != null && !cust.getFirstName().equals(cust1.getFirstName()))
				cust1.setFirstName(cust.getFirstName());
			if (cust.getLastName() != null && !cust.getLastName().equals(cust1.getLastName()))
				cust1.setLastName(cust.getLastName());
			if (cust.getAddress() != null && !cust.getAddress().equals(cust1.getAddress()))
				cust1.setAddress(cust.getAddress());
			if (cust.getPhone() != null && !cust.getPhone().equals(cust1.getPhone()))
				cust1.setPhone(cust.getPhone());

			cust1.setUpdatedAt(cust.getUpdatedAt());
			custRepository.save(cust1);
		} else
			throw new ResourceNotFoundException("Cannot find object with given id");

	}

}
