package com.jrdevel.aboutus.core.site.banner;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

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
	
	//Public site methods
	public ResultObject listHomePage();
	

}
