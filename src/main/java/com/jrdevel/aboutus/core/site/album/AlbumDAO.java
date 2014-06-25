package com.jrdevel.aboutus.core.site.album;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Album;
import com.jrdevel.aboutus.core.common.to.ListResult;

/**
 * @author Raphael Domingues
 *
 */
public interface AlbumDAO extends GenericDAO<Album, Integer>{
	
	public ListResult<AlbumListSiteView> getHomePageAlbuns();
	public ListResult<AlbumListSiteView> getAlbunsByCategory(int categoryId);

}
