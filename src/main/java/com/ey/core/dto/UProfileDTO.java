package com.ey.core.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.Range;

import com.ey.core.annotations.NotAllowed;
import com.ey.core.entity.PageCycle;

@XmlType(propOrder = { "id", "name", "registrationTimer", "ageoffTimer", "isConsole", "isSpoolingEnabled",
		"isOpenEnterprise", "ltePagingCycle" })
@XmlRootElement(name = "uprofile")
public class UProfileDTO {

	@Range(min = 1, max = 16000, message = "must be between 1 and 16000")
	private Integer id;

	@NotAllowed(message = "contains Invalid characters")
	@Size(min = 1, max = 24)
	@NotNull
	private String name;

	@Range(min = 60, max = 86400)
	private Integer registrationTimer;

	@Range(min = 60, max = 86400)
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
