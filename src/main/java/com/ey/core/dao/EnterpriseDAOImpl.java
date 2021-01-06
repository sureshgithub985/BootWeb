package com.ey.core.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ey.core.model.Enterprise;
import com.ey.core.util.MessageUtil;
import com.ey.core.util.ValidationErrorException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class EnterpriseDAOImpl implements EnterpriseDAO {

	@Autowired
	private EnterpriseRepository entRepo;

	@Override
	public void createEnt(Enterprise ent) {
		log.debug(" Create Enterprise DAO " + ent);

		String ckrList = ent.getCkrList();
		Integer ckr = ent.getCkr();
		log.info("ckrList value is  " + ckrList + "\n " + "ckr value is " + ckr);

		if (ckrList == null && ckr == null) {
			ent.setCkr(0);
			ent.setCkrList("");
		}

		// ValidateCkr and CkrList
		validateCkrAndCkrList(ckr, ckrList);

		Enterprise enterprise = entRepo.findByName(ent.getName());

		if (enterprise == null) {
			Serializable save = entRepo.save(ent);
			log.debug("save value is " + save);
		} else
			throw new ValidationErrorException(MessageUtil.ENTERPRISE_NAME_ALREADY_EXISTS);

	}

	private void validateCkrAndCkrList(Integer ckr, String ckrList) {

		if (ckrList != null && ckr != null) {

			if (ckr <= 0 || ckr > 999)
				throw new ValidationErrorException(MessageUtil.CKR_INVALID);

			String[] ckrArr = ckrList.split(",");

			if (ckrArr.length >= 10)
				throw new ValidationErrorException(MessageUtil.CKRLIST_INLVALD_SIZE);

			Set<Integer> set = new HashSet<>();
			for (String str : ckrArr) {
				if (!set.add(Integer.parseInt(str)))
					throw new ValidationErrorException(MessageUtil.DUPLICATE_CKR);
			}

			if (!set.contains(ckr))
				throw new ValidationErrorException(MessageUtil.CKR_NOT_IN_CKRLIST);
		} else if (ckrList == null && ckr == null) {
			log.debug("Both are Null, by-passing this request");
		} else
			throw new ValidationErrorException(MessageUtil.BOTH_CKR_CKRLIST_REQUIRED);

	}

	@Override
	public List<Enterprise> getAllEnterpriseDAO() {
		log.debug(" GetAll Enterprise DAO ");

		return entRepo.findAll();
	}

	@Override
	public Enterprise findByEntName(String name) {
		log.debug(" GET Enterprise DAO " + name);

		Enterprise ent = entRepo.findByName(name);
		if (ent == null)
			throw new ValidationErrorException(MessageUtil.ENTERPRISE_NAME_DOESNOT_EXISTS);

		return ent;
	}

	@Override
	public Enterprise deleteByName(String name) {
		log.debug("Delete Enterprise DAO " + name);

		Enterprise ent1 = entRepo.findByName(name);
		if (ent1 != null)
			entRepo.delete(ent1);
		else
			throw new ValidationErrorException(MessageUtil.ENTERPRISE_NAME_DOESNOT_EXISTS);

		return null;

	}

	@Override
	public Enterprise updateEnterpise(String name, Enterprise ent) {
		log.debug("Update Enterprise DAO " + name + "\n " + "Enterprise content " + ent);

		Enterprise ent1 = entRepo.findByName(name);

		log.info("Old Enterprise content is " + ent1);
		if (ent1 == null)
			throw new ValidationErrorException(MessageUtil.ENTERPRISE_NAME_DOESNOT_EXISTS);

		String ckrList = ent.getCkrList();
		Integer ckr = ent.getCkr();

		if (ckrList != null && ckr != null) {
			if (!ent1.getCkrList().equals("") && ent1.getCkr() != 0)
				ckrList = ent1.getCkrList().concat(",").concat(ckrList);

			// ValidateCkr and CkrList
			validateCkrAndCkrList(ckr, ckrList);
		} else if (ckrList == null && ckr == null) {
			if (!ent1.getCkrList().equals("") && ent1.getCkr() != 0) {
				ckrList = ent1.getCkrList();
				ckr = ent1.getCkr();
			} else {
				ckrList = "";
				ckr = 0;
			}
		}
		log.info("ckrList value is  " + ckrList + "\n " + "ckr value is " + ckr);

		if (!ent.getName().equals(ent1.getName()) && ent.getName() != null) {
			Enterprise enterprise = entRepo.findByName(ent.getName());

			if (enterprise != null)
				throw new ValidationErrorException(MessageUtil.ENTERPRISE_NAME_ALREADY_EXISTS);
		}

		ent1.setCkrList(ckrList);
		ent1.setCkr(ckr);
		ent1.setName(ent.getName());
		ent1.setCompany(ent.getCompany());
		ent1.setAddressLine1(ent.getAddressLine1());
		ent1.setAddressLine2(ent.getAddressLine2());
		ent1.setZipCode(ent.getZipCode());

		return entRepo.save(ent1);

	}

}
