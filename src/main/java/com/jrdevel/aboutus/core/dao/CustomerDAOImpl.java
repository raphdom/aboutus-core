package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Customer;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class CustomerDAOImpl extends AbstractGenericDAO<Customer, Integer> implements CustomerDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
	}
	

}
