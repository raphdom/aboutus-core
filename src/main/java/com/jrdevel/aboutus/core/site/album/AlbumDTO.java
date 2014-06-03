package com.jrdevel.aboutus.core.site.album;

import java.io.Serializable;
import java.util.List;

public class AlbumDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private Integer categoryId;
	private Integer thumbId;
	private String title;
	private String description;
	private Integer ordering;
	private boolean published;
	private List<ItemAlbumDTO> items;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getThumbId() {
		return thumbId;
	}
	public void setThumbId(Integer thumbId) {
		this.thumbId = thumbId;
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
	public Integer getOrdering() {
		return ordering;
	}
	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public List<ItemAlbumDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemAlbumDTO> items) {
		this.items = items;
	}
	
}
