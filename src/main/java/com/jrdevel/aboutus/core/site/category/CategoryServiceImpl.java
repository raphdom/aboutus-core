package com.jrdevel.aboutus.core.site.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Category;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.dto.NodeDTO;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public List<NodeDTO> list(ListParams params) {
		
		params.setLimit(-1);
		
		ListResult<CategoryListView> listResult = categoryDAO.findAllByView(params, CategoryListView.class);
		
		List<NodeDTO> dtos = new ArrayList<NodeDTO>();
		
		for(CategoryListView bean : listResult.getData()){
			if (bean.getParent()==0){
				NodeDTO node = new NodeDTO();
				node.setId(bean.getId());
				node.setParent(new Integer(bean.getParent()));
				node.setText(bean.getName());
				createNodes(listResult.getData(),node);
				dtos.add(node);
			}
		}
		
		return dtos;
		
	}
	
	private void createNodes(List<CategoryListView> listResult, NodeDTO parent) {
		for(CategoryListView bean : listResult){
			if (bean.getParent()==parent.getId()){
				NodeDTO node = new NodeDTO();
				node.setId(bean.getId());
				node.setParent(parent.getId());
				node.setText(bean.getName());
				createNodes(listResult,node);
				parent.setLeaf(false);
				parent.addChild(node);
			}
		}
	}

	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
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
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CATEGORY')")
	public ResultObject update(CategoryDTO categoryDTO) {
		
		ResultObject result = new ResultObject();

		return result;
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_DELETE_CATEGORY')")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();
		
		for (Integer id: beans){
			Category category = categoryDAO.findById(id, false);
			categoryDAO.makeTransient(category);
		}
		
		result.addInfoMessage("Categoria(s) eliminadas com sucesso");
		
		return result;
		
	}
	

}
