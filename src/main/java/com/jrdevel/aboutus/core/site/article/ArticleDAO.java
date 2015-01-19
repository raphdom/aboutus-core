package com.jrdevel.aboutus.core.site.article;

import net.aboutchurch.common.to.ListResult;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Article;

/**
 * @author Raphael Domingues
 *
 */
public interface ArticleDAO extends GenericDAO<Article, Integer>{
	
	public ListResult<ArticleListSiteView> getHomePageArticles(Integer limit);
	public ListResult<ArticleListSiteView> getArticlesByCategory(int categoryId);

}
