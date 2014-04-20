package com.jrdevel.aboutus.core.common;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.model.lists.translate.CivilStatusTranslate;
import com.jrdevel.aboutus.core.common.model.lists.translate.CountryTranslate;
import com.jrdevel.aboutus.core.common.model.lists.translate.MemberTypeTranslate;
import com.jrdevel.aboutus.core.dto.GenericValueTextDTO;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class ListDAOImpl extends AbstractGenericDAO<GenericValueTextDTO, Integer> implements ListDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
	}
	
	public List<GenericValueTextView> getCountryList(){
		return getList(CountryTranslate.class, "country");
	}
	
	public List<GenericValueTextView> getCivilStatusList(){
		return getList(CivilStatusTranslate.class, "civilStatus");
	}

	public List<GenericValueTextView> getMemberTypeList() {
		return getList(MemberTypeTranslate.class, "memberType");
	}
	
	@SuppressWarnings("unchecked")
	private List<GenericValueTextView> getList(Class<?> clazz, String listName){
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.add(Restrictions.eq("langId", "pt_PT"));
		criteria.addOrder(Order.asc("text"));
		
		criteria.setProjection( Projections.projectionList()
                .add( Projections.property(listName+".id"), "value" )
                .add( Projections.property("text"), "text" ));
		
		criteria.setResultTransformer(Transformers.aliasToBean(GenericValueTextView.class));
		
		List<GenericValueTextView> list = criteria.list();
		
		return list;
	}

}
