package com.jrdevel.aboutus.core.person;

import java.io.Serializable;

/**
 * @author Raphael Domingues
 *
 */
public class PersonComboListView implements Serializable{

	private static final long serialVersionUID = 7933043430371238089L;
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
