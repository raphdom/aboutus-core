package com.jrdevel.aboutus.core.site.article;

import java.util.ArrayList;
import java.util.List;

import net.aboutchurch.common.dto.ArticleDTO;
import net.aboutchurch.common.dto.ArticleListDTO;

import com.jrdevel.aboutus.core.common.model.Article;
import com.jrdevel.aboutus.core.common.model.Category;
import com.jrdevel.aboutus.core.common.model.File;

/**
 * @author Raphael Domingues
 *
 */
public class ArticleMappingHelper {
	
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static ArticleListDTO viewToDTO(ArticleListView view){
		ArticleListDTO dto = new ArticleListDTO();
		dto.setId(view.getId());
		dto.setTitle(view.getTitle());
		dto.setCreated(view.getCreated());
		dto.setPublishUp(view.getPublishUp());
		dto.setPublishDown(view.getPublishDown());
		dto.setOrdering(view.getOrdering());
		dto.setHits(view.getHits());
		dto.setAuthor(view.getAuthor());
		dto.setCategoryName(view.getCategoryName());
		return dto;
	}
	
	/**
	 * Mapping a view to a dto bean
	 * @param view
	 * @return
	 */
	public static ArticleListDTO viewToDTO(ArticleListSiteView view){
		ArticleListDTO dto = new ArticleListDTO();
		dto.setId(view.getId());
		dto.setTitle(view.getTitle());
		dto.setIntroarticle(view.getIntroarticle());
		dto.setThumbId(view.getThumbId());
		return dto;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<ArticleListDTO> listViewTolistDTO(List<ArticleListView> views){
		List<ArticleListDTO> dtos = new ArrayList<ArticleListDTO>();
		for(ArticleListView view : views){
			ArticleListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	/**
	 * Mapping a list of view to a dto beans list
	 * @param views
	 * @return
	 */
	public static List<ArticleListDTO> listSiteViewTolistDTO(List<ArticleListSiteView> views){
		List<ArticleListDTO> dtos = new ArrayList<ArticleListDTO>();
		for(ArticleListSiteView view : views){
			ArticleListDTO node = viewToDTO(view);
			dtos.add(node);
		}
		return dtos;
	}
	
	public static ArticleDTO beanToDTO(Article bean){
		
		ArticleDTO dto = new ArticleDTO();
		
		dto.setId(bean.getId());
		dto.setTitle(bean.getTitle());
		dto.setPublishUp(bean.getPublishUp());
		dto.setPublishDown(bean.getPublishDown());
		dto.setOrdering(bean.getOrdering());
		dto.setHits(bean.getHits());
		dto.setAuthor(bean.getAuthor());
		dto.setIntroarticle(bean.getIntroarticle());
		dto.setArticle(bean.getArticle());
		dto.setHomepage(bean.isHomepage());
		if (bean.getCategory()!= null){
			dto.setCategoryId(bean.getCategory().getId());
		}
		if (bean.getFile() != null){
			dto.setThumbId(bean.getFile().getId());
		}
		
		return dto;
	}
	
	public static Article DTOToBean(ArticleDTO dto){
		Article bean = new Article();
		return DTOToBean(dto, bean);
	}
	
	public static Article DTOToBean(ArticleDTO dto, Article bean){
		
		bean.setId(dto.getId());
		bean.setTitle(dto.getTitle());
		bean.setPublishUp(dto.getPublishUp());
		bean.setPublishDown(dto.getPublishDown());
		bean.setOrdering(dto.getOrdering());
		bean.setHits(dto.getHits());
		bean.setAuthor(dto.getAuthor());
		bean.setIntroarticle(dto.getIntroarticle());
		bean.setArticle(dto.getArticle());
		bean.setHomepage(dto.isHomepage());
		//Category
		Category category = new Category();
		category.setId(dto.getCategoryId());
		bean.setCategory(category);
		
		//Thumb
		if (dto.getThumbId()!= null && dto.getThumbId()>0){
			File file = new File();
			file.setId(dto.getThumbId());
			bean.setFile(file);
		}
		
		return bean;
	}
	
}
