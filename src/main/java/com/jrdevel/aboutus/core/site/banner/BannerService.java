package com.jrdevel.aboutus.core.site.banner;

import java.util.List;

import net.aboutchurch.common.dto.BannerDTO;
import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface BannerService{
	
	public ResultObject list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(BannerDTO dto);
	public ResultObject insert(BannerDTO dto);
	public ResultObject save(BannerDTO dto);
	public ResultObject delete(List<Integer> beans);
	

}
