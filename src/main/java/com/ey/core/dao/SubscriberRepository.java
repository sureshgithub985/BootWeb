package com.ey.core.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ey.core.entity.Subscriber;

public interface SubscriberRepository
		extends JpaRepository<Subscriber, Long>, PagingAndSortingRepository<Subscriber, Long> {

	public Subscriber findByMdn(Long mdn);

	public List<Subscriber> findAllByEnterpriseName(String ent, Pageable pageable);

}
