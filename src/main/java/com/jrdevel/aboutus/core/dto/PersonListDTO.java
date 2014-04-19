package com.jrdevel.aboutus.core.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Raphael Domingues
 *
 */
public class PersonListDTO implements Serializable{

	private static final long serialVersionUID = -4919811436246939507L;
	
	private Integer id;
	private String name;
	private boolean male;
	private String civilStatus;
	private String naturality;
	private boolean member;
	private Date birthday;
	private Integer nif;
	private String profession;
	private String memberType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	public String getCivilStatus() {
		return civilStatus;
	}
	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}
	public String getNaturality() {
		return naturality;
	}
	public void setNaturality(String naturality) {
		this.naturality = naturality;
	}
	public boolean isMember() {
		return member;
	}
	public void setMember(boolean member) {
		this.member = member;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getNif() {
		return nif;
	}
	public void setNif(Integer nif) {
		this.nif = nif;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
}
