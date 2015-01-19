package com.jrdevel.aboutus.core.site.video;

import java.util.List;

import net.aboutchurch.common.dto.VideoDTO;
import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface VideoService{
	
	public ResultObject list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(VideoDTO dto);
	public ResultObject insert(VideoDTO dto);
	public ResultObject save(VideoDTO dto);
	public ResultObject delete(List<Integer> beans);
	

}
