package com.jrdevel.aboutus.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.constant.DAOConstants;
import com.jrdevel.aboutus.core.model.lists.translate.CivilStatusTranslate;
import com.jrdevel.aboutus.core.model.lists.translate.CountryTranslate;
import com.jrdevel.aboutus.core.util.GenericValueText;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class ListDAO extends GenericDAO<GenericValueText, Integer>{
	
	public void setExtraFilters(Criteria criteria) {
		
	}
	
	public List<GenericValueText> getList(int listType){
		switch(listType){
			case DAOConstants.COUNTRY_LIST:
				return getCountryList();
			case DAOConstants.CIVILSTATUS_LIST:
				return getCivilStatusList();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<GenericValueText> getCountryList(){
		List<GenericValueText> itens = new ArrayList<GenericValueText>();
		Criteria criteria = getSession().createCriteria(CountryTranslate.class);
		criteria.add(Restrictions.eq("langId", "pt_PT"));
		criteria.addOrder(Order.asc("text"));
		List<CountryTranslate> data = criteria.list();
		for (CountryTranslate bean: data){
			GenericValueText item = new GenericValueText();
			item.setValue(bean.getCountry().getId());
			item.setText(bean.getText());
			itens.add(item);
		}
		return itens;
	}
	
	@SuppressWarnings("unchecked")
	private List<GenericValueText> getCivilStatusList(){
		List<GenericValueText> itens = new ArrayList<GenericValueText>();
		Criteria criteria = getSession().createCriteria(CivilStatusTranslate.class);
		criteria.add(Restrictions.eq("langId", "pt_PT"));
		criteria.addOrder(Order.asc("text"));
		List<CivilStatusTranslate> data = criteria.list();
		for (CivilStatusTranslate bean: data){
			GenericValueText item = new GenericValueText();
			item.setValue(bean.getCivilStatus().getId());
			item.setText(bean.getText());
			itens.add(item);
		}
		return itens;
	}

}
