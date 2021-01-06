package com.ey.core.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.entity.Subscriber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Repository
public class SubscriberDAOImpl implements SubscriberDAO {

	@Autowired
	private SubscriberRepository subRepository;

	@Override
	public void createSub(Subscriber sub) {

		log.debug(" Create Subscriber DAO ");

		subRepository.save(sub);
	}

	@Override
	public List<Subscriber> getAllSubscribersDAO() {

		log.debug(" GETALL Subscriber DAO ");

		return subRepository.findAll();
	}

	@Override
	public Subscriber updateSubscriber(Long mdn, Subscriber sub) {

		log.debug(" Update Subscriber DAO ");
		return null;
	}

	@Override
	public Subscriber getSubscriber(Long mdn) {

		log.debug(" GET Subscriber DAO ");

		return null;
	}

	@Override
	public Subscriber deleteSubscriber(Long mdn) {

		log.debug(" Delete Subscriber DAO ");

		subRepository.deleteById(mdn);

		return null;
	}

}
