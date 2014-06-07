package com.jrdevel.aboutus.core.common.model;

// Generated 7/jun/2014 21:22:28 by Hibernate Tools 3.4.0.CR1

import com.jrdevel.aboutus.core.common.model.lists.ContactType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PersonContacts generated by hbm2java
 */
@Entity
@Table(name = "tbl_personContacts")
public class PersonContacts implements java.io.Serializable {

	private Integer id;
	private Person person;
	private ContactType contactType;
	private String value;

	public PersonContacts() {
	}

	public PersonContacts(Person person, ContactType contactType) {
		this.person = person;
		this.contactType = contactType;
	}

	public PersonContacts(Person person, ContactType contactType, String value) {
		this.person = person;
		this.contactType = contactType;
		this.value = value;
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
	@JoinColumn(name = "person_id", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contactType", nullable = false)
	public ContactType getContactType() {
		return this.contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	@Column(name = "value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
