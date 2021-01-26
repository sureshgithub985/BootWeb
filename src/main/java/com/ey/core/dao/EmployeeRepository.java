package com.ey.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
