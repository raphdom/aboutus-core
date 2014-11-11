package com.jrdevel.aboutus.core.site.category;

import java.io.Serializable;

import com.jrdevel.aboutus.core.common.view.Projection;

/**
 * @author Raphael Domingues
 *
 */
public class CategoryListSiteView implements Serializable{
	
	private static final long serialVersionUID = -5329692834378102889L;
	private Integer id;
	private String name;
	private Integer thumbId;
	
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
	@Projection(entityName="file.id")
	public Integer getThumbId() {
		return thumbId;
	}
	public void setThumbId(Integer thumbId) {
		this.thumbId = thumbId;
	}
	
}
