package com.jrdevel.aboutus.core.site.category;

import java.util.List;

import net.aboutchurch.common.dto.CategoryDTO;
import net.aboutchurch.common.dto.CategoryListDTO;
import net.aboutchurch.common.services.CategoryPublicService;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Category;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class CategoryPublicServiceImpl implements CategoryPublicService{

	private static final long serialVersionUID = 5463898668341993231L;
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Transactional
	public ResultObject list(Integer limit) {
		return null;
	}

	@Transactional
	public ResultObject get(Integer id) {
		ResultObject result = new ResultObject();
		
		Category categoryDB = categoryDAO.findById(id, false);
		
		if (categoryDB != null && categoryDB.getId() != null){
			
			CategoryDTO dto = CategoryMappingHelper.beanToDTO(categoryDB);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Categoria não existe.");
		}
		
		return result;
	}

	@Transactional
	public List<CategoryListDTO> listCategoryByParent(int parent,
			boolean publishedAlbuns, boolean publishedVideos) {
		ListResult<CategoryListSiteView> listResult = categoryDAO.getCategoryByParent(parent, publishedAlbuns, publishedVideos);
		
		List<CategoryListDTO> dtos = CategoryMappingHelper.listViewSiteTolistDTO(listResult.getData());
		
		return dtos;
	}
	
}
