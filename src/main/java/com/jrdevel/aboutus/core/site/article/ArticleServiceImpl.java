package com.jrdevel.aboutus.core.site.article;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.helper.MessageHelper;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
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
	
	private static final Logger logger = Logger.getLogger(ArticleServiceImpl.class);
	
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
	public ResultObject listSite(Integer limit) {
		
		ResultObject result = new ResultObject();
		
		ListResult<ArticleListSiteView> listResult = articleDAO.getHomePageArticles(limit);
		
		List<ArticleListDTO> dtos = ArticleMappingHelper.listSiteViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject listByCategory(int categoryId) {
		
		ResultObject result = new ResultObject();
		
		ListResult<ArticleListSiteView> listResult = articleDAO.getArticlesByCategory(categoryId);
		
		List<ArticleListDTO> dtos = ArticleMappingHelper.listSiteViewTolistDTO(listResult.getData());
		
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

		try {
			articleDAO.makePersistent(entity);
			
			result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.ARTICLE_INSERTED));
			
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CATEGORY')")
	public ResultObject update(ArticleDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Article article = articleDAO.findById(dto.getId(), false);
		
		if (article != null && article.getId() != null){
			
			article = ArticleMappingHelper.DTOToBean(dto, article);
			
			try {
				articleDAO.makePersistent(article);
				
				result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.ARTICLE_UPDATED));
				
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
			Article article = articleDAO.findById(id, false);
			articleDAO.makeTransient(article);
		}
		
		result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.ARTICLE_DELETED));
		
		return result;
		
	}
	

}
