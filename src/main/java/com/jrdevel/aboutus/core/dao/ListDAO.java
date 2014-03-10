package com.jrdevel.aboutus.core.dao;

import java.util.List;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.util.GenericValueText;

public interface ListDAO extends GenericDAO<GenericValueText, Integer>{
	
	public List<GenericValueText> getList(int listType);

}
