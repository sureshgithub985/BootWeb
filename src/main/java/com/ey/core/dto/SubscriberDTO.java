package com.ey.core.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ey.core.entity.Suid;

@XmlRootElement(name = "subscriber")
//@XmlType(propOrder = { "min", "mdn", "enterpriseName", "cos", "isSecure", "ufmi", "userName", "email", "suids" })
public class SubscriberDTO {

	private Long mdn;
	private Long min;
	private String enterpriseName;
	private Boolean isSecure;
	private String ufmi;
	private String userName;
	private String email;
	private Integer cos;
	private List<SuidDTO> suids;

	@XmlElement(name = "min")
	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	@XmlElement(name = "mdn")
	public Long getMdn() {
		return mdn;
	}

	public void setMdn(Long mdn) {
		this.mdn = mdn;
	}

	@XmlElement(name = "enterpriseName")
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	@XmlElement(name = "isSecure")
	public Boolean getIsSecure() {
		return isSecure;
	}

	public void setIsSecure(Boolean isSecure) {
		this.isSecure = isSecure;
	}

	@XmlElement(name = "ufmi")
	public String getUfmi() {
		return ufmi;
	}

	public void setUfmi(String ufmi) {
		this.ufmi = ufmi;
	}

	@XmlElement(name = "userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@XmlElement(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement(name = "cos")
	public Integer getCos() {
		return cos;
	}

	public void setCos(Integer cos) {
		this.cos = cos;
	}

	@XmlElementWrapper(name = "suids")
	@XmlElement(name = "suid")
	public List<SuidDTO> getSuids() {
		return suids;
	}

	public void setSuids(List<SuidDTO> suids) {
		this.suids = suids;
	}

	@Override
	public String toString() {
		return "SubscriberDTO [mdn=" + mdn + ", min=" + min + ", enterpriseName=" + enterpriseName + ", isSecure="
				+ isSecure + ", ufmi=" + ufmi + ", userName=" + userName + ", email=" + email + ", cos=" + cos
				+ ", suids=" + suids + "]";
	}

	


}
