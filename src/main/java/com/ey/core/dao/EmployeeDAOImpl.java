package com.ey.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.entity.Employee;
import com.ey.core.service.EmployeeDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void saveEmployee(Employee emp) {

		log.debug("Create Employee DAO...");

		employeeRepository.save(emp);

	}

}
