package com.jrdevel.aboutus.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Folder;
import com.jrdevel.aboutus.core.model.Group;
import com.jrdevel.aboutus.core.model.User;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class FolderDAO extends GenericDAO<Folder, Integer>{
	
	@SuppressWarnings("unchecked")
	public List<Folder> getFoldersPermited(User user){
		Disjunction or = Restrictions.disjunction();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.createAlias("folderRoles", "role", Criteria.INNER_JOIN);
		Criterion crit1 = Restrictions.and(Restrictions.isNull("role.user"), Restrictions.isNull("role.group"));
		Criterion crit2 = Restrictions.eq("role.user.id", user.getId());
		or.add(crit1);
		or.add(crit2);
		for (Group group : user.getGroups()){
			or.add(Restrictions.eq("role.group.id", group.getId()));
		}
		crit.add(or);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}

}
