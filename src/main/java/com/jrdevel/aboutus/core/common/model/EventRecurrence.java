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
 * EventRecurrence generated by hbm2java
 */
@Entity
@Table(name = "tbl_event_recurrences")
public class EventRecurrence implements java.io.Serializable {

	private Integer id;
	private Event event;
	private int day;
	private Integer week;
	private Integer month;

	public EventRecurrence() {
	}

	public EventRecurrence(Event event, int day) {
		this.event = event;
		this.day = day;
	}

	public EventRecurrence(Event event, int day, Integer week, Integer month) {
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
	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Column(name = "week")
	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	@Column(name = "month")
	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
