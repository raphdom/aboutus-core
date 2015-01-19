package com.jrdevel.aboutus.core.site.article;

import java.util.Date;
import java.util.List;

import net.aboutchurch.common.to.ListResult;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Article;
import com.jrdevel.aboutus.core.common.model.Permission;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class ArticleDAOImpl extends AbstractGenericDAO<Article, Integer> implements ArticleDAO{

	public void setExtraFilters(Criteria criteria) {
		criteria.createAlias("category", "category", Criteria.LEFT_JOIN);
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_ARTICLE.getKey();
	}

	public String getObjectTitle(Article entity) {
		return entity.getTitle();
	}

	public ListResult<ArticleListSiteView> getHomePageArticles(Integer limit) {

		Date actualDate = new Date();
		
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.le("publishUp", new Date()));
		criteria.add(criteriaPublishDown(actualDate));
		criteria.add(Restrictions.eq("homepage", true));
		criteria.addOrder(Order.asc("ordering"));
		criteria.add(Restrictions.eq("customer.id", getCustomer().getId()));
		
		if (limit != null){
			criteria.setMaxResults(limit);
		}
		
		criteria.setProjection(getProjectionList(ArticleListSiteView.class));
		
		criteria.setResultTransformer(Transformers.aliasToBean(ArticleListSiteView.class));
		
		List<ArticleListSiteView> result = criteria.list();
		
		return new ListResult<ArticleListSiteView>(result,result.size());
		
	}
	
	private Criterion criteriaPublishDown(Date date){
		return Restrictions.or(Restrictions.ge("publishDown", date), Restrictions.isNull("publishDown"));
	}
	
	public ListResult<ArticleListSiteView> getArticlesByCategory(int categoryId) {
		
		Date actualDate = new Date();

		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.le("publishUp", new Date()));
		criteria.add(criteriaPublishDown(actualDate));
		criteria.add(Restrictions.eq("category.id", categoryId));
		criteria.addOrder(Order.asc("ordering"));
		
		criteria.setProjection(getProjectionList(ArticleListSiteView.class));
		
		criteria.setResultTransformer(Transformers.aliasToBean(ArticleListSiteView.class));
		
		List<ArticleListSiteView> result = criteria.list();
		
		return new ListResult<ArticleListSiteView>(result,result.size());
		
	}

}
