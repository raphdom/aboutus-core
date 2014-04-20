package com.jrdevel.aboutus.core.common;

import java.io.Serializable;

/**
 * @author Raphael Domingues
 *
 */
public class GenericValueTextView implements Serializable{
	
	private static final long serialVersionUID = 286079821807369390L;
	
	private String value;
	private String text;
	
	public String getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = Integer.toString(value);
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
