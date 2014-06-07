package com.jrdevel.aboutus.core.site.category;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Category;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class CategoryDAOImpl extends AbstractGenericDAO<Category, Integer> implements CategoryDAO{

	public void setExtraFilters(Criteria criteria) {
		
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_CATEGORY.getKey();
	}

	public String getObjectTitle(Category entity) {
		return entity.getName();
	}

}
