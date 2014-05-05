package com.jrdevel.aboutus.core.person;

import java.io.Serializable;

/**
 * @author Raphael Domingues
 *
 */
public class ContactDTO implements Serializable{

	private static final long serialVersionUID = -474994854076706361L;
	
	private String contactTypeValue;
	private String value;
	
	public String getContactTypeValue() {
		return contactTypeValue;
	}
	public void setContactTypeValue(String contactTypeValue) {
		this.contactTypeValue = contactTypeValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
