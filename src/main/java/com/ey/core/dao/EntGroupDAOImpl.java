package com.ey.core.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.model.EntGroup;
import com.ey.core.model.Enterprise;
import com.ey.core.util.MessageUtil;
import com.ey.core.util.ValidationErrorException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Repository
public class EntGroupDAOImpl implements EntGroupDAO {

	@Autowired
	private EntGroupRepository entGroupRepository;

	@Autowired
	private EnterpriseRepository entRepository;

	@Override
	public void createEntGroup(EntGroup egroup) {

		log.debug(" Create Enterprise Group ");

		validateEnterpriseAndckrcheck(egroup);
		if (egroup.getCkr() == null)
			egroup.setCkr(0);

		EntGroup entGroup = entGroupRepository.findByName(egroup.getName());

		if (entGroup == null) {
			Serializable save = entGroupRepository.save(egroup);
			log.debug("save value is " + save);
		} else
			throw new ValidationErrorException(MessageUtil.NAME_ALREADY_EXISTS);
	}

	private void validateEnterpriseAndckrcheck(EntGroup egroup) {

		Enterprise ent = entRepository.findByName(egroup.getEnterpriseName());
		if (ent == null)
			throw new ValidationErrorException(MessageUtil.ENTERPRISE_NAME_DOESNOT_EXISTS);
		else {
			if (egroup.getCkr() != null) {
				if (egroup.getCkr() <= 1 || egroup.getCkr() > 999)
					throw new ValidationErrorException(MessageUtil.CKR_INVALID);
				if (ent.getCkr() == 0)
					throw new ValidationErrorException(MessageUtil.CKR_CANNOT_BE_APPLIED_TO_NON_SECURE_ENTERPRISE);
			}

		}

	}

	@Override
	public List<EntGroup> getAllEntGroupDAO() {

		log.debug(" GETALL Enterprise Group ");

		return entGroupRepository.findAll();
	}

	@Override
	public EntGroup findByByName(String name) {

		log.debug(" GET Enterprise Group ");

		EntGroup entGroup = entGroupRepository.findByName(name);
		if (entGroup == null)
			throw new ValidationErrorException(MessageUtil.NAME_DOESNOT_EXISTS);

		return entGroup;
	}

	@Override
	public EntGroup deleteByName(String name) {

		log.debug(" Delete Enterprise Group ");

		EntGroup entGroup = entGroupRepository.findByName(name);
		if (entGroup != null)
			entGroupRepository.delete(entGroup);
		else
			throw new ValidationErrorException(MessageUtil.NAME_DOESNOT_EXISTS);

		return null;
	}

	@Override
	public EntGroup updateEntGroup(String name, EntGroup entGroup) {
		log.debug(" Update Enterprise Group ");

		EntGroup entGroup1 = entGroupRepository.findByName(name);

		log.info("Old Enterprise content is " + entGroup1);
		if (entGroup1 == null)
			throw new ValidationErrorException(MessageUtil.NAME_DOESNOT_EXISTS);

		if (!entGroup.getName().equals(entGroup1.getName()) && entGroup.getName() != null) {
			EntGroup entGroup2 = entGroupRepository.findByName(entGroup.getName());

			if (entGroup2 != null)
				throw new ValidationErrorException(MessageUtil.NAME_ALREADY_EXISTS);
		}

		entGroup1.setName(entGroup.getName());
		entGroup1.setCkr(entGroup.getCkr());
		entGroup1.setEnterpriseName(entGroup.getEnterpriseName());
		entGroup1.setGroupType(entGroup.getGroupType());
		entGroup1.setSgid(entGroup.getSgid());

		return entGroupRepository.save(entGroup1);
	}

}
