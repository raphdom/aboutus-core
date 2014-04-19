package com.jrdevel.aboutus.core.person;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.model.Person;
import com.jrdevel.aboutus.core.common.to.ListResult;

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

	@SuppressWarnings("unchecked")
	public ListResult<PersonComboListView> findComboList() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("customer.id", UserAuthenticatedManager.getCurrentCustomer().getId()));
		
		criteria.setProjection( Projections.projectionList()
                .add( Projections.property("id"), "id" )
                .add( Projections.property("name"), "name" ));
		
		criteria.setResultTransformer(Transformers.aliasToBean(PersonComboListView.class));
		
		List<PersonComboListView> list = criteria.list();
		
		return new ListResult<PersonComboListView>(list, list.size());
	}

}
