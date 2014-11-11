package com.jrdevel.aboutus.core.site.article;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Article;
import com.jrdevel.aboutus.core.common.to.ListResult;

/**
 * @author Raphael Domingues
 *
 */
public interface ArticleDAO extends GenericDAO<Article, Integer>{
	
	public ListResult<ArticleListSiteView> getHomePageArticles(Integer limit);
	public ListResult<ArticleListSiteView> getArticlesByCategory(int categoryId);

}
