package com.ey.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.entity.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {

	public Enterprise findByName(String name);

}
