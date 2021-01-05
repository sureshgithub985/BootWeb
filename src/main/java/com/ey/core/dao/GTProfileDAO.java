package com.ey.core.dao;

import java.util.List;

import com.ey.core.model.GTProfile;

public interface GTProfileDAO {

	public void save(GTProfile gprofile);

	public List<GTProfile> findAllGprofiles();

	public GTProfile getGprofileById(Integer id);

	public GTProfile deleteGprofileById(Integer id);

	public GTProfile updateGprofile(Integer id, GTProfile gprofile);

}
