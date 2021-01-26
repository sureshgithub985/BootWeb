package com.ey.core.entity;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.ey.core.annotations.NotAllowed;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Table(name = "wms_user_profile")
@Entity
public class UProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Range(min = 1, max = 16000, message = "must be between 1 and 16000")
	@Column(name = "id")
	private Integer id;

	@NotAllowed(message = "contains Invalid characters")
	@Size(min = 1, max = 24)
	@NotNull
	@Column(name = "name", unique = true)
	private String name;

	@Range(min = 60, max = 86400)
	@Column(name = "registration_timer")
	private Integer registrationTimer;

	@Range(min = 60, max = 86400)
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

}
