package com.jrdevel.aboutus.core.church;

import com.jrdevel.aboutus.core.common.view.Projection;

/**
 * @author Raphael Domingues
 *
 */
public class ChurchView {
	
	private String name;

	@Projection
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
