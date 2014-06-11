package com.jrdevel.aboutus.core.common.model;

// Generated 11/jun/2014 23:23:42 by Hibernate Tools 3.4.0.CR1

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
 * PlanParam generated by hbm2java
 */
@Entity
@Table(name = "tbl_plan_params")
public class PlanParam implements java.io.Serializable {

	private Integer id;
	private Plan plan;
	private String param;
	private String value;

	public PlanParam() {
	}

	public PlanParam(Plan plan, String param, String value) {
		this.plan = plan;
		this.param = param;
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
	@JoinColumn(name = "planId", nullable = false)
	public Plan getPlan() {
		return this.plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Column(name = "param", nullable = false, length = 100)
	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Column(name = "value", nullable = false, length = 200)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
