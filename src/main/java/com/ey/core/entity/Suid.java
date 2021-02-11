package com.ey.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;


@Entity
@Subselect("select whlr_suid.suid,whlr_mdn.mdn from whlr_suid, whlr_mdn where whlr_mdn.id=whlr_suid.mdn_id")
public class Suid {

	
	@Id
	@Column(name="mdn")
	private Long mdn;

	
	@Column(name="suid")
	private String suid;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Subscriber subscriber;
	

	/*
	 * @JsonBackReference
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn private Subscriber subscriber;
	 * 
	 * @Column(name = "mdn", unique = true) private Long mdn;
	 */
	
	@XmlElement(name="suid")
	public String getSuid() {
		return suid;
	}

	public void setSuid(String suid) {
		this.suid = suid;
	}
	
	

	/*
	 * public Subscriber getSubscriber() { return subscriber; }
	 * 
	 * public void setSubscriber(Subscriber subscriber) { this.subscriber =
	 * subscriber; }
	 * 
	 * public Long getMdn() { return mdn; }
	 * 
	 * public void setMdn(Long mdn) { this.mdn = mdn; }
	 */

	
	
	

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public Long getMdn() {
		return mdn;
	}

	public void setMdn(Long mdn) {
		this.mdn = mdn;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public String toString() {
		return "Suid [suid=" + suid + "]";
	}

}
