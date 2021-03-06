package com.jrdevel.aboutus.core.common.model;

import com.jrdevel.aboutus.core.common.model.lists.Country;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Register generated by hbm2java
 */
@Entity
@Table(name = "tbl_register")
public class Register implements java.io.Serializable {

	private Integer id;
	private User user;
	private Country country;
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

	public Register(User user, Country country, String email, String password,
			String churchName, String churchAddress, String nameResp,
			String siteAlias, String qtdMembers, Date registerDate) {
		this.user = user;
		this.country = country;
		this.email = email;
		this.password = password;
		this.churchName = churchName;
		this.churchAddress = churchAddress;
		this.nameResp = nameResp;
		this.siteAlias = siteAlias;
		this.qtdMembers = qtdMembers;
		this.registerDate = registerDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "churchName", nullable = false)
	public String getChurchName() {
		return this.churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	@Column(name = "churchAddress", nullable = false)
	public String getChurchAddress() {
		return this.churchAddress;
	}

	public void setChurchAddress(String churchAddress) {
		this.churchAddress = churchAddress;
	}

	@Column(name = "nameResp", nullable = false)
	public String getNameResp() {
		return this.nameResp;
	}

	public void setNameResp(String nameResp) {
		this.nameResp = nameResp;
	}

	@Column(name = "siteAlias", nullable = false)
	public String getSiteAlias() {
		return this.siteAlias;
	}

	public void setSiteAlias(String siteAlias) {
		this.siteAlias = siteAlias;
	}

	@Column(name = "qtdMembers")
	public String getQtdMembers() {
		return this.qtdMembers;
	}

	public void setQtdMembers(String qtdMembers) {
		this.qtdMembers = qtdMembers;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registerDate", nullable = false, length = 19)
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

}
