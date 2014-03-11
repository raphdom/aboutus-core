package com.jrdevel.aboutus.core.common;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.GenericValueText;

public interface ListDAO extends GenericDAO<GenericValueText, Integer>{
	
	public List<GenericValueText> getList(int listType);

}
