package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Group;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class GroupDAO extends GenericDAO<Group, Integer>{
	
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}
	
	
	public Group getGroupById(int id){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.setFetchMode("permissions", FetchMode.JOIN);
		crit.add(Restrictions.eq("id", id));
		return (Group) crit.uniqueResult();
	}

}
