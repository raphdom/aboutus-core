package com.jrdevel.aboutus.core.common.model;

import com.jrdevel.aboutus.core.common.model.lists.Country;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Church generated by hbm2java
 */
@Entity
@Table(name = "tbl_churchs")
public class Church implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private File file;
	private Country country;
	private String name;
	private String completeName;
	private String address;
	private String state;
	private String postalCode;
	private Integer nif;
	private Set<User> users = new HashSet<User>(0);
	private Set<Person> persons = new HashSet<Person>(0);

	public Church() {
	}

	public Church(Customer customer, String name, String completeName) {
		this.customer = customer;
		this.name = name;
		this.completeName = completeName;
	}

	public Church(Customer customer, File file, Country country, String name,
			String completeName, String address, String state,
			String postalCode, Integer nif, Set<User> users, Set<Person> persons) {
		this.customer = customer;
		this.file = file;
		this.country = country;
		this.name = name;
		this.completeName = completeName;
		this.address = address;
		this.state = state;
		this.postalCode = postalCode;
		this.nif = nif;
		this.users = users;
		this.persons = persons;
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
	@JoinColumn(name = "customerId", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "logo")
	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country")
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "completeName", nullable = false)
	public String getCompleteName() {
		return this.completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	@Column(name = "address", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "state", length = 100)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "postalCode", length = 100)
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "nif")
	public Integer getNif() {
		return this.nif;
	}

	public void setNif(Integer nif) {
		this.nif = nif;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "church")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "church")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}
