package com.jrdevel.aboutus.core.person;

import java.io.Serializable;

import com.jrdevel.aboutus.core.common.model.Person;

/**
 * @author Raphael Domingues
 *
 */
public class ContactDTO implements Serializable{

	private static final long serialVersionUID = -474994854076706361L;
	
	private Integer id;
	private String contactTypeValue;
	private Person person;
	private String value;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContactTypeValue() {
		return contactTypeValue;
	}
	public void setContactTypeValue(String contactTypeValue) {
		this.contactTypeValue = contactTypeValue;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
