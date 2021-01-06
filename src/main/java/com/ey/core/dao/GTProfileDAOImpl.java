package com.ey.core.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ey.core.model.GTProfile;
import com.ey.core.util.ResourceNotFoundException;
import com.ey.core.util.ValidationErrorException;

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

		groupProfileIdCheck(gprofile.getId(), 0);
		groupProfileNameCheck(gprofile.getName());

		gprofileRepo.save(gprofile);
	}

	private void groupProfileNameCheck(String name) {

		Optional<GTProfile> opGprofileNameExists = gprofileRepo.findByName(name);
		if (opGprofileNameExists.isPresent())
			throw new ValidationErrorException("Profile Name already exists");

	}

	private GTProfile groupProfileIdCheck(Integer id, int op) {

		Optional<GTProfile> opGprofileIdExists = gprofileRepo.findById(id);
		if (opGprofileIdExists.isPresent() && op == 0)
			throw new ValidationErrorException("Profile ID already exists");
		else if (!opGprofileIdExists.isPresent() && op == 1)
			throw new ResourceNotFoundException("Cannot find object with given id");

		return opGprofileIdExists.isPresent() ? opGprofileIdExists.get() : null;

	}

	@Override
	public List<GTProfile> findAllGprofiles() {
		log.debug("GETALL GTPrrofile DAO  ");

		return gprofileRepo.findAll();
	}

	@Override
	public GTProfile getGprofileById(Integer id) {
		log.debug("GET GTPrrofile DAO  ");

		GTProfile opGprofile = groupProfileIdCheck(id, 1);

		return opGprofile;
	}

	@Override
	public void deleteGprofileById(Integer id) {
		log.debug("Delete GTPrrofile DAO  ");

		groupProfileIdCheck(id, 1);

		gprofileRepo.deleteById(id);
	}

	@Override
	public void updateGprofile(Integer id, GTProfile gprofile) {
		log.debug("Update GTPrrofile DAO  ");

		GTProfile oldGFprofile = groupProfileIdCheck(id, 1);

		Optional<GTProfile> opGprofileNameExists = gprofileRepo.findByName(gprofile.getName());
		if (opGprofileNameExists.isPresent() && !oldGFprofile.getName().equals(gprofile.getName()))
			throw new ValidationErrorException("Profile Name already exists");

		if (gprofile.getName() != null)
			oldGFprofile.setName(gprofile.getName());
		if (gprofile.getCallSetupPriority() != null)
			oldGFprofile.setCallSetupPriority(gprofile.getCallSetupPriority());
		if (gprofile.getIsCallSetupPreemptionEnabled() != null)
			oldGFprofile.setIsCallSetupPreemptionEnabled(gprofile.getIsCallSetupPreemptionEnabled());

		gprofileRepo.save(oldGFprofile);
	}

}
