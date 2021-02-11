package com.ey.core.dto;

import javax.xml.bind.annotation.XmlElement;

public class SuidDTO {

	private String suid;

	public String getSuid() {
		return suid;
	}

	public void setSuid(String suid) {
		this.suid = suid;
	}

	@Override
	public String toString() {
		return "SuidDTO [suid=" + suid + "]";
	}

}
