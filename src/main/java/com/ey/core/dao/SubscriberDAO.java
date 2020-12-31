package com.ey.core.dao;

import java.util.List;

import com.ey.core.model.Subscriber;

public interface SubscriberDAO {

	public void createSub(Subscriber sub);

	public List<Subscriber> getAllSubscribersDAO();

	public Subscriber getSubscriber(Long mdn);

	public Subscriber deleteSubscriber(Long mdn);

	public Subscriber updateSubscriber(Long mdn, Subscriber sub);

}
