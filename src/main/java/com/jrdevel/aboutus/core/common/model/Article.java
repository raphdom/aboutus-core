package com.jrdevel.aboutus.core.common.model;

// Generated 2/jun/2014 21:02:16 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Article generated by hbm2java
 */
@Entity
@Table(name = "tbl_article")
public class Article implements java.io.Serializable {

	private Integer id;
	private Customer customer;
	private Category category;
	private File file;
	private String introarticle;
	private String article;
	private String title;
	private Date created;
	private Date publishUp;
	private Date publishDown;
	private int ordering;
	private int hits;
	private boolean homepage;
	private String author;

	public Article() {
	}

	public Article(Customer customer, Category category, String article,
			String title, Date created, Date publishUp, int ordering, int hits,
			boolean homepage) {
		this.customer = customer;
		this.category = category;
		this.article = article;
		this.title = title;
		this.created = created;
		this.publishUp = publishUp;
		this.ordering = ordering;
		this.hits = hits;
		this.homepage = homepage;
	}

	public Article(Customer customer, Category category, File file,
			String introarticle, String article, String title, Date created,
			Date publishUp, Date publishDown, int ordering, int hits,
			boolean homepage, String author) {
		this.customer = customer;
		this.category = category;
		this.file = file;
		this.introarticle = introarticle;
		this.article = article;
		this.title = title;
		this.created = created;
		this.publishUp = publishUp;
		this.publishDown = publishDown;
		this.ordering = ordering;
		this.hits = hits;
		this.homepage = homepage;
		this.author = author;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thumb")
	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Column(name = "introarticle", length = 16777215)
	public String getIntroarticle() {
		return this.introarticle;
	}

	public void setIntroarticle(String introarticle) {
		this.introarticle = introarticle;
	}

	@Column(name = "article", nullable = false, length = 16777215)
	public String getArticle() {
		return this.article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_up", nullable = false, length = 19)
	public Date getPublishUp() {
		return this.publishUp;
	}

	public void setPublishUp(Date publishUp) {
		this.publishUp = publishUp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_down", length = 19)
	public Date getPublishDown() {
		return this.publishDown;
	}

	public void setPublishDown(Date publishDown) {
		this.publishDown = publishDown;
	}

	@Column(name = "ordering", nullable = false)
	public int getOrdering() {
		return this.ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	@Column(name = "hits", nullable = false)
	public int getHits() {
		return this.hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	@Column(name = "homepage", nullable = false)
	public boolean isHomepage() {
		return this.homepage;
	}

	public void setHomepage(boolean homepage) {
		this.homepage = homepage;
	}

	@Column(name = "author", length = 100)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
