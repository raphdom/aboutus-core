package com.jrdevel.aboutus.core.person;

import java.io.Serializable;
import java.util.Date;

import com.jrdevel.aboutus.core.common.view.Projection;

/**
 * @author Raphael Domingues
 *
 */
public class PersonView implements Serializable{

	private static final long serialVersionUID = -952498841612285280L;
	private Integer id;
	private String name;
	private boolean male;
	private String civilStatus;
	private String naturality;
	private boolean member;
	private Date birthday;
	private int nif;
	private String profession;
	private String memberType;
	
	@Projection
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Projection
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Projection
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	@Projection(entityName="cvStatTrans.text")
	public String getCivilStatus() {
		return civilStatus;
	}
	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}
	@Projection(entityName="countryTrans.text")
	public String getNaturality() {
		return naturality;
	}
	public void setNaturality(String naturality) {
		this.naturality = naturality;
	}
	@Projection
	public boolean isMember() {
		return member;
	}
	public void setMember(boolean member) {
		this.member = member;
	}
	@Projection
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Projection
	public int getNif() {
		return nif;
	}
	public void setNif(int nif) {
		this.nif = nif;
	}
	@Projection
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Projection(entityName="memberTypeTrans.text")
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
}
