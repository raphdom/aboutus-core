package com.jrdevel.aboutus.core.site.banner;

import java.io.Serializable;
import java.util.Date;

public class BannerDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private String name;
	private String alias;
	private String link;
	private Date publishUp;
	private Date publishDown;
	private Integer ordering;
	private Integer bannerId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getPublishUp() {
		return publishUp;
	}
	public void setPublishUp(Date publishUp) {
		this.publishUp = publishUp;
	}
	public Date getPublishDown() {
		return publishDown;
	}
	public void setPublishDown(Date publishDown) {
		this.publishDown = publishDown;
	}
	public Integer getOrdering() {
		return ordering;
	}
	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}
	public Integer getBannerId() {
		return bannerId;
	}
	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}
	
}