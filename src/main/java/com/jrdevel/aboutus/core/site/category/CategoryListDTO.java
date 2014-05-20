package com.jrdevel.aboutus.core.site.category;

import java.io.Serializable;

import com.jrdevel.aboutus.core.dto.NodeDTO;

/**
 * @author Raphael Domingues
 *
 */
public class CategoryListDTO extends NodeDTO implements Serializable{

	private static final long serialVersionUID = 2513871739789525803L;
	
	private String description;
	private int position;
	private boolean publishedAlbuns;
	private boolean publishedVideos;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
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
	
}
