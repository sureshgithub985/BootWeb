package com.ey.core.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ey.core.entity.UProfile;
import com.ey.core.util.MessageUtil;
import com.ey.core.util.ResourceNotFoundException;
import com.ey.core.util.ValidationErrorException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UprofileDAOImpl implements UprofileDAO {

	@Autowired
	private UserProfileRepo userprofileRepo;

	@Override
	public void save(UProfile uprofile) {
		log.debug(" Add UserProfile DAO ");

		profileIdExistsCheck(uprofile.getId(), 0);
		profileNameExistsCheck(uprofile.getName());

		userprofileRepo.save(uprofile);
	}

	@Override
	public void update(UProfile uprofile, Integer id) {
		log.debug(" Update UserProfile DAO ");

		UProfile uprofileId = profileIdExistsCheck(uprofile.getId(), 1);
		if (uprofileId != null) {
			if (uprofile.getName() != null && !uprofileId.getName().equals(uprofile.getName())) {
				profileNameExistsCheck(uprofile.getName());
				uprofileId.setName(uprofile.getName());
			}

			if (uprofile.getAgeoffTimer() != null)
				uprofileId.setAgeoffTimer(uprofile.getAgeoffTimer());
			if (uprofile.getRegistrationTimer() != null)
				uprofileId.setRegistrationTimer(uprofile.getRegistrationTimer());
			if (uprofile.getIsConsole() != null)
				uprofileId.setIsConsole(uprofile.getIsConsole());
			if (uprofile.getIsOpenEnterprise() != null)
				uprofileId.setIsOpenEnterprise(uprofile.getIsOpenEnterprise());
			if (uprofile.getIsSpoolingEnabled() != null)
				uprofileId.setIsSpoolingEnabled(uprofile.getIsSpoolingEnabled());

			userprofileRepo.save(uprofileId);
		}
	}

	private void profileNameExistsCheck(String name) {

		Optional<UProfile> opNameuprofile = userprofileRepo.findByName(name);
		if (opNameuprofile.isPresent())
			throw new ValidationErrorException(MessageUtil.PROFILE_NAME_EXISTS);

	}

	private UProfile profileIdExistsCheck(Integer id, int op) {

		Optional<UProfile> opIduprofile = userprofileRepo.findById(id);

		if (opIduprofile.isPresent() && op == 0)
			throw new ValidationErrorException(MessageUtil.PROFILE_ID_EXISTS);
		else if (!opIduprofile.isPresent() && op == 1)
			throw new ResourceNotFoundException(MessageUtil.NOT_FOUND_MSG);

		return opIduprofile.isPresent() ? opIduprofile.get() : null;

	}

	@Override
	public UProfile get(Integer id) {

		log.debug(" Get UserProfile DAO ");

		return profileIdExistsCheck(id, 1);
	}

	@Override
	public List<UProfile> getAll(Pageable pageable) {

		log.debug(" GetAll UserProfile DAO ");

		return userprofileRepo.findAll(pageable).getContent();
	}

	@Override
	public void delete(Integer id) {

		log.debug(" Delete UserProfile DAO ");

		profileIdExistsCheck(id, 1);
		userprofileRepo.deleteById(id);
	}

}
