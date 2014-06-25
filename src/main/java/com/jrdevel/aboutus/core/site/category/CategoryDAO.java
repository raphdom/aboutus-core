package com.jrdevel.aboutus.core.site.category;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Category;
import com.jrdevel.aboutus.core.common.to.ListResult;

/**
 * @author Raphael Domingues
 *
 */
public interface CategoryDAO extends GenericDAO<Category, Integer>{

	public ListResult<CategoryListSiteView> getCategoryByParent(int parent, boolean publishedAlbuns,boolean publishedVideos);

}
