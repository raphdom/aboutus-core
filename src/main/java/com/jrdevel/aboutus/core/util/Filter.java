package com.jrdevel.aboutus.core.util;

import java.io.Serializable;

/**
 * @author Raphael Domingues
 *
 */
public class Filter implements Serializable{
	
	private static final long serialVersionUID = -8395455676063883733L;
	
	private String property;
	private String value;
	private String type;
	private String operator;
	
	public Filter(){
		
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
