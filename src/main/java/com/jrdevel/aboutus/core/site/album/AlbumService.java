package com.jrdevel.aboutus.core.site.album;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface AlbumService{
	
	public ResultObject list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(AlbumDTO dto);
	public ResultObject insert(AlbumDTO dto);
	public ResultObject save(AlbumDTO dto);
	public ResultObject delete(List<Integer> beans);
	

}
