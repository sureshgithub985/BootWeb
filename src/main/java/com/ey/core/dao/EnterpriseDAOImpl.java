package com.ey.core.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.model.Enterprise;

@Repository
@Transactional
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

	@Override
	public Enterprise findByEntName(String name) {
		return entRepo.findByName(name);
	}

	@Override
	public Enterprise deleteByName(String name) {
		System.out.println("Going to delete........." + name);
		Enterprise ent1 = entRepo.findByName(name);
		if (ent1 != null)
			entRepo.delete(ent1);
		else
			System.out.println("Enterprise doesnt exists");

		return null;

	}

	@Override
	public Enterprise updateEnterpise(String name, Enterprise ent) {
		System.out.println("Going to select........." + name);
		Enterprise ent1 = entRepo.findByName(name);
		Enterprise ent2 = null;
		
		System.out.println("ent1 value is "+ent1);
		if (ent1 != null)
			ent2 = entRepo.save(ent);
		else
			System.out.println("Enterprise doesnt exists");

		return ent2;

	}

}
