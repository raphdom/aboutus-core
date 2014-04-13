package com.jrdevel.aboutus.core.user;

import java.io.Serializable;
import java.util.List;

import com.jrdevel.aboutus.core.common.view.Projection;

/**
 * @author Raphael Domingues
 *
 */
public class UserView implements Serializable{

	private static final long serialVersionUID = 5971822657932830407L;
	private Integer id;
	private String email;
	private Integer churchId;
	private Integer personId;
	private List<Integer> permission;
	private List<Integer> group;
	
	@Projection
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Projection
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Projection(entityName="church.id")
	public Integer getChurchId() {
		return churchId;
	}
	public void setChurchId(Integer churchId) {
		this.churchId = churchId;
	}
	@Projection(entityName="person.id")
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public List<Integer> getGroup() {
		return group;
	}
	public void setGroup(List<Integer> group) {
		this.group = group;
	}
	public List<Integer> getPermission() {
		return permission;
	}
	public void setPermission(List<Integer> permission) {
		this.permission = permission;
	} 

}
