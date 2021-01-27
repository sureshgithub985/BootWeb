package com.ey.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ey.core.dao.SubscriberDAO;
import com.ey.core.entity.Subscriber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriberServiceImpl implements SubscriberService {

	@Autowired
	private SubscriberDAO subDao;

	@Override
	public void addSubscriber(Subscriber sub) {

		log.debug("Create Subscriber Service ");
		subDao.createSub(sub);
	}

	@Override
	public List<Subscriber> getAllSubscribers(Pageable pageable) {
		
		log.debug("Update Subscriber Service ");
		return subDao.getAllSubscribersDAO(pageable);
	}

	@Override
	public Subscriber getSubscriber(Long mdn) {

		log.debug("GET Subscriber Service ");
		return subDao.getSubscriber(mdn);
	}

	@Override
	public void deleteSubscriber(Long mdn) {

		log.debug("Delete Subscriber Service ");
		subDao.deleteSubscriber(mdn);
	}

	@Override
	public void updateSusbciber(Long mdn, Subscriber sub) {

		log.debug("Update Subscriber Service ");
		subDao.updateSubscriber(mdn, sub);
	}

}
