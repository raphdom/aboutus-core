package com.jrdevel.aboutus.core.church;

import com.jrdevel.aboutus.core.common.view.Projection;

/**
 * @author Raphael Domingues
 *
 */
public class ChurchView {
	
	private Integer id;
	private String name;
	
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

}
