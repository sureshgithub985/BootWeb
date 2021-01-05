package com.ey.core.service;

import java.util.List;

import com.ey.core.model.GTProfile;

public interface GTProfileService {

	public void addGprofile(GTProfile gprofile);

	public List<GTProfile> getAllGprofiles();

	public GTProfile getGprofileById(Integer id);

	public GTProfile deleteGroupProfileById(Integer id);

	public GTProfile updateGprofile(Integer id, GTProfile cust);

}
