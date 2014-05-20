package com.jrdevel.aboutus.core.site.category;

import java.io.Serializable;

public class CategoryDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private String text;
	private int parentId;
	private int position;
	private String description;
	private boolean publishedAlbuns;
	private boolean publishedVideos;
	private int thumbId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPublishedAlbuns() {
		return publishedAlbuns;
	}
	public void setPublishedAlbuns(boolean publishedAlbuns) {
		this.publishedAlbuns = publishedAlbuns;
	}
	public boolean isPublishedVideos() {
		return publishedVideos;
	}
	public void setPublishedVideos(boolean publishedVideos) {
		this.publishedVideos = publishedVideos;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getThumbId() {
		return thumbId;
	}
	public void setThumbId(int thumbId) {
		this.thumbId = thumbId;
	}
	
}
