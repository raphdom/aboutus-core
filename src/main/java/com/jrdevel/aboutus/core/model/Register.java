package com.jrdevel.aboutus.core.model;

// Generated 9/Mar/2014 21:10:27 by Hibernate Tools 3.4.0.CR1

import com.jrdevel.aboutus.core.model.lists.Country;
import java.util.Date;

/**
 * Register generated by hbm2java
 */
public class Register implements java.io.Serializable {

	private Integer id;
	private Country country;
	private User user;
	private String email;
	private String password;
	private String churchName;
	private String churchAddress;
	private String nameResp;
	private String siteAlias;
	private String qtdMembers;
	private Date registerDate;

	public Register() {
	}

	public Register(Country country, String email, String password,
			String churchName, String churchAddress, String nameResp,
			String siteAlias, Date registerDate) {
		this.country = country;
		this.email = email;
		this.password = password;
		this.churchName = churchName;
		this.churchAddress = churchAddress;
		this.nameResp = nameResp;
		this.siteAlias = siteAlias;
		this.registerDate = registerDate;
	}

	public Register(Country country, User user, String email, String password,
			String churchName, String churchAddress, String nameResp,
			String siteAlias, String qtdMembers, Date registerDate) {
		this.country = country;
		this.user = user;
		this.email = email;
		this.password = password;
		this.churchName = churchName;
		this.churchAddress = churchAddress;
		this.nameResp = nameResp;
		this.siteAlias = siteAlias;
		this.qtdMembers = qtdMembers;
		this.registerDate = registerDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChurchName() {
		return this.churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getChurchAddress() {
		return this.churchAddress;
	}

	public void setChurchAddress(String churchAddress) {
		this.churchAddress = churchAddress;
	}

	public String getNameResp() {
		return this.nameResp;
	}

	public void setNameResp(String nameResp) {
		this.nameResp = nameResp;
	}

	public String getSiteAlias() {
		return this.siteAlias;
	}

	public void setSiteAlias(String siteAlias) {
		this.siteAlias = siteAlias;
	}

	public String getQtdMembers() {
		return this.qtdMembers;
	}

	public void setQtdMembers(String qtdMembers) {
		this.qtdMembers = qtdMembers;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

}
