package com.jrdevel.aboutus.core.common;

import java.util.List;

import net.aboutchurch.common.dto.GenericValueTextDTO;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.helper.MessageHelper;
import com.jrdevel.aboutus.core.common.model.lists.translate.CivilStatusTranslate;
import com.jrdevel.aboutus.core.common.model.lists.translate.ContactTypeTranslate;
import com.jrdevel.aboutus.core.common.model.lists.translate.CountryTranslate;
import com.jrdevel.aboutus.core.common.model.lists.translate.MemberTypeTranslate;

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
	
	public List<GenericValueTextView> getContactTypeList() {
		return getList(ContactTypeTranslate.class, "contactType");
	}
	
	@SuppressWarnings("unchecked")
	private List<GenericValueTextView> getList(Class<?> clazz, String listName){
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.add(Restrictions.eq("langId", MessageHelper.getCurrentLocale().toString()));
		criteria.addOrder(Order.asc("text"));
		
		criteria.setProjection( Projections.projectionList()
                .add( Projections.property(listName+".id"), "value" )
                .add( Projections.property("text"), "text" ));
		
		criteria.setResultTransformer(Transformers.aliasToBean(GenericValueTextView.class));
		
		List<GenericValueTextView> list = criteria.list();
		
		return list;
	}

	public String getObjectName() {
		return null;
	}

	public String getObjectTitle(GenericValueTextDTO entity) {
		return null;
	}

}
