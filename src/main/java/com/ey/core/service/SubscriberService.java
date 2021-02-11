package com.ey.core.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ey.core.entity.Subscriber;

public interface SubscriberService {

	public void addSubscriber(Subscriber sub);

	public List<Subscriber> getAllSubscribers(Pageable pageable, String enterprise);

	public Subscriber getSubscriber(Long mdn);

	public void deleteSubscriber(Long mdn);

	public void updateSusbciber(Long mdn, Subscriber sub);

}
