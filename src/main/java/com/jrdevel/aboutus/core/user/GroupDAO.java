package com.jrdevel.aboutus.core.user;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Group;

public interface GroupDAO extends GenericDAO<Group, Integer>{
	
	public Group getGroupById(int id);

}
