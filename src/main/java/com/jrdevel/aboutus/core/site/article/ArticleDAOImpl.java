package com.jrdevel.aboutus.core.site.article;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Article;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class ArticleDAOImpl extends AbstractGenericDAO<Article, Integer> implements ArticleDAO{

	public void setExtraFilters(Criteria criteria) {
		criteria.createAlias("category", "category", Criteria.LEFT_JOIN);
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_ARTICLE.getKey();
	}

	public String getObjectTitle(Article entity) {
		return entity.getTitle();
	}

}