package com.jrdevel.aboutus.core.calendar;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Raphael Domingues
 *
 */
public class EventListView implements Serializable{
	
	private static final long serialVersionUID = -5329692834378102889L;
	private Integer id;
	private Integer cid;
	private Integer eventId;
	private Date startsOn;
	private Date endsOn;
	private Date startsAt;
	private Date endsAt;
	private String frequency;
	private boolean separation;
	private Integer count;
	private Date until;	
	private String timezoneName;
	private Date eventStart;
	private String what;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getStartsOn() {
		return startsOn;
	}
	public void setStarts_on(Date startsOn) {
		this.startsOn = startsOn;
	}
	public Date getEndsOn() {
		return endsOn;
	}
	public void setEnds_on(Date endsOn) {
		this.endsOn = endsOn;
	}
	public Date getStartsAt() {
		return startsAt;
	}
	public void setStarts_at(Date startsAt) {
		this.startsAt = startsAt;
	}
	public Date getEndsAt() {
		return endsAt;
	}
	public void setEnds_at(Date endsAt) {
		this.endsAt = endsAt;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public boolean isSeparation() {
		return separation;
	}
	public void setSeparation(boolean separation) {
		this.separation = separation;
	}
	public Date getUntil() {
		return until;
	}
	public void setUntil(Date until) {
		this.until = until;
	}
	public String getTimezoneName() {
		return timezoneName;
	}
	public void setTimezone_name(String timezoneName) {
		this.timezoneName = timezoneName;
	}
	public Date getEventStart() {
		return eventStart;
	}
	public void setEvent_start(Date eventStart) {
		this.eventStart = eventStart;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEvent_id(Integer eventId) {
		this.eventId = eventId;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
}
