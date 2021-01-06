package com.ey.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
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

}
