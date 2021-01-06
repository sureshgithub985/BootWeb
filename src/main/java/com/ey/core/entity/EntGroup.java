package com.ey.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
@Entity
@Table(name = "shared_chat_group_info")
public class EntGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min = 3, max = 24)
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "ckr")
	private Integer ckr;

	@NotNull
	@Column(name = "enterprise_name")
	private String enterpriseName;

	@Column(name = "sgid")
	private String sgid;

	@Range(min = 0, max = 1)
	@Column(name = "group_type")
	private Integer groupType;

}
