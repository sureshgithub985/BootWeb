package com.ey.core.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ey.core.entity.Enterprise;
import com.ey.core.entity.Subscriber;
import com.ey.core.util.MessageUtil;
import com.ey.core.util.ResourceNotFoundException;
import com.ey.core.util.ValidationErrorException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Repository
public class SubscriberDAOImpl implements SubscriberDAO {

	@Autowired
	private SubscriberRepository subRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private EnterpriseDAO enterpriseDAO;

	@Override
	public void createSub(Subscriber sub) {

		log.debug(" Create Subscriber DAO ");
		if (sub.getSuspended() != null)
			throw new ValidationErrorException(MessageUtil.SUSPENEDED_NOT_ALLOWED);
		else
			sub.setSuspended(false);

		if (sub.getIsSecure() == null)
			sub.setIsSecure(false);

		mdnDatabaseExistscheck(sub.getMdn());
		minDatabaseExistscheck(sub.getMin());
		ufmiDatabaseExistscheck(sub.getUfmi());
		cosDatabaseExistscheck(sub.getCos());
		int enterpriseId = enterpriseNameDatabaseExistscheck(sub.getEnterpriseName(), sub.getIsSecure());

		entityManager.createNativeQuery("insert into whlr_mdn(mdn) values (:mdn)").setParameter("mdn", sub.getMdn())
				.executeUpdate();

		Object singleResult = entityManager.createNativeQuery("select id from whlr_mdn where mdn=:mdn")
				.setParameter("mdn", sub.getMdn()).getSingleResult();
		int mdn_id = (Integer) singleResult;

		entityManager.createNativeQuery("insert into shared_contacts(mdn_id, user_name) values (:mdn_id,:user_name)")
				.setParameter("mdn_id", mdn_id).setParameter("user_name", sub.getUserName()).executeUpdate();

		int executeUpdate = entityManager.createNativeQuery(
				"insert into whlr_subscribers_info(mdn_id, cos, email, enterprise_id, is_secure, min, suspended, ufmi) values (:mdn_id,:cos,:email,:enterprise_id,:is_secure,:min,:suspended,:ufmi)")
				.setParameter("mdn_id", mdn_id).setParameter("cos", sub.getCos()).setParameter("email", sub.getEmail())
				.setParameter("enterprise_id", enterpriseId).setParameter("is_secure", sub.getIsSecure())
				.setParameter("min", sub.getMin()).setParameter("suspended", sub.getSuspended())
				.setParameter("ufmi", sub.getUfmi()).executeUpdate();

		System.out.println("executeUpdate value is " + executeUpdate);

	}

	private int enterpriseNameDatabaseExistscheck(String enterpriseName, Boolean isSecure) {

		Enterprise ent = enterpriseDAO.findByEntName(enterpriseName);
		if (ent != null) {
			int ckr = ent.getCkr();
			if (ckr == 0 && isSecure)
				throw new ValidationErrorException(MessageUtil.SECURE_SUB_CANNOT_BE_ADDED_TO_NONSECURE_ENT);
			return ent.getId();
		} else {
			throw new ValidationErrorException(MessageUtil.ENTERPRISE_NAME_DOESNOT_EXISTS);
		}

	}

	private void cosDatabaseExistscheck(Integer id) {

		Object singleResult = entityManager.createNativeQuery("select count(*) from wms_user_profile where id=:id")
				.setParameter("id", id).getSingleResult();
		BigInteger cosId = (BigInteger) singleResult;
		if (cosId.signum() == 0)
			throw new ValidationErrorException(MessageUtil.COS_DOESNOT_EXISTS);

	}

	private void ufmiDatabaseExistscheck(String ufmi) {
		Object singleResult = entityManager
				.createNativeQuery("select count(*) from whlr_subscribers_info where ufmi=:ufmi")
				.setParameter("ufmi", ufmi).getSingleResult();
		BigInteger ufmiId = (BigInteger) singleResult;
		if (ufmiId.signum() != 0)
			throw new ValidationErrorException(MessageUtil.UFMI_ALREADY_EXISTS);

	}

	private void minDatabaseExistscheck(Long min) {

		Object singleResult = entityManager
				.createNativeQuery("select count(*) from whlr_subscribers_info where min=:min").setParameter("min", min)
				.getSingleResult();
		BigInteger minId = (BigInteger) singleResult;
		if (minId.signum() != 0)
			throw new ValidationErrorException(MessageUtil.MIN_ALREADY_EXISTS);

	}

	private void mdnDatabaseExistscheck(Long mdn) {

		Object singleResult = entityManager.createNativeQuery("select count(*) from whlr_mdn where mdn=:mdn")
				.setParameter("mdn", mdn).getSingleResult();
		BigInteger mdnId = (BigInteger) singleResult;
		System.out.println(" mdnId.signum() value is  " + mdnId.signum());
		if (mdnId.signum() != 0)
			throw new ValidationErrorException(MessageUtil.MDN_ALREADY_EXISTS);
	}

	@Override
	public List<Subscriber> getAllSubscribersDAO(Pageable pageable) {

		log.debug(" GETALL Subscriber DAO ");
		return subRepository.findAll(pageable).getContent();
	}

	@Override
	public void updateSubscriber(Long mdn, Subscriber sub) {

		log.debug(" Update Subscriber DAO ");
		log.info("Subscriber Suspended value is " + sub.getSuspended());

		Subscriber oldSub = subscriberExistcheck(mdn);

		if (oldSub == null) {
			throw new ResourceNotFoundException(MessageUtil.NOT_FOUND_MSG);
		} else {

			if (sub.getSuspended() != null) {
				int count = entityManager.createNativeQuery(
						"update whlr_subscribers_info set suspended =:suspended where mdn_id = (select id from whlr_mdn where mdn=:mdn)")
						.setParameter("mdn", mdn).setParameter("suspended", sub.getSuspended()).executeUpdate();

				if (count == 1)
					log.debug("Subscriber suspend/unsuspend Updated Succesfully ");
				else
					log.debug("Failed to Update the Subscriber");
			} else {
				if (sub.getEnterpriseName() != null && !sub.getEnterpriseName().equals(oldSub.getEnterpriseName()))
					throw new ValidationErrorException(MessageUtil.ENTERPRISE_NAME_CANNOT_BE_MODIFIED);

				if (oldSub.getSuspended())
					throw new ValidationErrorException(MessageUtil.CANNOT_UPDATE_SUSPENDED_SUBSCRIBER);

				if (sub.getMdn() != null && !sub.getMdn().equals(oldSub.getMdn())) {
					mdnDatabaseExistscheck(sub.getMdn());

					entityManager.createNativeQuery("update whlr_mdn set mdn =:mdn1 where mdn =:mdn2")
							.setParameter("mdn1", sub.getMdn()).setParameter("mdn2", oldSub.getMdn()).executeUpdate();
				}
				if (sub.getMin() != null && !sub.getMin().equals(oldSub.getMin()))
					minDatabaseExistscheck(sub.getMin());
				else
					sub.setMin(oldSub.getMin());

				if (sub.getUfmi() != null && !sub.getUfmi().equals(oldSub.getUfmi()))
					ufmiDatabaseExistscheck(sub.getUfmi());
				else
					sub.setUfmi(oldSub.getUfmi());

				if (sub.getCos() != null && !sub.getCos().equals(oldSub.getCos()))
					cosDatabaseExistscheck(sub.getCos());
				else
					sub.setCos(oldSub.getCos());

				if (sub.getIsSecure() != null && !sub.getIsSecure().equals(oldSub.getIsSecure()) && sub.getIsSecure())
					enterpriseNameDatabaseExistscheck(oldSub.getEnterpriseName(), sub.getIsSecure());
				else
					sub.setIsSecure(oldSub.getIsSecure());

				if (sub.getEmail() == null)
					sub.setEmail(oldSub.getEmail());

				if (sub.getUserName() != null && !sub.getUserName().equals(oldSub.getUserName())) {
					entityManager.createNativeQuery(
							"update shared_contacts set user_name =:userName where mdn_id = (select id from whlr_mdn where mdn=:mdn)")
							.setParameter("userName", sub.getUserName()).setParameter("mdn", sub.getMdn())
							.executeUpdate();
				}

				entityManager.createNativeQuery(
						"update whlr_subscribers_info set cos =:cos,email=:email,is_secure=:is_secure,min=:min,ufmi=:ufmi where mdn_id = (select id from whlr_mdn where mdn=:mdn)")
						.setParameter("cos", sub.getCos()).setParameter("email", sub.getEmail())
						.setParameter("is_secure", sub.getIsSecure()).setParameter("min", sub.getMin())
						.setParameter("ufmi", sub.getUfmi()).setParameter("mdn", sub.getMdn()).executeUpdate();

			}
		}

	}

	@Override
	public Subscriber getSubscriber(Long mdn) {

		log.debug(" GET Subscriber DAO ");
		Subscriber subscriber = subscriberExistcheck(mdn);

		if (subscriber.getSuspended())
			throw new ValidationErrorException(MessageUtil.SUBSCRIBER_SUSPENDED);

		return subscriber;

	}

	private Subscriber subscriberExistcheck(Long mdn) {

		Subscriber sub = subRepository.findByMdn(mdn);
		if (sub == null)
			throw new ResourceNotFoundException(MessageUtil.NOT_FOUND_MSG);

		return sub;
	}

	@Override
	public void deleteSubscriber(Long mdn) {

		log.debug(" Delete Subscriber DAO ");
		subscriberExistcheck(mdn);

		String[] strArr = { "delete from whlr_subscribers_info where mdn_id=(select id from whlr_mdn where mdn=:mdn)",
				"delete from shared_contacts where mdn_id=(select id from whlr_mdn where mdn=:mdn)",
				"delete from whlr_mdn where mdn=:mdn" };

		for (String query : strArr) {
			entityManager.createNativeQuery(query).setParameter("mdn", mdn).executeUpdate();
		}

	}

}
