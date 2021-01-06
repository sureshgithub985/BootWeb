package com.ey.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
@Entity
@Table(name = "ptt_subscribers_info")
public class Subscriber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Range(min = 100, max = 99999999999999L)
	@Column(name = "mdn")
	private Long mdn;

	@NotNull
	@Column(name = "enterprise_name")
	private String enterpriseName;

	@Column(name = "is_secure")
	private Boolean isSecure;

	@Size(min = 5, max = 15)
	@Column(name = "ufmi")
	private String ufmi;

	@Size(min = 3, max = 24)
	@Column(name = "user_name")
	private String userName;

	@Email
	@Column(name = "email")
	private String email;
	
	
}

