package com.jrdevel.aboutus.core.site.article;

import java.util.List;

import net.aboutchurch.common.dto.ArticleDTO;
import net.aboutchurch.common.dto.ArticleListDTO;
import net.aboutchurch.common.services.ArticlePublicService;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Article;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class ArticlePublicServiceImpl implements ArticlePublicService{

	private static final long serialVersionUID = -5048972132232884422L;
	
	@Autowired
	private ArticleDAO articleDAO;
	
	@Transactional
	public ResultObject list(Integer limit) {
		ResultObject result = new ResultObject();
		
		ListResult<ArticleListSiteView> listResult = articleDAO.getHomePageArticles(limit);
		
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
			result.addErrorMessage("Artigo não existe.");
		}
		
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

}
