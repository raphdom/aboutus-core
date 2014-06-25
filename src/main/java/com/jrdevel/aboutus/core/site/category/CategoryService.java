package com.jrdevel.aboutus.core.site.category;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public interface CategoryService{
	
	public List<CategoryListDTO> list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(CategoryDTO categoryDTO);
	public ResultObject insert(CategoryDTO categoryDTO);
	public ResultObject save(CategoryDTO categoryDTO);
	public ResultObject delete(List<Integer> beans);

	public List<CategoryListDTO> listCategoryByParent(int parent, boolean publishedAlbuns, boolean publishedVideos);
	

}