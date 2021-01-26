package com.ey.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.core.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeSeviceIml implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public void createEmployee(Employee emp) {
		log.debug("Create Employee Service...");
		employeeDAO.saveEmployee(emp);

	}

}
