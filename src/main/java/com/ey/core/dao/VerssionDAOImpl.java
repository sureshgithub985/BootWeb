package com.ey.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.entity.Version;

@Repository
public class VerssionDAOImpl implements VerssionDAO {

	@Autowired
	private VersionRepository versionRepository;

	@Override
	public Version getversion() {

		List<Version> versionList = versionRepository.findAll();

		if (!versionList.isEmpty() && versionList.size() == 1) {
			return versionList.get(0);
		} else
			return null;
	}

}
