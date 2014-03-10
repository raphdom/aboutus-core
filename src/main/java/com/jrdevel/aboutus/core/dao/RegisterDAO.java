package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Register;
import com.jrdevel.aboutus.core.model.User;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class RegisterDAO extends GenericDAO<Register, Integer>{
	
	public void setExtraFilters(Criteria criteria) {
		
	}
	
	/**
	 * Get User from database
	 * @return user
	 */
	public boolean existEmailRegistered(String email) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("email", email));
		Register register = (Register) criteria.uniqueResult(); 
		return register != null;
	}
	
	public Register getRegisterByUser(User user){
		Criterion crit = Restrictions.eq("user.id", user.getId());
		return findUniqueByCriteria(crit);
	}
	

}
