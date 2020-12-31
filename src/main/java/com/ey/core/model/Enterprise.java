package com.ey.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "ptt_enterprises_info")
public class Enterprise implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min = 3, max = 15)
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "ckr")
	private Integer ckr;

	@Column(name = "ckr_list")
	private String ckrList;

	@Column(name = "company")
	private String company;

	@Column(name = "address_line1")
	private String addressLine1;

	@Column(name = "address_line2")
	private String addressLine2;

	@Column(name = "zip_code")
	private String zipCode;

}
