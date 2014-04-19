package com.jrdevel.aboutus.core.common;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.constants.DAOConstants;
import com.jrdevel.aboutus.core.common.model.lists.translate.CivilStatusTranslate;
import com.jrdevel.aboutus.core.common.model.lists.translate.CountryTranslate;
import com.jrdevel.aboutus.core.dto.GenericValueTextDTO;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class ListDAOImpl extends AbstractGenericDAO<GenericValueTextDTO, Integer> implements ListDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
	}
	
	public List<GenericValueTextDTO> getList(int listType){
		switch(listType){
			case DAOConstants.COUNTRY_LIST:
				return getCountryList();
			case DAOConstants.CIVILSTATUS_LIST:
				return getCivilStatusList();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<GenericValueTextDTO> getCountryList(){
		List<GenericValueTextDTO> itens = new ArrayList<GenericValueTextDTO>();
		Criteria criteria = getSession().createCriteria(CountryTranslate.class);
		criteria.add(Restrictions.eq("langId", "pt_PT"));
		criteria.addOrder(Order.asc("text"));
		List<CountryTranslate> data = criteria.list();
		for (CountryTranslate bean: data){
			GenericValueTextDTO item = new GenericValueTextDTO();
			item.setValue(bean.getCountry().getId());
			item.setText(bean.getText());
			itens.add(item);
		}
		return itens;
	}
	
	@SuppressWarnings("unchecked")
	private List<GenericValueTextDTO> getCivilStatusList(){
		List<GenericValueTextDTO> itens = new ArrayList<GenericValueTextDTO>();
		Criteria criteria = getSession().createCriteria(CivilStatusTranslate.class);
		criteria.add(Restrictions.eq("langId", "pt_PT"));
		criteria.addOrder(Order.asc("text"));
		List<CivilStatusTranslate> data = criteria.list();
		for (CivilStatusTranslate bean: data){
			GenericValueTextDTO item = new GenericValueTextDTO();
			item.setValue(bean.getCivilStatus().getId());
			item.setText(bean.getText());
			itens.add(item);
		}
		return itens;
	}

}
