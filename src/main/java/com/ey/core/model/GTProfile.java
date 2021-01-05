package com.ey.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.Range;

@XmlType(propOrder = { "id", "name", "callSetupPriority", "isCallSetupPreemptionEnabled" })
@XmlRootElement(name = "gprofile")
@Table(name = "ptt_group_profiles")
@Entity
public class GTProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Range(min = 1, max = 16000)
	@Id
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "name", unique = true)
	private String name;

	@Range(min = 3, max = 13)
	@Column(name = "call_setup_priority")
	private Integer callSetupPriority;

	@Column(name = "is_call_setup_preemption_enabled")
	private Boolean isCallSetupPreemptionEnabled;

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

	@XmlElement(name = "call_setup_priority")
	public Integer getCallSetupPriority() {
		return callSetupPriority;
	}

	public void setCallSetupPriority(Integer callSetupPriority) {
		this.callSetupPriority = callSetupPriority;
	}

	@XmlElement(name = "is_call_setup_preemption_enabled")
	public Boolean getIsCallSetupPreemptionEnabled() {
		return isCallSetupPreemptionEnabled;
	}

	public void setIsCallSetupPreemptionEnabled(Boolean isCallSetupPreemptionEnabled) {
		this.isCallSetupPreemptionEnabled = isCallSetupPreemptionEnabled;
	}

	@Override
	public String toString() {
		return "GTProfile [id=" + id + ", name=" + name + ", callSetupPriority=" + callSetupPriority
				+ ", isCallSetupPreemptionEnabled=" + isCallSetupPreemptionEnabled + "]";
	}

}
