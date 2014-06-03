package com.jrdevel.aboutus.core.site.video;

import java.io.Serializable;
import java.util.Date;

import com.jrdevel.aboutus.core.dto.NodeDTO;

/**
 * @author Raphael Domingues
 *
 */
public class VideoListDTO extends NodeDTO implements Serializable{

	private static final long serialVersionUID = 2513871739789525803L;
	
	private Integer id;
	private String title;
	private int ordering;
	private String url;
	private boolean published;
	private String categoryName;
	
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
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}