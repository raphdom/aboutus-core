package com.jrdevel.aboutus.core.site.banner;

import org.hibernate.Criteria;
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

}
