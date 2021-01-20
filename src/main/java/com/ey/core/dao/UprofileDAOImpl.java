package com.ey.core.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ey.core.entity.UProfile;
import com.ey.core.util.MessageUtil;
import com.ey.core.util.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Repository
@Slf4j
public class UprofileDAOImpl implements UprofileDAO {

	@Autowired
	private UserProfileRepo userprofileRepo;

	@Override
	public void save(UProfile uprofile) {
		log.debug(" Add UserProfile DAO ");
		userprofileRepo.save(uprofile);
	}

	@Override
	public void update(UProfile uprofile, Integer id) {
		log.debug(" Update UserProfile DAO ");
		Optional<UProfile> opuprofile = userprofileRepo.findById(id);
		if (!opuprofile.isPresent())
			throw new ResourceNotFoundException(MessageUtil.NOT_FOUND_MSG);

		userprofileRepo.save(uprofile);
	}

	@Override
	public UProfile get(Integer id) {
		log.debug(" Get UserProfile DAO ");
		Optional<UProfile> opuprofile = userprofileRepo.findById(id);
		if (!opuprofile.isPresent())
			throw new ResourceNotFoundException(MessageUtil.NOT_FOUND_MSG);
		else
			return opuprofile.get();
	}

	@Override
	public List<UProfile> getAll() {
		log.debug(" GetAll UserProfile DAO ");
		return userprofileRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		log.debug(" Delete UserProfile DAO ");

		Optional<UProfile> opuprofile = userprofileRepo.findById(id);
		if (!opuprofile.isPresent())
			throw new ResourceNotFoundException(MessageUtil.NOT_FOUND_MSG);

		userprofileRepo.deleteById(id);
	}

}
