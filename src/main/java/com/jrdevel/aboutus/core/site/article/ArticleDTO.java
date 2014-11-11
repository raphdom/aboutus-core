package com.jrdevel.aboutus.core.site.article;

import java.io.Serializable;
import java.util.Date;

public class ArticleDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private String title;
	private Date created;
	private Date publishUp;
	private Date publishDown;
	private int ordering;
	private int hits;
	private String author;
	private Integer categoryId;
	private Integer thumbId;
	private String introarticle;
	private String article; 
	private boolean homepage;
	
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
	public Integer getThumbId() {
		return thumbId;
	}
	public void setThumbId(Integer thumbId) {
		this.thumbId = thumbId;
	}
	public String getIntroarticle() {
		return introarticle;
	}
	public void setIntroarticle(String introarticle) {
		this.introarticle = introarticle;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public boolean isHomepage() {
		return homepage;
	}
	public void setHomepage(boolean homepage) {
		this.homepage = homepage;
	}
	
}
