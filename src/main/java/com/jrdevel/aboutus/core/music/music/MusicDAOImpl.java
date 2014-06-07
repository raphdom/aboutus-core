package com.jrdevel.aboutus.core.music.music;

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
public class MusicDAOImpl extends AbstractGenericDAO<Music, Integer> implements MusicDAO{

	public void setExtraFilters(Criteria criteria) {
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_MUSIC.getKey();
	}

	public String getObjectTitle(Music entity) {
		return entity.getTitle();
	}

}
