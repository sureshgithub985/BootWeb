package com.ey.core.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "name", "ckr", "enterpriseName", "sgid", "groupType" })
@XmlRootElement(name = "egroup")
public class EntGroupDTO {

	private Integer id;
	private String name;
	private Integer ckr;
	private String enterpriseName;
	private String sgid;
	private Integer groupType;

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

	@XmlElement(name = "enterpriseName")
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	@XmlElement(name = "sgid")
	public String getSgid() {
		return sgid;
	}

	public void setSgid(String sgid) {
		this.sgid = sgid;
	}

	@XmlElement(name = "groupType")
	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	@Override
	public String toString() {
		return "EntGroupDTO [id=" + id + ", name=" + name + ", ckr=" + ckr + ", enterpriseName=" + enterpriseName
				+ ", sgid=" + sgid + ", groupType=" + groupType + "]";
	}

}
