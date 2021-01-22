package com.ey.core.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.entity.UProfile;

public interface UserProfileRepo extends JpaRepository<UProfile, Integer> {

	public Optional<UProfile> findByName(String name);

}
