package com.jrdevel.aboutus.core.site.video;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Video;
import com.jrdevel.aboutus.core.common.to.ListResult;

/**
 * @author Raphael Domingues
 *
 */
public interface VideoDAO extends GenericDAO<Video, Integer>{
	
	public ListResult<VideoListSiteView> getHomePageVideos();

}
