package com.ey.core.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ey.core.entity.UProfile;

public interface UserProfileRepo
		extends JpaRepository<UProfile, Integer>, PagingAndSortingRepository<UProfile, Integer> {

	public Optional<UProfile> findByName(String name);

}
