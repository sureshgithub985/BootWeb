package com.ey.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.entity.UProfile;

public interface UserProfileRepo extends JpaRepository<UProfile, Integer> {

}
