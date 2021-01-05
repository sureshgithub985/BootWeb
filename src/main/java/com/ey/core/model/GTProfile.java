package com.ey.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@XmlRootElement(name = "gprofile")
@Data
@Table(name = "ptt_group_profiles")
@Entity
public class GTProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Range(min = 1, max = 16000)
	@XmlElement(name = "id")
	@Id
	@Column(name = "id")
	private Integer id;

	@XmlElement(name = "name")
	@NotNull
	@Column(name = "name", unique = true)
	private String name;

	@XmlElement(name = "call_setup_priority")
	@Range(min = 3, max = 13)
	@Column(name = "call_setup_priority")
	private Integer callSetupPriority;

	@XmlElement(name = "is_call_setup_preemption_enabled")
	@Column(name = "is_call_setup_preemption_enabled")
	private Boolean isCallSetupPreemptionEnabled;

}
