package com.ey.core.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = { "id", "name", "callSetupPriority", "isCallSetupPreemptionEnabled" })
@XmlRootElement(name = "gprofile")
public class GTProfileDTO {

	private Integer id;
	private String name;
	private Integer callSetupPriority;
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
		return "GTProfileDTO [id=" + id + ", name=" + name + ", callSetupPriority=" + callSetupPriority
				+ ", isCallSetupPreemptionEnabled=" + isCallSetupPreemptionEnabled + "]";
	}

}
