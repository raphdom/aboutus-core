package com.jrdevel.aboutus.core.person;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.model.Person;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class PersonDAOImpl extends AbstractGenericDAO<Person, Integer> implements PersonDAO{
	
	@SuppressWarnings("unchecked")
	public void teste(){
		try{
			
			ProjectionList result = Projections.projectionList();
			result.add(Projections.property("id"), "id");
			result.add(Projections.property("name"), "name");
			//result.add(Projections.property("cvStat.text"), "civilStatus");
			Criteria crit1 = getSession().createCriteria(Person.class);
//			addCivilStatusCriteria1(crit1);
//			addCivilStatusCriteria2(crit1);
//			addCivilStatusCriteria3(crit1);
//			addCivilStatusCriteria4(crit1);
			crit1.setProjection(result);
			
			crit1.setResultTransformer(Transformers.aliasToBean(PersonView.class));
			
			List<PersonView> persons = crit1.list();
			
			String teste = "Raphael";
			
			teste = teste + persons.toString();
			
			
		}catch(Throwable e ){
		}
	}
	
	public void setExtraFilters(Criteria criteria) {
		addCivilStatusCriteria(criteria);
		addCountryCriteria(criteria);
		addMemberType(criteria);
		
	}
	
	private void addCivilStatusCriteria(Criteria criteria){
		criteria.createAlias("civilStatus", "civilStatus", Criteria.INNER_JOIN);
		criteria.createAlias("civilStatus.civilStatusTranslates", "cvStatTrans", Criteria.LEFT_JOIN);
		Criterion crit1 = Restrictions.eq("cvStatTrans.langId", "pt_PT");
		Criterion crit2 = Restrictions.isNull("civilStatus");
		LogicalExpression orExp = Restrictions.or(crit1,crit2);
		criteria.add(orExp);
	}
	
	private void addCountryCriteria(Criteria criteria){
		criteria.createAlias("country", "country", Criteria.INNER_JOIN);
		criteria.createAlias("country.countryTranslates", "countryTrans", Criteria.LEFT_JOIN);
		Criterion crit1 = Restrictions.eq("countryTrans.langId", "pt_PT");
		Criterion crit2 = Restrictions.isNull("country");
		LogicalExpression orExp = Restrictions.or(crit1,crit2);
		criteria.add(orExp);
	}
	
	private void addMemberType(Criteria criteria){
		criteria.createAlias("memberType", "memberType", Criteria.INNER_JOIN);
		criteria.createAlias("memberType.memberTypeTranslates", "memberTypeTrans", Criteria.LEFT_JOIN);
		Criterion crit1 = Restrictions.eq("memberTypeTrans.langId", "pt_PT");
		Criterion crit2 = Restrictions.isNull("memberType");
		LogicalExpression orExp = Restrictions.or(crit1,crit2);
		criteria.add(orExp);
	}

}
