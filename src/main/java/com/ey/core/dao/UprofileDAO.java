package com.ey.core.dao;

import java.util.List;

import com.ey.core.entity.UProfile;

public interface UprofileDAO {

	void save(UProfile uprofile);

	void update(UProfile uprofile, Integer id);

	UProfile get(Integer id);

	List<UProfile> getAll();

	void delete(Integer id);

}
