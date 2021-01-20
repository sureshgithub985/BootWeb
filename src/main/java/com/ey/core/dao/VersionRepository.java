package com.ey.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.entity.Version;

public interface VersionRepository extends JpaRepository<Version, Double> {

}
