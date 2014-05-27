package com.jrdevel.aboutus.core.common.model;

// Generated 27/mai/2014 21:32:42 by Hibernate Tools 3.4.0.CR1

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Event generated by hbm2java
 */
@Entity
@Table(name = "tbl_events")
public class Event implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private File file;
	private Category category;
	private Date startsOn;
	private Date endsOn;
	private Date startsAt;
	private Date endsAt;
	private String frequency;
	private boolean separation;
	private Boolean count;
	private Date until;
	private String timezoneName;
	private boolean status;
	private String what;
	private String description;
	private String where;
	private boolean published;
	private Set<EventRecurrence> eventRecurrences = new HashSet<EventRecurrence>(
			0);
	private Set<EventCancellation> eventCancellations = new HashSet<EventCancellation>(
			0);

	public Event() {
	}

	public Event(Customer customer, String frequency, boolean separation,
			String timezoneName, boolean status, String what, boolean published) {
		this.customer = customer;
		this.frequency = frequency;
		this.separation = separation;
		this.timezoneName = timezoneName;
		this.status = status;
		this.what = what;
		this.published = published;
	}

	public Event(Customer customer, File file, Category category,
			Date startsOn, Date endsOn, Date startsAt, Date endsAt,
			String frequency, boolean separation, Boolean count, Date until,
			String timezoneName, boolean status, String what,
			String description, String where, boolean published,
			Set<EventRecurrence> eventRecurrences,
			Set<EventCancellation> eventCancellations) {
		this.customer = customer;
		this.file = file;
		this.category = category;
		this.startsOn = startsOn;
		this.endsOn = endsOn;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.frequency = frequency;
		this.separation = separation;
		this.count = count;
		this.until = until;
		this.timezoneName = timezoneName;
		this.status = status;
		this.what = what;
		this.description = description;
		this.where = where;
		this.published = published;
		this.eventRecurrences = eventRecurrences;
		this.eventCancellations = eventCancellations;
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
	@JoinColumn(name = "thumbId")
	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "starts_on", length = 10)
	public Date getStartsOn() {
		return this.startsOn;
	}

	public void setStartsOn(Date startsOn) {
		this.startsOn = startsOn;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ends_on", length = 10)
	public Date getEndsOn() {
		return this.endsOn;
	}

	public void setEndsOn(Date endsOn) {
		this.endsOn = endsOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "starts_at", length = 19)
	public Date getStartsAt() {
		return this.startsAt;
	}

	public void setStartsAt(Date startsAt) {
		this.startsAt = startsAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ends_at", length = 19)
	public Date getEndsAt() {
		return this.endsAt;
	}

	public void setEndsAt(Date endsAt) {
		this.endsAt = endsAt;
	}

	@Column(name = "frequency", nullable = false, length = 7)
	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	@Column(name = "separation", nullable = false)
	public boolean isSeparation() {
		return this.separation;
	}

	public void setSeparation(boolean separation) {
		this.separation = separation;
	}

	@Column(name = "count")
	public Boolean getCount() {
		return this.count;
	}

	public void setCount(Boolean count) {
		this.count = count;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "until", length = 10)
	public Date getUntil() {
		return this.until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

	@Column(name = "timezone_name", nullable = false)
	public String getTimezoneName() {
		return this.timezoneName;
	}

	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "what", nullable = false)
	public String getWhat() {
		return this.what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "where")
	public String getWhere() {
		return this.where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	@Column(name = "published", nullable = false)
	public boolean isPublished() {
		return this.published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	public Set<EventRecurrence> getEventRecurrences() {
		return this.eventRecurrences;
	}

	public void setEventRecurrences(Set<EventRecurrence> eventRecurrences) {
		this.eventRecurrences = eventRecurrences;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	public Set<EventCancellation> getEventCancellations() {
		return this.eventCancellations;
	}

	public void setEventCancellations(Set<EventCancellation> eventCancellations) {
		this.eventCancellations = eventCancellations;
	}

}
