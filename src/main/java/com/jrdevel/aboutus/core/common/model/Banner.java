package com.jrdevel.aboutus.core.common.model;

// Generated 5/mai/2014 23:56:03 by Hibernate Tools 3.4.0.CR1

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
 * Banner generated by hbm2java
 */
@Entity
@Table(name = "tbl_banners")
public class Banner implements java.io.Serializable {

	private Integer id;
	private File file;
	private String name;
	private String alias;
	private String link;
	private Date publishUp;
	private Date publishDown;
	private int ordering;

	public Banner() {
	}

	public Banner(String name, Date publishUp, int ordering) {
		this.name = name;
		this.publishUp = publishUp;
		this.ordering = ordering;
	}

	public Banner(File file, String name, String alias, String link,
			Date publishUp, Date publishDown, int ordering) {
		this.file = file;
		this.name = name;
		this.alias = alias;
		this.link = link;
		this.publishUp = publishUp;
		this.publishDown = publishDown;
		this.ordering = ordering;
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
	@JoinColumn(name = "image")
	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "alias")
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "link", length = 65535)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
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

}
