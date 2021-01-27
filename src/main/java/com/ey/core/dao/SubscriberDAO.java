package com.ey.core.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ey.core.entity.Subscriber;

public interface SubscriberDAO {

	public void createSub(Subscriber sub);

	public List<Subscriber> getAllSubscribersDAO(Pageable pageable);

	public Subscriber getSubscriber(Long mdn);

	public void deleteSubscriber(Long mdn);

	public void updateSubscriber(Long mdn, Subscriber sub);

}
