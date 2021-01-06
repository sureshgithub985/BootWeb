package com.ey.core.dao;

import java.util.List;

import com.ey.core.entity.EntGroup;

public interface EntGroupDAO {

	
	public void createEntGroup(EntGroup entGroup);

	public List<EntGroup> getAllEntGroupDAO();

	public EntGroup findByByName(String name);

	public EntGroup deleteByName(String name);

	public EntGroup updateEntGroup(String name, EntGroup entGroup);
	
}
