package com.jrdevel.aboutus.core.site.banner;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Banner;
import com.jrdevel.aboutus.core.common.to.ListResult;

/**
 * @author Raphael Domingues
 *
 */
public interface BannerDAO extends GenericDAO<Banner, Integer>{
	
	public ListResult<BannerListSiteView> getHomePageBanners();
	

}
