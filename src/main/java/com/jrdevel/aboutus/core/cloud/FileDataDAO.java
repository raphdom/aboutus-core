package com.jrdevel.aboutus.core.cloud;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.FileData;

public interface FileDataDAO extends GenericDAO<FileData, Integer>{
	
	public FileData getFileDataByFileAndDataType(Integer fileId, Integer dataType);

}
