package com.jrdevel.aboutus.core.authentication;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.model.Register;
import com.jrdevel.aboutus.core.common.model.User;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class RegisterDAOImpl extends AbstractGenericDAO<Register, Integer> implements RegisterDAO{
	
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
