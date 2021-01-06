package com.ey.core.dao;

import java.util.List;

import com.ey.core.model.GTProfile;

public interface GTProfileDAO {

	public void save(GTProfile gprofile);

	public List<GTProfile> findAllGprofiles();

	public GTProfile getGprofileById(Integer id);

	public void deleteGprofileById(Integer id);

	public void updateGprofile(Integer id, GTProfile gprofile);

}
