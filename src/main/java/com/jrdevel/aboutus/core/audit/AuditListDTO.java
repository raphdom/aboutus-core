package com.jrdevel.aboutus.core.audit;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Raphael Domingues
 *
 */
public class AuditListDTO implements Serializable{

	private static final long serialVersionUID = -56033427120000776L;
	private Integer id;
	private Date actionDate;
	private String action;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
