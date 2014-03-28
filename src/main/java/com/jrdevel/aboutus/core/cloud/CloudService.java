package com.jrdevel.aboutus.core.cloud;

import java.io.InputStream;
import java.util.Map;

import com.jrdevel.aboutus.core.common.GenericService;
import com.jrdevel.aboutus.core.common.model.File;

public interface CloudService extends GenericService<File>{
	
	public void processFile(InputStream inputStream, String name, Long size, 
			String filePath, String fileType, Integer folderId);
	
	public byte[] getThumb(Integer fileId, Integer dataType);
	
	public Map<String,Object> download(Integer fileId);
	
}
