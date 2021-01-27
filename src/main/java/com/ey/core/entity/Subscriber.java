package com.ey.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Subselect;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Subselect("select whlr_mdn.mdn,whlr_subscribers_info.min,whlr_subscribers_info.cos,whlr_subscribers_info.email,whlr_subscribers_info.enterprise_name,"
		+ "whlr_subscribers_info.is_secure,whlr_subscribers_info.suspended,whlr_subscribers_info.ufmi,shared_contacts.user_name from whlr_subscribers_info,"
		+ " whlr_mdn,shared_contacts where whlr_mdn.id=whlr_subscribers_info.mdn_id and whlr_mdn.id=shared_contacts.mdn_id")
public class Subscriber {

	@Id
	@NotNull
	@Range(min = 100, max = 99999999999999L)
	@Column(name = "mdn", unique = true)
	private Long mdn;

	@NotNull
	@Range(min = 100, max = 99999999999999L)
	@Column(name = "min", unique = true)
	private Long min;

	@NotNull
	@Column(name = "enterprise_name")
	private String enterpriseName;

	@NotNull
	@Range(min = 1, max = 16000)
	@Column(name = "cos")
	private Integer cos;

	@Column(name = "is_secure")
	private Boolean isSecure;

	@Size(min = 5, max = 15)
	@Column(name = "ufmi", unique = true)
	private String ufmi;

	@Size(min = 3, max = 24)
	@Column(name = "user_name")
	private String userName;

	@Email
	@Column(name = "email")
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "suspended")
	private Boolean suspended;

}
