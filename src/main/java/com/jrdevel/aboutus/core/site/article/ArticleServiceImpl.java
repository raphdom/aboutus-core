package com.jrdevel.aboutus.core.site.article;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.model.Article;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleDAO articleDAO;
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<ArticleListView> listResult = articleDAO.findAllByView(params, ArticleListView.class);
		
		List<ArticleListDTO> dtos = ArticleMappingHelper.listViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
		Article articleDB = articleDAO.findById(id, false);
		
		if (articleDB != null && articleDB.getId() != null){
			
			ArticleDTO dto = ArticleMappingHelper.beanToDTO(articleDB);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Categoria não existe.");
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject save(ArticleDTO categoryDTO) {
		
		if (categoryDTO.getId() != null && categoryDTO.getId() != 0){
			return update(categoryDTO);
		}else{
			return insert(categoryDTO);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_INSERT_CATEGORY')")
	public ResultObject insert(ArticleDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Article entity = ArticleMappingHelper.DTOToBean(dto);
		
		//Insert data
		entity.setId(null);
		entity.setCreated(new Date());
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		articleDAO.makePersistent(entity);
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CATEGORY')")
	public ResultObject update(ArticleDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Article article = articleDAO.findById(dto.getId(), false);
		
		if (article != null && article.getId() != null){
			
			article = ArticleMappingHelper.DTOToBean(dto, article);
			
			articleDAO.makePersistent(article);
			
		}
		
		return result;
	}

	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_DELETE_CATEGORY')")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();
		
		for (Integer id: beans){
			Article article = articleDAO.findById(id, false);
			articleDAO.makeTransient(article);
		}
		
		result.addInfoMessage("Artigo(s) eliminados com sucesso");
		
		return result;
		
	}
	

}
