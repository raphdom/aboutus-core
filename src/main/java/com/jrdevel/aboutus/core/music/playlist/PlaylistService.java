package com.jrdevel.aboutus.core.music.playlist;

import java.util.List;

import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface PlaylistService{
	
	public ResultObject list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(PlaylistDTO dto);
	public ResultObject insert(PlaylistDTO dto);
	public ResultObject save(PlaylistDTO dto);
	public ResultObject delete(List<Integer> beans);
	

}
