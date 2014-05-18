package com.jrdevel.aboutus.core.site.category;

import java.util.List;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.dto.NodeDTO;

/**
 * @author Raphael Domingues
 *
 */
public interface CategoryService{
	
	public List<NodeDTO> list(ListParams params);
	public ResultObject get(Integer id);
	public ResultObject update(CategoryDTO categoryDTO);
	public ResultObject insert(CategoryDTO categoryDTO);
	public ResultObject save(CategoryDTO categoryDTO);
	public ResultObject delete(List<Integer> beans);
	

}
