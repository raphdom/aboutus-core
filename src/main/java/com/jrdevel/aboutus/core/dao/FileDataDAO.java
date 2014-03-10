package com.jrdevel.aboutus.core.dao;

import com.jrdevel.aboutus.core.model.FileData;

public interface FileDataDAO extends GenericDAO<FileData, Integer>{
	
	public FileData getFileDataByFileAndDataType(Integer fileId, Integer dataType);

}
