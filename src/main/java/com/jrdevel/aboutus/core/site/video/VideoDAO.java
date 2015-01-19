package com.jrdevel.aboutus.core.site.video;

import net.aboutchurch.common.to.ListResult;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Video;

/**
 * @author Raphael Domingues
 *
 */
public interface VideoDAO extends GenericDAO<Video, Integer>{
	
	public ListResult<VideoListSiteView> getHomePageVideos(Integer limit);
	public ListResult<VideoListSiteView> getVideosByCategory(int categoryId);

}
