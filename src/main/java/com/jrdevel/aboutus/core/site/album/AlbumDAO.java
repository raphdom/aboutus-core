package com.jrdevel.aboutus.core.site.album;

import net.aboutchurch.common.to.ListResult;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Album;

/**
 * @author Raphael Domingues
 *
 */
public interface AlbumDAO extends GenericDAO<Album, Integer>{
	
	public ListResult<AlbumListSiteView> getHomePageAlbuns(Integer limit);
	public ListResult<AlbumListSiteView> getAlbunsByCategory(int categoryId);
	public Album findAlbumById(Integer id);

}
