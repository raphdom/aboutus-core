package com.jrdevel.aboutus.core.site.banner;

import java.util.Date;
import java.util.List;

import net.aboutchurch.common.to.ListResult;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Banner;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class BannerDAOImpl extends AbstractGenericDAO<Banner, Integer> implements BannerDAO{

	public void setExtraFilters(Criteria criteria) {
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_BANNER.getKey();
	}

	public String getObjectTitle(Banner entity) {
		return entity.getName();
	}
	
	public ListResult<BannerListSiteView> getHomePageBanners() {

		Date actualDate = new Date();
		
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.le("publishUp", new Date()));
		criteria.add(criteriaPublishDown(actualDate));
		criteria.addOrder(Order.asc("ordering"));
		
		criteria.setProjection(getProjectionList(BannerListSiteView.class));
		
		criteria.setResultTransformer(Transformers.aliasToBean(BannerListSiteView.class));
		
		List<BannerListSiteView> result = criteria.list();
		
		return new ListResult<BannerListSiteView>(result,result.size());
		
	}
	
	private Criterion criteriaPublishDown(Date date){
		return Restrictions.or(Restrictions.ge("publishDown", date), Restrictions.isNull("publishDown"));
	}

}
