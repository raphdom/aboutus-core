package com.jrdevel.aboutus.core.user;

import java.io.Serializable;
import java.util.List;

public class GroupDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private Integer id;
	private String name;
	private List<Integer> permissions;
	
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
	public List<Integer> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Integer> permission) {
		this.permissions = permission;
	}
	
}
