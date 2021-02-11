package com.ey.core.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("")
public class EGOwner {

	@Id
	@Column(name = "mdn")
	private Long mdn;

	@Column(name = "name")
	private String name;

	@ManyToOne
	private List<Long> egowners;

	public Long getMdn() {
		return mdn;
	}

	public void setMdn(Long mdn) {
		this.mdn = mdn;
	}

	public List<Long> getEgowners() {
		return egowners;
	}

	public void setEgowners(List<Long> egowners) {
		this.egowners = egowners;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EGOwner [mdn=" + mdn + ", name=" + name + ", egowners=" + egowners + "]";
	}

}
