package com.ey.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ey.core.entity.Subscriber;

public interface SubscriberRepository
		extends JpaRepository<Subscriber, Long>, PagingAndSortingRepository<Subscriber, Long> {

	public Subscriber findByMdn(Long mdn);

}
