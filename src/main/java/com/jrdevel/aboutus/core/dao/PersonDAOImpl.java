package com.jrdevel.aboutus.core.dao;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.model.Person;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class PersonDAOImpl extends AbstractGenericDAO<Person, Integer> implements PersonDAO{
	
	public void setExtraFilters(Criteria criteria) {
		addCivilStatusCriteria(criteria);
		addCountryCriteria(criteria);
		addMemberType(criteria);
	}
	
	private void addCivilStatusCriteria(Criteria criteria){
		criteria.setFetchMode("civilStatus", FetchMode.JOIN);
		criteria.setFetchMode("civilStatusTranslates", FetchMode.JOIN);
		criteria.createAlias("civilStatus.civilStatusTranslates", "cvStat", Criteria.LEFT_JOIN);
		Criterion crit1 = Restrictions.eq("cvStat.langId", "pt_PT");
		Criterion crit2 = Restrictions.isNull("civilStatus");
		LogicalExpression orExp = Restrictions.or(crit1,crit2);
		criteria.add(orExp);
	}
	
	private void addCountryCriteria(Criteria criteria){
		criteria.setFetchMode("country", FetchMode.JOIN);
		criteria.setFetchMode("countryTranslates", FetchMode.JOIN);
		criteria.createAlias("country.countryTranslates", "countryTrans", Criteria.LEFT_JOIN);
		Criterion crit1 = Restrictions.eq("countryTrans.langId", "pt_PT");
		Criterion crit2 = Restrictions.isNull("country");
		LogicalExpression orExp = Restrictions.or(crit1,crit2);
		criteria.add(orExp);
	}
	
	private void addMemberType(Criteria criteria){
		criteria.setFetchMode("memberType", FetchMode.JOIN);
		criteria.setFetchMode("memberTypeTranslates", FetchMode.JOIN);
		criteria.createAlias("memberType.memberTypeTranslates", "memberTypeTrans", Criteria.LEFT_JOIN);
		Criterion crit1 = Restrictions.eq("memberTypeTrans.langId", "pt_PT");
		Criterion crit2 = Restrictions.isNull("memberType");
		LogicalExpression orExp = Restrictions.or(crit1,crit2);
		criteria.add(orExp);
	}

}
