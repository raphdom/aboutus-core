package com.jrdevel.aboutus.core.site.album;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Album;
import com.jrdevel.aboutus.core.common.to.ListResult;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class AlbumDAOImpl extends AbstractGenericDAO<Album, Integer> implements AlbumDAO{

	public void setExtraFilters(Criteria criteria) {
		criteria.createAlias("category", "category", Criteria.LEFT_JOIN);
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_ALBUM.getKey();
	}

	public String getObjectTitle(Album entity) {
		return entity.getTitle();
	}
	
	public ListResult<AlbumListSiteView> getHomePageAlbuns() {

		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("published", true));
		criteria.addOrder(Order.asc("ordering"));
		
		criteria.setProjection(getProjectionList(AlbumListSiteView.class));
		
		criteria.setResultTransformer(Transformers.aliasToBean(AlbumListSiteView.class));
		
		List<AlbumListSiteView> result = criteria.list();
		
		return new ListResult<AlbumListSiteView>(result,result.size());
		
	}

}
