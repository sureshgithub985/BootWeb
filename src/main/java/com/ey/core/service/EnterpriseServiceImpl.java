package com.ey.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.core.dao.EnterpriseDAO;
import com.ey.core.model.Enterprise;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	private EnterpriseDAO entDAO;

	@Override
	public void addEnterprise(Enterprise ent) {

		entDAO.createEnt(ent);

	}

	@Override
	public List<Enterprise> getAllEnterprises() {
		
		return entDAO.getAllEnterpriseDAO();
	}

}
