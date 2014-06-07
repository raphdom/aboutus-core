package com.jrdevel.aboutus.core.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Group;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class GroupDAOImpl extends AbstractGenericDAO<Group, Integer> implements GroupDAO{
	
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}
	
	public Group getGroupById(int id){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.setFetchMode("permissions", FetchMode.JOIN);
		crit.add(Restrictions.eq("id", id));
		return (Group) crit.uniqueResult();
	}


	public List<Integer> getUserGroups(Integer userId) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("user.id", userId));
		return crit.list();
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_GROUP.getKey();
	}

	public String getObjectTitle(Group entity) {
		return entity.getName();
	}

}
