package com.ey.core.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ey.core.entity.UProfile;

public interface UprofileDAO {

	void save(UProfile uprofile);

	void update(UProfile uprofile, Integer id);

	UProfile get(Integer id);

	List<UProfile> getAll(Pageable pageable);

	void delete(Integer id);

}
