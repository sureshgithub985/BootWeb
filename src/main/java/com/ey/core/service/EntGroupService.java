
package com.ey.core.service;

import java.util.List;

import com.ey.core.entity.EntGroup;

public interface EntGroupService {

	public void addEntGroup(EntGroup ent);

	public List<EntGroup> getAllEntGroups();

	public EntGroup getEntGroupByName(String name);

	public EntGroup deleteEntGroupByName(String name);

	public EntGroup updateEntGroup(String name, EntGroup entGroup);

}
