package com.ey.core.service;

import java.util.List;

import com.ey.core.model.Enterprise;

public interface EnterpriseService {

	public void addEnterprise(Enterprise ent);

	public List<Enterprise> getAllEnterprises();

	public Enterprise getEnterpriseByName(String name);

	public Enterprise deleteEnterpriseByName(String name);

	public Enterprise updateEnterprise(String name, Enterprise ent);
}
