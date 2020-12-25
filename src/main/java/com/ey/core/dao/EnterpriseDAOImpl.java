package com.ey.core.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.model.Enterprise;

@Repository
public class EnterpriseDAOImpl implements EnterpriseDAO {

	@Autowired
	private EnterpriseRepository entRepo;

	@Override
	public void createEnt(Enterprise ent) {
		System.out.println("we are in the repo model... class " + ent);

		Serializable save = entRepo.save(ent);
		System.out.println("save value is " + save);
	}

	@Override
	public List<Enterprise> getAllEnterpriseDAO() {

		return entRepo.findAll();
	}

}
