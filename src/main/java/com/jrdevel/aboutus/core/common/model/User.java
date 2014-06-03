package com.jrdevel.aboutus.core.common.model;

// Generated 3/jun/2014 19:59:04 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "tbl_user")
public class User implements java.io.Serializable {

	private Integer id;
	private Church church;
	private Customer customer;
	private Person person;
	private String email;
	private String password;
	private boolean block;
	private boolean activation;
	private Date registerDate;
	private Date lastvisitDate;
	private Set<Permission> permissions = new HashSet<Permission>(0);
	private Set<Register> registers = new HashSet<Register>(0);
	private Set<FolderRole> folderRoles = new HashSet<FolderRole>(0);
	private Set<Session> sessions = new HashSet<Session>(0);
	private Set<Group> groups = new HashSet<Group>(0);

	public User() {
	}

	public User(String email, String password, boolean block,
			boolean activation, Date registerDate) {
		this.email = email;
		this.password = password;
		this.block = block;
		this.activation = activation;
		this.registerDate = registerDate;
	}

	public User(Church church, Customer customer, Person person, String email,
			String password, boolean block, boolean activation,
			Date registerDate, Date lastvisitDate, Set<Permission> permissions,
			Set<Register> registers, Set<FolderRole> folderRoles,
			Set<Session> sessions, Set<Group> groups) {
		this.church = church;
		this.customer = customer;
		this.person = person;
		this.email = email;
		this.password = password;
		this.block = block;
		this.activation = activation;
		this.registerDate = registerDate;
		this.lastvisitDate = lastvisitDate;
		this.permissions = permissions;
		this.registers = registers;
		this.folderRoles = folderRoles;
		this.sessions = sessions;
		this.groups = groups;
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
	@JoinColumn(name = "churchId")
	public Church getChurch() {
		return this.church;
	}

	public void setChurch(Church church) {
		this.church = church;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "block", nullable = false)
	public boolean isBlock() {
		return this.block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	@Column(name = "activation", nullable = false)
	public boolean isActivation() {
		return this.activation;
	}

	public void setActivation(boolean activation) {
		this.activation = activation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registerDate", nullable = false, length = 19)
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastvisitDate", length = 19)
	public Date getLastvisitDate() {
		return this.lastvisitDate;
	}

	public void setLastvisitDate(Date lastvisitDate) {
		this.lastvisitDate = lastvisitDate;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_user_permissions", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "permission_id", nullable = false, updatable = false) })
	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Register> getRegisters() {
		return this.registers;
	}

	public void setRegisters(Set<Register> registers) {
		this.registers = registers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<FolderRole> getFolderRoles() {
		return this.folderRoles;
	}

	public void setFolderRoles(Set<FolderRole> folderRoles) {
		this.folderRoles = folderRoles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Session> getSessions() {
		return this.sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_user_groups", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "group_id", nullable = false, updatable = false) })
	public Set<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

}
