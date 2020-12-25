package com.ey.core.dao;

import java.util.List;

import com.ey.core.model.Enterprise;

public interface EnterpriseDAO {
	
	public void createEnt(Enterprise ent);

	public List<Enterprise> getAllEnterpriseDAO();

}
