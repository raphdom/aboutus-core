package com.jrdevel.aboutus.core.common;

import java.io.Serializable;

/**
 * @author Raphael Domingues
 *
 */
public class GenericIdTextView implements Serializable{

	
	private static final long serialVersionUID = 896343188555983854L;
	
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
