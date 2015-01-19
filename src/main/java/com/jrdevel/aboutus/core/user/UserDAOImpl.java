package com.jrdevel.aboutus.core.user;

import net.aboutchurch.common.to.ListResult;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.User;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class UserDAOImpl extends AbstractGenericDAO<User, Integer> implements UserDAO{
	
	/**
	 * Get User from database
	 * @return user
	 */
	public User getUserByEmail(String email) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("block", false));
		criteria.setFetchMode("groups", FetchMode.JOIN);
		criteria.setFetchMode("permissions", FetchMode.JOIN);
		criteria.setFetchMode("person", FetchMode.JOIN);
		criteria.setFetchMode("church", FetchMode.JOIN);
		criteria.setFetchMode("customer", FetchMode.JOIN);
		User user = (User) criteria.uniqueResult(); 
		return user;
	}
	
	public boolean existEmailRegistered(String email) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("email", email));
		User user = (User) criteria.uniqueResult(); 
		return user != null;
	}
	
	public void setExtraFilters(Criteria criteria) {
		
		criteria.createAlias("person", "person", Criteria.LEFT_JOIN);
		criteria.createAlias("church", "church", Criteria.LEFT_JOIN);
		
	}
	
	public User getUserById(int id){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.setFetchMode("person", FetchMode.JOIN);
		crit.setFetchMode("church", FetchMode.JOIN);
		crit.setFetchMode("groups", FetchMode.JOIN);
		crit.setFetchMode("permissions", FetchMode.JOIN);
		crit.add(Restrictions.eq("id", id));
		return (User) crit.uniqueResult();
	}
	
	public User getUserSimpleById(int id){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.setFetchMode("groups", FetchMode.JOIN);
		crit.setFetchMode("permissions", FetchMode.JOIN);
		crit.add(Restrictions.eq("id", id));
		return (User) crit.uniqueResult();
	}
	
	public ListResult<UserListView> getUsersView(){
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.createAlias("person", "person", Criteria.LEFT_JOIN);
		criteria.createAlias("church", "church", Criteria.LEFT_JOIN);
		
		criteria.setProjection( Projections.projectionList()
                .add( Projections.property("email"), "email" )
                .add( Projections.property("person.name"), "personName" ));
		
		criteria.setResultTransformer(Transformers.aliasToBean(UserListView.class));
		return new ListResult<UserListView>(criteria.list(), 15);
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_USER.getKey();
	}

	public String getObjectTitle(User entity) {
		return entity.getEmail();
	}

}
