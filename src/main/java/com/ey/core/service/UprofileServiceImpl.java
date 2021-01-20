package com.ey.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.core.dao.UprofileDAO;
import com.ey.core.entity.UProfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UprofileServiceImpl implements UprofileService {

	@Autowired
	private UprofileDAO uprofileDAO;

	@Override
	public void addUserProfile(UProfile uprofile) {
		log.debug(" Add UserProfile Service ");
		uprofileDAO.save(uprofile);
	}

	@Override
	public void updateUserProfile(UProfile uprofile, Integer id) {
		log.debug(" Update UserProfile Service ");
		uprofileDAO.update(uprofile, id);

	}

	@Override
	public UProfile getUserProfile(Integer id) {
		log.debug(" Get UserProfile Service ");
		return uprofileDAO.get(id);
	}

	@Override
	public void deleteUserProfileById(Integer id) {
		log.debug(" Delete UserProfile Service ");
		uprofileDAO.delete(id);
	}

	@Override
	public List<UProfile> getAllCustomers() {
		log.debug(" GetAll UserProfile Service ");
		return uprofileDAO.getAll();
	}

}
