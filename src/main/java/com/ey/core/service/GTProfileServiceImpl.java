package com.ey.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.core.dao.GTProfileDAO;
import com.ey.core.model.GTProfile;

@Service
public class GTProfileServiceImpl implements GTProfileService {

	@Autowired
	private GTProfileDAO gtprofileDAO;

	@Override
	public void addGprofile(GTProfile gprofile) {
		gtprofileDAO.save(gprofile);
	}

	@Override
	public List<GTProfile> getAllGprofiles() {

		return gtprofileDAO.findAllGprofiles();
	}

	@Override
	public GTProfile getGprofileById(Integer id) {

		return gtprofileDAO.getGprofileById(id);
	}

	@Override
	public GTProfile deleteGroupProfileById(Integer id) {
		return gtprofileDAO.deleteGprofileById(id);
	}

	@Override
	public GTProfile updateGprofile(Integer id, GTProfile gprofile) {

		return gtprofileDAO.updateGprofile(id, gprofile);
	}

}
