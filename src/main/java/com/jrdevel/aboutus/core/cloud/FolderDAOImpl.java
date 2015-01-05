package com.jrdevel.aboutus.core.cloud;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Folder;
import com.jrdevel.aboutus.core.common.model.Group;
import com.jrdevel.aboutus.core.common.model.User;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class FolderDAOImpl extends AbstractGenericDAO<Folder, Integer> implements FolderDAO{
	
	@SuppressWarnings("unchecked")
	public List<Folder> getFoldersPermited(User user){
		Disjunction or = Restrictions.disjunction();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.createAlias("folderRoles", "role", Criteria.INNER_JOIN);
		Criterion crit1 = Restrictions.and(Restrictions.isNull("role.user"), Restrictions.isNull("role.group"));
		Criterion crit2 = Restrictions.eq("role.user.id", user.getId());
		Criterion crit3 = Restrictions.eq("customer.id", UserAuthenticatedManager.getCurrentCustomer().getId());
		or.add(crit1);
		or.add(crit2);
		
		for (Group group : user.getGroups()){
			or.add(Restrictions.eq("role.group.id", group.getId()));
		}
		crit.add(or);
		crit.add(crit3);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Folder> getFoldersByParent(Integer folderId){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("parent", folderId));
		return crit.list();
	}
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_FOLDER.getKey();
	}

	public String getObjectTitle(Folder entity) {
		return entity.getName();
	}

}
