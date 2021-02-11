package com.ey.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.Subselect;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity

@Subselect("select whlr_mdn.mdn,whlr_subscribers_info.min,whlr_subscribers_info.cos,whlr_subscribers_info.email,ptt_enterprises_info.name as enterprise_name,whlr_subscribers_info.is_secure,whlr_subscribers_info.suspended,whlr_subscribers_info.ufmi,whlr_suid.suid, shared_contacts.user_name from whlr_subscribers_info, whlr_mdn,shared_contacts,ptt_enterprises_info, whlr_suid where whlr_mdn.id=whlr_subscribers_info.mdn_id and whlr_mdn.id=shared_contacts.mdn_id and ptt_enterprises_info.id=whlr_subscribers_info.enterprise_id and whlr_suid.mdn_id=whlr_mdn.id")
//@Subselect("select whlr_mdn.mdn,whlr_subscribers_info.min,whlr_subscribers_info.cos,whlr_subscribers_info.email,ptt_enterprises_info.name as enterprise_name,whlr_subscribers_info.is_secure,whlr_subscribers_info.suspended,whlr_subscribers_info.ufmi,shared_contacts.user_name from whlr_subscribers_info, whlr_mdn,shared_contacts,ptt_enterprises_info where whlr_mdn.id=whlr_subscribers_info.mdn_id and whlr_mdn.id=shared_contacts.mdn_id and ptt_enterprises_info.id=whlr_subscribers_info.enterprise_id")
//@Subselect("select whlr_mdn.mdn,whlr_subscribers_info.min,whlr_subscribers_info.cos,whlr_subscribers_info.email,ptt_enterprises_info.name as enterprise_name,whlr_subscribers_info.is_secure,"
//		+ "whlr_subscribers_info.suspended,whlr_subscribers_info.ufmi,whlr_suid.suid, shared_contacts.user_name from whlr_subscribers_info, whlr_mdn,shared_contacts,ptt_enterprises_info, "
// + "whlr_suid where whlr_mdn.id=whlr_subscribers_info.mdn_id and
// whlr_mdn.id=shared_contacts.mdn_id and
// ptt_enterprises_info.id=whlr_subscribers_info.enterprise_id and "
// + "whlr_suid.mdn_id=whlr_mdn.id")
//@Subselect("select whlr_mdn.mdn,whlr_subscribers_info.min,whlr_subscribers_info.cos,whlr_subscribers_info.email,ptt_enterprises_info.name as enterprise_name,whlr_subscribers_info.is_secure,"
// +
// "whlr_subscribers_info.suspended,whlr_subscribers_info.ufmi,shared_contacts.user_name
// from whlr_subscribers_info, whlr_mdn,shared_contacts,ptt_enterprises_info
// where "
// + "whlr_mdn.id=whlr_subscribers_info.mdn_id and
// whlr_mdn.id=shared_contacts.mdn_id and
// ptt_enterprises_info.id=whlr_subscribers_info.enterprise_id")
public class Subscriber {

	@Id
	@NotNull
	@Range(min = 100, max = 99999999999999L)
	@Column(name = "mdn", unique = true)
	private Long mdn;

	@NotNull
	@Range(min = 100, max = 99999999999999L)
	@Column(name = "min", unique = true)
	private Long min;

	@NotNull
	@Column(name = "enterprise_name")
	private String enterpriseName;

	@XmlElement(name = "cos")
	@NotNull
	@Range(min = 1, max = 16000)
	@Column(name = "cos")
	private Integer cos;

	@Column(name = "is_secure")
	private Boolean isSecure;

	@XmlElement(name = "ufmi")
	@Size(min = 5, max = 15)
	@Column(name = "ufmi", unique = true)
	private String ufmi;

	@Size(min = 3, max = 24)
	@Column(name = "user_name")
	private String userName;

	@Email
	@Column(name = "email")
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "suspended")
	private Boolean suspended;

	@XmlElement(name = "mdn")
	public Long getMdn() {
		return mdn;
	}

	public void setMdn(Long mdn) {
		this.mdn = mdn;
	}

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Integer getCos() {
		return cos;
	}

	public void setCos(Integer cos) {
		this.cos = cos;
	}

	@XmlElement(name = "isSecure")
	public Boolean getIsSecure() {
		return isSecure;
	}

	public void setIsSecure(Boolean isSecure) {
		this.isSecure = isSecure;
	}

	public String getUfmi() {
		return ufmi;
	}

	public void setUfmi(String ufmi) {
		this.ufmi = ufmi;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	@Override
	public String toString() {
		return "Subscriber [mdn=" + mdn + ", min=" + min + ", enterpriseName=" + enterpriseName + ", cos=" + cos
				+ ", isSecure=" + isSecure + ", ufmi=" + ufmi + ", userName=" + userName + ", email=" + email
				+ ", suspended=" + suspended + "]";
	}

	public List<Suid> getSuids() {
		return suids;
	}

	public void setSuids(List<Suid> suids) {
		this.suids = suids;
	}

	//@JsonSubTypes(@Type(value = Suid.class))
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subscriber")
	private List<Suid> suids;
	
	

	/*
	 * public List<Suid> getSuids1() { Suid suid = new Suid();
	 * suid.setSuid("hello"); List<Suid> suidList = new ArrayList<>();
	 * suidList.add(suid);
	 * System.out.println("list of suid values are... "+suidList); return suidList;
	 * }
	 * 
	 * public void setSuids(List<Suid> suids) { this.suids = suids; }
	 */
	
	
}
