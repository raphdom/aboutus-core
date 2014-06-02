package com.jrdevel.aboutus.core.site.article;

import java.io.Serializable;
import java.util.Date;

import com.jrdevel.aboutus.core.common.view.Projection;

/**
 * @author Raphael Domingues
 *
 */
public class ArticleListView implements Serializable{
	
	private static final long serialVersionUID = -5329692834378102889L;
	private Integer id;
	private String title;
	private Date created;
	private Date publishUp;
	private Date publishDown;
	private int ordering;
	private int hits;
	private String author;
	private String categoryName;
	
	@Projection
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Projection
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Projection
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@Projection
	public Date getPublishUp() {
		return publishUp;
	}
	public void setPublishUp(Date publishUp) {
		this.publishUp = publishUp;
	}
	@Projection
	public Date getPublishDown() {
		return publishDown;
	}
	public void setPublishDown(Date publishDown) {
		this.publishDown = publishDown;
	}
	@Projection
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	@Projection
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	@Projection
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Projection(entityName="category.name")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
