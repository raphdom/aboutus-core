package com.jrdevel.aboutus.core.site.category;

import java.util.List;

import net.aboutchurch.common.dto.CategoryDTO;
import net.aboutchurch.common.dto.CategoryListDTO;
import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.helper.MessageHelper;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Category;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public List<CategoryListDTO> list(ListParams params) {
		
		params.setLimit(-1);
		
		ListResult<CategoryListView> listResult = categoryDAO.findAllByView(params, CategoryListView.class);
		
		List<CategoryListDTO> dtos = CategoryMappingHelper.listViewTolistDTO(listResult.getData());
		
		return dtos;
		
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
	public ResultObject save(CategoryDTO categoryDTO) {
		
		if (categoryDTO.getId() != null && categoryDTO.getId() != 0){
			return update(categoryDTO);
		}else{
			return insert(categoryDTO);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_INSERT_CATEGORY')")
	public ResultObject insert(CategoryDTO categoryDTO) {
		
		ResultObject result = new ResultObject();
		
		Category entity = CategoryMappingHelper.DTOToBean(categoryDTO);
		
		//Insert data
		entity.setId(null);
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			categoryDAO.makePersistent(entity);
			
			result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.CATEGORY_INSERTED));
			
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CATEGORY')")
	public ResultObject update(CategoryDTO categoryDTO) {
		
		ResultObject result = new ResultObject();
		
		Category category = categoryDAO.findById(categoryDTO.getId(), false);
		
		if (category != null && category.getId() != null){
			
			category = CategoryMappingHelper.DTOToBean(categoryDTO, category);
			
			try {
				categoryDAO.makePersistent(category);
				
				result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.CATEGORY_UPDATED));
				
			} catch (PlanExceededException e) {
				logger.error("PlanExceededException in update method");
			}
			
		}
		
		return result;
	}

	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_DELETE_CATEGORY')")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();
		
		for (Integer id: beans){
			Category category = categoryDAO.findById(id, false);
			categoryDAO.makeTransient(category);
		}
		
		result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.CATEGORY_DELETED));
		
		return result;
		
	}


}
