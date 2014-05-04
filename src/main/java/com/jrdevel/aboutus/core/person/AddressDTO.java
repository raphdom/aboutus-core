package com.jrdevel.aboutus.core.person;

import java.io.Serializable;

/**
 * @author Raphael Domingues
 *
 */
public class AddressDTO implements Serializable{

	private static final long serialVersionUID = 2190720453395740188L;
	
	private Integer id;
	private String address;
	private String state;
	private String country;
	private String postcode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
}
