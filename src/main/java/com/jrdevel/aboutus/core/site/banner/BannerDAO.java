package com.jrdevel.aboutus.core.site.banner;

import net.aboutchurch.common.to.ListResult;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Banner;

/**
 * @author Raphael Domingues
 *
 */
public interface BannerDAO extends GenericDAO<Banner, Integer>{
	
	public ListResult<BannerListSiteView> getHomePageBanners();
	

}
