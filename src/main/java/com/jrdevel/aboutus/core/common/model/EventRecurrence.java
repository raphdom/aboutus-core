package com.jrdevel.aboutus.core.common.model;

// Generated 28/mai/2014 22:42:24 by Hibernate Tools 3.4.0.CR1

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
 * EventRecurrence generated by hbm2java
 */
@Entity
@Table(name = "tbl_event_recurrences")
public class EventRecurrence implements java.io.Serializable {

	private Integer id;
	private Event event;
	private boolean day;
	private Boolean week;
	private Boolean month;

	public EventRecurrence() {
	}

	public EventRecurrence(Event event, boolean day) {
		this.event = event;
		this.day = day;
	}

	public EventRecurrence(Event event, boolean day, Boolean week, Boolean month) {
		this.event = event;
		this.day = day;
		this.week = week;
		this.month = month;
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
	@JoinColumn(name = "event_id", nullable = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Column(name = "day", nullable = false)
	public boolean isDay() {
		return this.day;
	}

	public void setDay(boolean day) {
		this.day = day;
	}

	@Column(name = "week")
	public Boolean getWeek() {
		return this.week;
	}

	public void setWeek(Boolean week) {
		this.week = week;
	}

	@Column(name = "month")
	public Boolean getMonth() {
		return this.month;
	}

	public void setMonth(Boolean month) {
		this.month = month;
	}

}
