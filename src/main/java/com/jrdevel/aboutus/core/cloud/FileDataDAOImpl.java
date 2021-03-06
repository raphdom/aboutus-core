package com.jrdevel.aboutus.core.cloud;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jrdevel.aboutus.core.common.AbstractGenericDAO;
import com.jrdevel.aboutus.core.common.model.FileData;

/**
 * @author Raphael Domingues
 *
 */
@Repository
public class FileDataDAOImpl extends AbstractGenericDAO<FileData, Integer> implements FileDataDAO{
	
	public void setExtraFilters(Criteria criteria) {
		
		
	}
	
	public FileData getFileDataByFileAndDataType(Integer fileId, Integer dataType){
 		
		return findUniqueByCriteria(Restrictions.eq("file.id", fileId),
				Restrictions.eq("dataType", dataType));
		
	}

	public String getObjectName() {
		return null;
	}

	public String getObjectTitle(FileData entity) {
		return null;
	}

}
