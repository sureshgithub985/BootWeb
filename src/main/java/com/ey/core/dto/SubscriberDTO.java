package com.ey.core.dto;

public class SubscriberDTO {

	private Integer id;
	private Long mdn;
	private String enterpriseName;
	private Boolean isSecure;
	private String ufmi;
	private String userName;
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getMdn() {
		return mdn;
	}

	public void setMdn(Long mdn) {
		this.mdn = mdn;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

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

	@Override
	public String toString() {
		return "SubscriberDTO [id=" + id + ", mdn=" + mdn + ", enterpriseName=" + enterpriseName + ", isSecure="
				+ isSecure + ", ufmi=" + ufmi + ", userName=" + userName + ", email=" + email + "]";
	}

}
