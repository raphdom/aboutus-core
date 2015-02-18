package com.jrdevel.aboutus.core.common.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
	private int separation;
	private Integer count;
	private Date until;
	private String timezoneName;
	private Boolean status;
	private String what;
	private String description;
	private String location;
	private boolean published;
	private int calendarId;
	private Set<EventRecurrence> eventRecurrences = new HashSet<EventRecurrence>(
			0);
	private Set<EventCancellation> eventCancellations = new HashSet<EventCancellation>(
			0);
	private Set<EventPeople> eventPeoples = new HashSet<EventPeople>(0);

	public Event() {
	}

	public Event(String frequency, int separation, String what,
			boolean published, int calendarId) {
		this.frequency = frequency;
		this.separation = separation;
		this.what = what;
		this.published = published;
		this.calendarId = calendarId;
	}

	public Event(Customer customer, File file, Category category,
			Date startsOn, Date endsOn, Date startsAt, Date endsAt,
			String frequency, int separation, Integer count, Date until,
			String timezoneName, Boolean status, String what,
			String description, String location, boolean published,
			int calendarId, Set<EventRecurrence> eventRecurrences,
			Set<EventCancellation> eventCancellations,
			Set<EventPeople> eventPeoples) {
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
		this.location = location;
		this.published = published;
		this.calendarId = calendarId;
		this.eventRecurrences = eventRecurrences;
		this.eventCancellations = eventCancellations;
		this.eventPeoples = eventPeoples;
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
	public int getSeparation() {
		return this.separation;
	}

	public void setSeparation(int separation) {
		this.separation = separation;
	}

	@Column(name = "count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
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

	@Column(name = "timezone_name")
	public String getTimezoneName() {
		return this.timezoneName;
	}

	public void setTimezoneName(String timezoneName) {
		this.timezoneName = timezoneName;
	}

	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
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

	@Column(name = "location")
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "published", nullable = false)
	public boolean isPublished() {
		return this.published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Column(name = "calendarId", nullable = false)
	public int getCalendarId() {
		return this.calendarId;
	}

	public void setCalendarId(int calendarId) {
		this.calendarId = calendarId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<EventPeople> getEventPeoples() {
		return this.eventPeoples;
	}

	public void setEventPeoples(Set<EventPeople> eventPeoples) {
		this.eventPeoples = eventPeoples;
	}

}
