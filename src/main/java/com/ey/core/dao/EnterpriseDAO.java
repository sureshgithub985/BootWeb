package com.ey.core.dao;

import java.util.List;

import com.ey.core.entity.Enterprise;

public interface EnterpriseDAO {
	
	public void createEnt(Enterprise ent);

	public List<Enterprise> getAllEnterpriseDAO();

	public Enterprise findByEntName(String name);

	public Enterprise deleteByName(String name);

	public Enterprise updateEnterpise(String name, Enterprise ent);

}
