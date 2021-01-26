package com.ey.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ey.core.dao.UprofileDAO;
import com.ey.core.entity.PageCycle;
import com.ey.core.entity.UProfile;
import com.ey.core.util.MessageUtil;
import com.ey.core.util.ValidationErrorException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UprofileServiceImpl implements UprofileService {

	@Autowired
	private UprofileDAO uprofileDAO;

	@Override
	public void addUserProfile(UProfile uprofile) {
		log.debug(" Add UserProfile Service ");

		if (uprofile.getAgeoffTimer() == null)
			uprofile.setAgeoffTimer(3630);
		if (uprofile.getRegistrationTimer() == null)
			uprofile.setRegistrationTimer(3600);
		if (uprofile.getIsOpenEnterprise() == null)
			uprofile.setIsOpenEnterprise(true);
		if (uprofile.getIsConsole() == null)
			uprofile.setIsConsole(true);
		if (uprofile.getIsSpoolingEnabled() == null)
			uprofile.setIsSpoolingEnabled(true);
		if (uprofile.getLtePagingCycle() == null)
			uprofile.setLtePagingCycle(PageCycle.REDUCED2);

		ageoffTimerValidation(uprofile.getAgeoffTimer(), uprofile.getRegistrationTimer());
		uprofileDAO.save(uprofile);
	}

	private void ageoffTimerValidation(Integer ageoffTimer, Integer registrationTimer) {

		if (ageoffTimer < registrationTimer)
			throw new ValidationErrorException(MessageUtil.AGE_TIMER_GRATER_MSG);

	}

	@Override
	public void updateUserProfile(UProfile uprofile, Integer id) {
		log.debug(" Update UserProfile Service ");

		ageoffTimerValidation(uprofile.getAgeoffTimer(), uprofile.getRegistrationTimer());
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
	public List<UProfile> getAllCustomers(Pageable pageable) {
		log.debug(" GetAll UserProfile Service ");
		return uprofileDAO.getAll(pageable);
	}

}
