package com.ey.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.core.dao.EnterpriseDAO;
import com.ey.core.entity.Enterprise;
import com.ey.core.util.MessageUtil;
import com.ey.core.util.ValidationErrorException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	private EnterpriseDAO entDAO;

	@Override
	public void addEnterprise(Enterprise ent) {
		log.debug(" Create Enterprise Service ");

		entDAO.createEnt(ent);

	}

	@Override
	public List<Enterprise> getAllEnterprises() {
		log.debug(" GETALL Enterprise Service ");

		return entDAO.getAllEnterpriseDAO();
	}

	@Override
	public Enterprise getEnterpriseByName(String name) {
		log.debug(" GET Enterprise Service ");

		return entDAO.findByEntName(name);
	}

	@Override
	public Enterprise deleteEnterpriseByName(String name) {
		log.debug(" Delete Enterprise Service ");

		return entDAO.deleteByName(name);
	}

	@Override
	public Enterprise updateEnterprise(String name, Enterprise ent) {
		log.debug(" Update Enterprise Service ");

		// ckr and ckrList validations
		String ckrList = ent.getCkrList();
		Integer ckr = ent.getCkr();

		if ((ckrList != null && ckr == null) || (ckrList == null && ckr != null)) {
			throw new ValidationErrorException(MessageUtil.BOTH_CKR_CKRLIST_REQUIRED);
		}

		return entDAO.updateEnterpise(name, ent);
	}

}
