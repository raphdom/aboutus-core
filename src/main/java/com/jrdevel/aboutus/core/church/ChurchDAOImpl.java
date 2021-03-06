package com.jrdevel.aboutus.core.church;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Church;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class ChurchDAOImpl extends AbstractGenericDAO<Church, Integer> implements ChurchDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
	}

	public String getObjectName() {
		return MessageKeyEnum.AUDIT_OBJECT_CHURCH.getKey();
	}

	public String getObjectTitle(Church entity) {
		return entity.getName();
	}

}
