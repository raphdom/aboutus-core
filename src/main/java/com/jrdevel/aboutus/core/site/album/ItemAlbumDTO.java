package com.jrdevel.aboutus.core.site.album;

import java.io.Serializable;

public class ItemAlbumDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private String title;
	private String description;
	private int ordering;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	
}