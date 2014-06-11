package com.jrdevel.aboutus.core.common.model;

// Generated 11/jun/2014 23:23:42 by Hibernate Tools 3.4.0.CR1

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
 * Plan generated by hbm2java
 */
@Entity
@Table(name = "tbl_plan")
public class Plan implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<PlanParam> planParams = new HashSet<PlanParam>(0);
	private Set<Customer> customers = new HashSet<Customer>(0);

	public Plan() {
	}

	public Plan(String name) {
		this.name = name;
	}

	public Plan(String name, Set<PlanParam> planParams, Set<Customer> customers) {
		this.name = name;
		this.planParams = planParams;
		this.customers = customers;
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

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
	public Set<PlanParam> getPlanParams() {
		return this.planParams;
	}

	public void setPlanParams(Set<PlanParam> planParams) {
		this.planParams = planParams;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

}
