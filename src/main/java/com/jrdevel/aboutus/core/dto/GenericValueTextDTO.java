package com.jrdevel.aboutus.core.dto;

import java.io.Serializable;

/**
 * @author Raphael Domingues
 *
 */
public class GenericValueTextDTO implements Serializable{

	private static final long serialVersionUID = -4112522759204799523L;
	
	private String value;
	private String text;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
