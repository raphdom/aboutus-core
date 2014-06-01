package com.jrdevel.aboutus.core.calendar;

import java.io.Serializable;
import java.util.Date;

public class EventDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private String title;
	private Integer cid;
	private Integer eid;
	private Integer catId;
	private Integer thbId;
	private Date start;
	private Date end;
	private Integer count;
	private String loc; 
	private Integer separation;
	private boolean ad;
	private String frequency;
	private String notes;
	private Date until;
	private Integer[] weekDays;
	private String url;
	private boolean published;
	
	private String rrule;
	private String rem;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public Integer getThbId() {
		return thbId;
	}
	public void setThbId(Integer thbId) {
		this.thbId = thbId;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Integer getSeparation() {
		return separation;
	}
	public void setSeparation(Integer separation) {
		this.separation = separation;
	}
	public boolean isAd() {
		return ad;
	}
	public void setAd(boolean ad) {
		this.ad = ad;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getUntil() {
		return until;
	}
	public void setUntil(Date until) {
		this.until = until;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRrule() {
		return rrule;
	}
	public void setRrule(String rrule) {
		this.rrule = rrule;
	}
	public String getRem() {
		return rem;
	}
	public void setRem(String rem) {
		this.rem = rem;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public Integer[] getWeekDays() {
		return weekDays;
	}
	public void setWeekDays(Integer[] weekDays) {
		this.weekDays = weekDays;
	}
	
}
