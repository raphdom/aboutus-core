package com.jrdevel.aboutus.core.site.video;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.model.Video;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class VideoDAOImpl extends AbstractGenericDAO<Video, Integer> implements VideoDAO{

	public void setExtraFilters(Criteria criteria) {
		criteria.createAlias("category", "category", Criteria.LEFT_JOIN);
	}

}
