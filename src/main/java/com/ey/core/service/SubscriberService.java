package com.ey.core.service;

import java.util.List;

import com.ey.core.entity.Subscriber;

public interface SubscriberService {

	public void addSubscriber(Subscriber sub);

	public List<Subscriber> getAllSubscribers();

	public Subscriber getSubscriber(Long mdn);

	public Subscriber deleteSubscriber(Long mdn);

	public Subscriber updateSusbciber(Long mdn, Subscriber sub);

}
