package com.jrdevel.aboutus.core.music.playlist;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Playlist;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class PlaylistDAOImpl extends AbstractGenericDAO<Playlist, Integer> implements PlaylistDAO{

	public void setExtraFilters(Criteria criteria) {
		criteria.addOrder(Order.desc("createDate"));
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_PLAYLIST.getKey();
	}

	public String getObjectTitle(Playlist entity) {
		return entity.getName();
	}

}
