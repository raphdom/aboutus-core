package com.jrdevel.aboutus.core.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private String email;
	private Integer personId;
	private Integer churchId;
	private List<Integer> permissions;
	private List<Integer> groups;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Integer getChurchId() {
		return churchId;
	}
	public void setChurchId(Integer churchId) {
		this.churchId = churchId;
	}
	public List<Integer> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Integer> permission) {
		this.permissions = permission;
	}
	public List<Integer> getGroups() {
		return groups;
	}
	public void setGroups(List<Integer> group) {
		this.groups = group;
	}
	
}
