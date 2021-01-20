package com.ey.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wms_versions")
@Entity
public class Version {

	@Id
	@Column(name = "number")
	private Double number;

	@Column(name = "role")
	private String role;

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Version [number=" + number + ", role=" + role + "]";
	}

}
