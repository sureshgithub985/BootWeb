package com.ey.core.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "name", "ckr", "ckrList", "company", "addressLine1", "addressLine2", "zipCode" })
@XmlRootElement(name = "enterprise")
public class EnterpriseDTO {

	private Integer id;
	private String name;
	private Integer ckr;
	private String ckrList;
	private String company;
	private String addressLine1;
	private String addressLine2;
	private String zipCode;

	@XmlElement(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "ckr")
	public Integer getCkr() {
		return ckr;
	}

	public void setCkr(Integer ckr) {
		this.ckr = ckr;
	}

	@XmlElement(name = "ckrList")
	public String getCkrList() {
		return ckrList;
	}

	public void setCkrList(String ckrList) {
		this.ckrList = ckrList;
	}

	@XmlElement(name = "company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@XmlElement(name = "addressLine1")
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	@XmlElement(name = "addressLine2")
	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@XmlElement(name = "zipCode")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "EnterpriseDTO [id=" + id + ", name=" + name + ", ckr=" + ckr + ", ckrList=" + ckrList + ", company="
				+ company + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", zipCode=" + zipCode
				+ "]";
	}

}
