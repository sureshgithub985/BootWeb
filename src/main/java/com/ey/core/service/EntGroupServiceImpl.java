package com.ey.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.core.dao.EntGroupDAO;
import com.ey.core.model.EntGroup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EntGroupServiceImpl implements EntGroupService {

	@Autowired
	private EntGroupDAO entGroupDao;

	@Override
	public void addEntGroup(EntGroup egroup) {

		log.debug(" Create Enterprise Group Service ");

		entGroupDao.createEntGroup(egroup);

	}

	@Override
	public List<EntGroup> getAllEntGroups() {

		log.debug(" GETALL Enterprise Group Service ");

		return entGroupDao.getAllEntGroupDAO();
	}

	@Override
	public EntGroup getEntGroupByName(String name) {

		log.debug(" GET Enterprise Group Service ");

		return entGroupDao.findByByName(name);
	}

	@Override
	public EntGroup deleteEntGroupByName(String name) {

		log.debug(" Delete Enterprise Group Service ");

		return entGroupDao.deleteByName(name);
	}

	@Override
	public EntGroup updateEntGroup(String name, EntGroup entGroup) {

		log.debug(" Update Enterprise Group Service ");

		return entGroupDao.updateEntGroup(name, entGroup);
	}

}
