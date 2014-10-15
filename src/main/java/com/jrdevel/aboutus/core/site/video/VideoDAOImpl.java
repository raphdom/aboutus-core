package com.jrdevel.aboutus.core.site.video;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Video;
import com.jrdevel.aboutus.core.common.to.ListResult;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class VideoDAOImpl extends AbstractGenericDAO<Video, Integer> implements VideoDAO{

	public void setExtraFilters(Criteria criteria) {
		criteria.createAlias("category", "category", Criteria.LEFT_JOIN);
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_VIDEO.getKey();
	}

	public String getObjectTitle(Video entity) {
		return entity.getTitle();
	}
	
	public ListResult<VideoListSiteView> getHomePageVideos() {

		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("published", true));
		criteria.addOrder(Order.asc("ordering"));
		
		criteria.setProjection(getProjectionList(VideoListSiteView.class));
		
		criteria.setResultTransformer(Transformers.aliasToBean(VideoListSiteView.class));
		
		List<VideoListSiteView> result = criteria.list();
		
		return new ListResult<VideoListSiteView>(result,result.size());
		
	}

}
