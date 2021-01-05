package com.ey.core.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ey.core.model.GTProfile;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@Repository
public class GTProfileDAOImpl implements GTProfileDAO {

	@Autowired
	private GTProfileRepository gprofileRepo;

	@Override
	public void save(GTProfile gprofile) {
		log.debug("Create GTPrrofile DAO  " + gprofile);
		gprofileRepo.save(gprofile);
	}

	@Override
	public List<GTProfile> findAllGprofiles() {
		log.debug("GETALL GTPrrofile DAO  ");
		return gprofileRepo.findAll();
	}

	@Override
	public GTProfile getGprofileById(Integer id) {
		log.debug("GET GTPrrofile DAO  ");
		Optional<GTProfile> opGprofile = gprofileRepo.findById(id);
		return opGprofile.isPresent() ? opGprofile.get() : null;
	}

	@Override
	public GTProfile deleteGprofileById(Integer id) {
		log.debug("Delete GTPrrofile DAO  ");
		gprofileRepo.deleteById(id);
		return null;
	}

	@Override
	public GTProfile updateGprofile(Integer id, GTProfile gprofile) {
		log.debug("Update GTPrrofile DAO  ");
		gprofileRepo.save(null);

		return null;
	}

}
