package com.jrdevel.aboutus.core.common.model;

// Generated 5/mai/2014 23:56:03 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Instrument generated by hbm2java
 */
@Entity
@Table(name = "tbl_instruments")
public class Instrument implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<Tab> tabs = new HashSet<Tab>(0);

	public Instrument() {
	}

	public Instrument(String name) {
		this.name = name;
	}

	public Instrument(String name, Set<Tab> tabs) {
		this.name = name;
		this.tabs = tabs;
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "instrument")
	public Set<Tab> getTabs() {
		return this.tabs;
	}

	public void setTabs(Set<Tab> tabs) {
		this.tabs = tabs;
	}

}
