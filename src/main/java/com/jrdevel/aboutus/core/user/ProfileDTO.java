package com.jrdevel.aboutus.core.user;

import java.io.Serializable;

import com.jrdevel.aboutus.core.person.PersonDTO;

public class ProfileDTO implements Serializable{
	
	private static final long serialVersionUID = 6548590740401867795L;
	
	private UserDTO user;
	private PersonDTO person;
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public PersonDTO getPerson() {
		return person;
	}
	public void setPerson(PersonDTO person) {
		this.person = person;
	}
	
}
