package com.jrdevel.aboutus.core.common.model;

// Generated 27/mai/2014 21:32:42 by Hibernate Tools 3.4.0.CR1

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

/**
 * Group generated by hbm2java
 */
@Entity
@Table(name = "tbl_group")
public class Group implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private String name;
	private Set<User> users = new HashSet<User>(0);
	private Set<FolderRole> folderRoles = new HashSet<FolderRole>(0);
	private Set<Permission> permissions = new HashSet<Permission>(0);

	public Group() {
	}

	public Group(String name) {
		this.name = name;
	}

	public Group(Customer customer, String name, Set<User> users,
			Set<FolderRole> folderRoles, Set<Permission> permissions) {
		this.customer = customer;
		this.name = name;
		this.users = users;
		this.folderRoles = folderRoles;
		this.permissions = permissions;
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
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_user_groups", joinColumns = { @JoinColumn(name = "group_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	public Set<FolderRole> getFolderRoles() {
		return this.folderRoles;
	}

	public void setFolderRoles(Set<FolderRole> folderRoles) {
		this.folderRoles = folderRoles;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_group_permissions", joinColumns = { @JoinColumn(name = "group_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "permission_id", nullable = false, updatable = false) })
	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}
