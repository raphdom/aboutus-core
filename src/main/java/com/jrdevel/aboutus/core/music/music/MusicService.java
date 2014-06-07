package com.jrdevel.aboutus.core.music.music;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface MusicService{
	
	public ResultObject list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(MusicDTO dto);
	public ResultObject insert(MusicDTO dto);
	public ResultObject save(MusicDTO dto);
	public ResultObject delete(List<Integer> beans);
	

}
