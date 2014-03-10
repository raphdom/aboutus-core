package com.jrdevel.aboutus.core.dao;

import com.jrdevel.aboutus.core.model.Group;

public interface GroupDAO extends GenericDAO<Group, Integer>{
	
	public Group getGroupById(int id);

}
