package com.jrdevel.aboutus.core.site.category;

import java.util.List;

import net.aboutchurch.common.dto.CategoryDTO;
import net.aboutchurch.common.dto.CategoryListDTO;
import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;

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
	

}