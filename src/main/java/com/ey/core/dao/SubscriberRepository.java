package com.ey.core.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {


}
