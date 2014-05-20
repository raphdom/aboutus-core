package com.jrdevel.aboutus.core.site.category;

import java.io.Serializable;

import com.jrdevel.aboutus.core.common.view.Projection;

/**
 * @author Raphael Domingues
 *
 */
public class CategoryListView implements Serializable{
	
	private static final long serialVersionUID = -5329692834378102889L;
	private Integer id;
	private String name;
	private int parent;
	private String description;
	private int position;
	private boolean publishedAlbuns;
	private boolean publishedVideos;
	
	@Projection
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Projection
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Projection
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	@Projection
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Projection
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	@Projection
	public boolean isPublishedAlbuns() {
		return publishedAlbuns;
	}
	public void setPublishedAlbuns(boolean publishedAlbuns) {
		this.publishedAlbuns = publishedAlbuns;
	}
	@Projection
	public boolean isPublishedVideos() {
		return publishedVideos;
	}
	public void setPublishedVideos(boolean publishedVideos) {
		this.publishedVideos = publishedVideos;
	}
}
