package com.jrdevel.aboutus.core.site.album;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Album;

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

}
