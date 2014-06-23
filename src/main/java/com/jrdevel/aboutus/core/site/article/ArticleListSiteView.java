package com.jrdevel.aboutus.core.site.article;

import java.io.Serializable;

import com.jrdevel.aboutus.core.common.view.Projection;

/**
 * @author Raphael Domingues
 *
 */
public class ArticleListSiteView implements Serializable{
	
	private static final long serialVersionUID = -5329692834378102889L;
	private Integer id;
	private String title;
	private String introarticle;
	private Integer thumbId;
	
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
	public String getIntroarticle() {
		return introarticle;
	}
	public void setIntroarticle(String introarticle) {
		this.introarticle = introarticle;
	}
	@Projection(entityName="file.id")
	public Integer getThumbId() {
		return thumbId;
	}
	public void setThumbId(Integer thumbId) {
		this.thumbId = thumbId;
	}
	
	
	
}
