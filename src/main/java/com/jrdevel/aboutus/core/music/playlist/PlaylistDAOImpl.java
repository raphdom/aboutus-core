package com.jrdevel.aboutus.core.music.playlist;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Music;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class PlaylistDAOImpl extends AbstractGenericDAO<Music, Integer> implements PlaylistDAO{

	public void setExtraFilters(Criteria criteria) {
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_PLAYLIST.getKey();
	}

	public String getObjectTitle(Music entity) {
		return entity.getTitle();
	}

}
