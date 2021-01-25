package com.ey.core.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ey.core.entity.PageCycle;

@XmlType(propOrder = { "id", "name", "registrationTimer", "ageoffTimer", "isConsole", "isSpoolingEnabled",
		"isOpenEnterprise", "ltePagingCycle" })
@XmlRootElement(name = "uprofile")
public class UProfileDTO {

	private Integer id;
	private String name;
	private Integer registrationTimer;
	private Integer ageoffTimer;
	private Boolean isConsole;
	private Boolean isSpoolingEnabled;
	private Boolean isOpenEnterprise;
	private PageCycle ltePagingCycle;

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

	@XmlElement(name = "registrationTimer")
	public Integer getRegistrationTimer() {
		return registrationTimer;
	}

	public void setRegistrationTimer(Integer registrationTimer) {
		this.registrationTimer = registrationTimer;
	}

	@XmlElement(name = "ageoffTimer")
	public Integer getAgeoffTimer() {
		return ageoffTimer;
	}

	public void setAgeoffTimer(Integer ageoffTimer) {
		this.ageoffTimer = ageoffTimer;
	}

	@XmlElement(name = "isConsole")
	public Boolean getIsConsole() {
		return isConsole;
	}

	public void setIsConsole(Boolean isConsole) {
		this.isConsole = isConsole;
	}

	@XmlElement(name = "isSpoolingEnabled")
	public Boolean getIsSpoolingEnabled() {
		return isSpoolingEnabled;
	}

	public void setIsSpoolingEnabled(Boolean isSpoolingEnabled) {
		this.isSpoolingEnabled = isSpoolingEnabled;
	}

	@XmlElement(name = "isOpenEnterprise")
	public Boolean getIsOpenEnterprise() {
		return isOpenEnterprise;
	}

	public void setIsOpenEnterprise(Boolean isOpenEnterprise) {
		this.isOpenEnterprise = isOpenEnterprise;
	}

	@XmlElement(name = "ltePagingCycle")
	public PageCycle getLtePagingCycle() {
		return ltePagingCycle;
	}

	public void setLtePagingCycle(PageCycle ltePagingCycle) {
		this.ltePagingCycle = ltePagingCycle;
	}

}
