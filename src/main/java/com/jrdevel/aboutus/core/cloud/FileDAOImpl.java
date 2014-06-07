package com.jrdevel.aboutus.core.cloud;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.File;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class FileDAOImpl extends AbstractGenericDAO<File, Integer> implements FileDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_FILE.getKey();
	}

	public String getObjectTitle(File entity) {
		return entity.getFilename();
	}

}
