package com.jrdevel.aboutus.core.person;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Raphael Domingues
 *
 */
public class PersonDTO implements Serializable{
	
	private static final long serialVersionUID = -8675196533772013353L;
	
	private Integer id;
	private String name;
	private boolean male;
	private String civilStatusValue;
	private String naturalityValue;
	private Date birthday;
	private int nif;
	private String profession;
	private boolean member;
	private String memberTypeValue;
	private boolean baptized;
	private Date baptismdate;
	private boolean consolidated;
	private Date arrivalChurchDate;
	private String precedingChurch;
	private String observations;
	private List<AddressDTO> addresses;
	private List<ContactDTO> contacts;
	
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
	public String getCivilStatusValue() {
		return civilStatusValue;
	}
	public void setCivilStatusValue(String civilStatusValue) {
		this.civilStatusValue = civilStatusValue;
	}
	public String getNaturalityValue() {
		return naturalityValue;
	}
	public void setNaturalityValue(String naturalityValue) {
		this.naturalityValue = naturalityValue;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getNif() {
		return nif;
	}
	public void setNif(int nif) {
		this.nif = nif;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public boolean isMember() {
		return member;
	}
	public void setMember(boolean member) {
		this.member = member;
	}
	public String getMemberTypeValue() {
		return memberTypeValue;
	}
	public void setMemberTypeValue(String memberTypeValue) {
		this.memberTypeValue = memberTypeValue;
	}
	public boolean isBaptized() {
		return baptized;
	}
	public void setBaptized(boolean baptized) {
		this.baptized = baptized;
	}
	public Date getBaptismdate() {
		return baptismdate;
	}
	public void setBaptismdate(Date baptismdate) {
		this.baptismdate = baptismdate;
	}
	public boolean isConsolidated() {
		return consolidated;
	}
	public void setConsolidated(boolean consolidated) {
		this.consolidated = consolidated;
	}
	public Date getArrivalChurchDate() {
		return arrivalChurchDate;
	}
	public void setArrivalChurchDate(Date arrivalChurchDate) {
		this.arrivalChurchDate = arrivalChurchDate;
	}
	public String getPrecedingChurch() {
		return precedingChurch;
	}
	public void setPrecedingChurch(String precedingChurch) {
		this.precedingChurch = precedingChurch;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	public List<ContactDTO> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactDTO> contacts) {
		this.contacts = contacts;
	}
	
}
