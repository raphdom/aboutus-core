package com.jrdevel.aboutus.core.site.category;

import java.util.List;

import net.aboutchurch.common.to.ListResult;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Category;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class CategoryDAOImpl extends AbstractGenericDAO<Category, Integer> implements CategoryDAO{

	public void setExtraFilters(Criteria criteria) {
		
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_CATEGORY.getKey();
	}

	public String getObjectTitle(Category entity) {
		return entity.getName();
	}

	public ListResult<CategoryListSiteView> getCategoryByParent(int parent, boolean publishedAlbuns,boolean publishedVideos) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("parent", parent));
		if (publishedAlbuns && !publishedVideos){
			criteria.add(Restrictions.eq("publishedAlbuns", publishedAlbuns));
		}
		if (publishedVideos && !publishedAlbuns){
			criteria.add(Restrictions.eq("publishedVideos", publishedVideos));
		}
		criteria.addOrder(Order.asc("position"));
		
		criteria.setProjection(getProjectionList(CategoryListSiteView.class));
		
		criteria.setResultTransformer(Transformers.aliasToBean(CategoryListSiteView.class));
		
		List<CategoryListSiteView> result = criteria.list();
		
		return new ListResult<CategoryListSiteView>(result,result.size());
	}
	

}
