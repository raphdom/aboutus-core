package com.jrdevel.aboutus.core.site.category;

import java.io.Serializable;

/**
 * @author Raphael Domingues
 *
 */
public class CategoryListDTO implements Serializable{

	private static final long serialVersionUID = -56033427120000776L;
	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
