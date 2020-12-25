package com.ey.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.model.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {

	public List<Enterprise> findByName(String name);

}
