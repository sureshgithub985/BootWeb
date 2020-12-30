package com.ey.core.model;

import lombok.Data;

@Data
public class EnterpriseDTO {

	private Integer id;
	private String name;
	private Integer ckr;
	private String ckrList;
	private String company;
	private String addressLine1;
	private String addressLine2;
	private String zipCode;

}
