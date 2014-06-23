package com.jrdevel.aboutus.core.site.article;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface ArticleService{
	
	public ResultObject list(ListParams params);
	public ResultObject listSite();
	public ResultObject get(Integer id);
	public ResultObject update(ArticleDTO dto);
	public ResultObject insert(ArticleDTO dto);
	public ResultObject save(ArticleDTO dto);
	public ResultObject delete(List<Integer> beans);
	

}
