package com.jrdevel.aboutus.core.authentication;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.model.Customer;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class CustomerDAOImpl extends AbstractGenericDAO<Customer, Integer> implements CustomerDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
	}

	public String getObjectName() {
		return null;
	}

	public String getObjectTitle(Customer entity) {
		return null;
	}
	

}
