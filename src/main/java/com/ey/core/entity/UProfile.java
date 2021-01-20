package com.ey.core.entity;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Table(name = "wms_user_profile")
@Entity
public class UProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * <name>Profile1</name> <registration_timer>1800</registration_timer>
	 * <ageoff_timer>2000</ageoff_timer> <is_console>false</is_console>
	 * <is_open_enterprise>true</is_open_enterprise>
	 * <is_spooling_enabled>true</is_spooling_enabled>
	 * <is_qos_enabled>true</is_qos_enabled>
	 * <is_mdn_in_min_enabled>true</is_mdn_in_min_enabled>
	 * <talkburst_limit>20</talkburst_limit> <lte_paging_cycle>1</lte_paging_cycle>
	 * <floor_revoke_timer>30</floor_revoke_timer>
	 * <target_wakeup_throttle_timer>100</target_wakeup_throttle_timer>
	 * <signaling_reservation_priority>13</signaling_reservation_priority>
	 * <media_reservation_priority>13</media_reservation_priority>
	 * <call_setup_priority>1</call_setup_priority>
	 * <is_call_setup_preemption_enabled>true</is_call_setup_preemption_enabled>
	 * <floor_priority>1</floor_priority>
	 * <is_floor_preemption_enabled>true</is_floor_preemption_enabled>
	 * <emergency_floor_revoke_timer>60</emergency_floor_revoke_timer>
	 */

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

	@Override
	public String toString() {
		return "UProfile [id=" + id + ", name=" + name + ", registrationTimer=" + registrationTimer + ", ageoffTimer="
				+ ageoffTimer + ", isConsole=" + isConsole + ", isSpoolingEnabled=" + isSpoolingEnabled + "]";
	}

}
