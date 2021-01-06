package com.ey.core.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.model.GTProfile;

public interface GTProfileRepository extends JpaRepository<GTProfile, Integer> {

	public Optional<GTProfile> findByName(String name);

}
