package com.jrdevel.aboutus.core.site.banner;

import java.io.Serializable;
import java.util.Date;

import com.jrdevel.aboutus.core.common.view.Projection;

/**
 * @author Raphael Domingues
 *
 */
public class BannerListView implements Serializable{
	
	private static final long serialVersionUID = -5329692834378102889L;
	private Integer id;
	private String name;
	private String alias;
	private String link;
	private Date publishUp;
	private Date publishDown;
	private Integer ordering;
	
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@Projection
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
	public Integer getOrdering() {
		return ordering;
	}
	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}
	
}
