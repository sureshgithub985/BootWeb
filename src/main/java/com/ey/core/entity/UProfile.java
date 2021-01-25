package com.ey.core.entity;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Table(name = "wms_user_profile")
@Entity
public class UProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "registration_timer")
	private Integer registrationTimer;

	@Column(name = "ageoff_timer")
	private Integer ageoffTimer;

	@Column(name = "is_console")
	private Boolean isConsole;

	@Column(name = "is_spooling_enabled")
	private Boolean isSpoolingEnabled;

	@Column(name = "is_open_enterprise")
	private Boolean isOpenEnterprise;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "lte_paging_cycle")
	private PageCycle ltePagingCycle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegistrationTimer() {
		return registrationTimer;
	}

	public void setRegistrationTimer(Integer registrationTimer) {
		this.registrationTimer = registrationTimer;
	}

	public Integer getAgeoffTimer() {
		return ageoffTimer;
	}

	public void setAgeoffTimer(Integer ageoffTimer) {
		this.ageoffTimer = ageoffTimer;
	}

	public Boolean getIsConsole() {
		return isConsole;
	}

	public void setIsConsole(Boolean isConsole) {
		this.isConsole = isConsole;
	}

	public Boolean getIsSpoolingEnabled() {
		return isSpoolingEnabled;
	}

	public void setIsSpoolingEnabled(Boolean isSpoolingEnabled) {
		this.isSpoolingEnabled = isSpoolingEnabled;
	}

	public Boolean getIsOpenEnterprise() {
		return isOpenEnterprise;
	}

	public void setIsOpenEnterprise(Boolean isOpenEnterprise) {
		this.isOpenEnterprise = isOpenEnterprise;
	}

	public PageCycle getLtePagingCycle() {
		return ltePagingCycle;
	}

	public void setLtePagingCycle(PageCycle ltePagingCycle) {
		this.ltePagingCycle = ltePagingCycle;
	}

	@Override
	public String toString() {
		return "UProfile [id=" + id + ", name=" + name + ", registrationTimer=" + registrationTimer + ", ageoffTimer="
				+ ageoffTimer + ", isConsole=" + isConsole + ", isSpoolingEnabled=" + isSpoolingEnabled
				+ ", isOpenEnterprise=" + isOpenEnterprise + ", ltePagingCycle=" + ltePagingCycle + "]";
	}

}
