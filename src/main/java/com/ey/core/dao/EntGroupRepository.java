package com.ey.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.core.entity.EntGroup;

public interface EntGroupRepository extends JpaRepository<EntGroup, String> {

	public EntGroup findByName(String name);
}
