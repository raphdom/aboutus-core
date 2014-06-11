package com.jrdevel.aboutus.core.site.article;

import java.io.Serializable;
import java.util.Date;

import com.jrdevel.aboutus.core.dto.NodeDTO;

/**
 * @author Raphael Domingues
 *
 */
public class ArticleListDTO extends NodeDTO implements Serializable{

	private static final long serialVersionUID = 2513871739789525803L;
	
	private Integer id;
	private String title;
	private Date created;
	private Date publishUp;
	private Date publishDown;
	private int ordering;
	private int hits;
	private String author;
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
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
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}