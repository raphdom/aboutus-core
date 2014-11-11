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
	
	public Album findAlbumById(Integer id){
		
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.createAlias("itemAlbums", "items", Criteria.INNER_JOIN);
		criteria.addOrder(Order.asc("items.position"));
		
		Album album = (Album) criteria.uniqueResult();
		
		if (album == null || album.getId() == null){
			album = findById(id, false);
		}
		
		return album;
		
	}
	
	public ListResult<AlbumListSiteView> getHomePageAlbuns(Integer limit) {

		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("published", true));
		criteria.addOrder(Order.desc("created"));
		
		if (limit != null){
			criteria.setMaxResults(limit);
		}
		
		criteria.setProjection(getProjectionList(AlbumListSiteView.class));
		
		criteria.setResultTransformer(Transformers.aliasToBean(AlbumListSiteView.class));
		
		List<AlbumListSiteView> result = criteria.list();
		
		return new ListResult<AlbumListSiteView>(result,result.size());
		
	}
	
	public ListResult<AlbumListSiteView> getAlbunsByCategory(int categoryId) {

		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("published", true));
		criteria.add(Restrictions.eq("category.id", categoryId));
		criteria.addOrder(Order.asc("ordering"));
		
		criteria.setProjection(getProjectionList(AlbumListSiteView.class));
		
		criteria.setResultTransformer(Transformers.aliasToBean(AlbumListSiteView.class));
		
		List<AlbumListSiteView> result = criteria.list();
		
		return new ListResult<AlbumListSiteView>(result,result.size());
		
	}

}
