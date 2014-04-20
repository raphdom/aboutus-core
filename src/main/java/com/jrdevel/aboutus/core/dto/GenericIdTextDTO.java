package com.jrdevel.aboutus.core.dto;

import java.io.Serializable;

/**
 * @author Raphael Domingues
 *
 */
public class GenericIdTextDTO implements Serializable{

	private static final long serialVersionUID = -4112522759204799523L;
	
	private Integer value;
	private String text;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
