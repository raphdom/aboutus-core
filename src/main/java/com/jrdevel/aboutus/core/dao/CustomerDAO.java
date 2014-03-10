package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.model.Customer;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class CustomerDAO extends GenericDAO<Customer, Integer>{
	
	public void setExtraFilters(Criteria criteria) {
		
	}
	

}
