package com.ey.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.entity.EGOwner;

@Repository
public class EGOwnerDAOImpl implements EGOwnerDAO {

	@Autowired
	private EGOwnerRepository egownerRepository;

	@Override
	public void addEnterpriseGroupOwner(String name, EGOwner egowner) {

		System.out.println(" name value is " + name);
		System.out.println("egowner value is " + egowner);
	}

}
