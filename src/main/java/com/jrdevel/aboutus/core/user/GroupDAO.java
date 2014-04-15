package com.jrdevel.aboutus.core.user;

import java.util.List;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Group;

public interface GroupDAO extends GenericDAO<Group, Integer>{
	
	public Group getGroupById(int id);
	
	public List<Integer> getUserGroups(Integer userId);

}
