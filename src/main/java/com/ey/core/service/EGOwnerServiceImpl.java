package com.ey.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.core.dao.EGOwnerDAO;
import com.ey.core.entity.EGOwner;

@Service
public class EGOwnerServiceImpl implements EGOwnerService {

	@Autowired
	private EGOwnerDAO egownerDAO;

	@Override
	public void createEnterpriseGroupOwner(String name, EGOwner egowner) {

		egownerDAO.addEnterpriseGroupOwner(name, egowner);
	}

}
