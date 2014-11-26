package com.jrdevel.aboutus.core.cloud;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.jrdevel.aboutus.core.common.model.File;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ResultObject;

public interface CloudService{
	
	public ResultObject processFile(InputStream inputStream, String name, Long size, 
			String filePath, String fileType, Integer folderId);
	
	public byte[] getThumb(Integer fileId, Integer dataType);
	
	public byte[] getThumb(Integer fileId, Integer width, Integer height, boolean exactlySize);
	
	public Map<String,Object> download(Integer fileId);

	public ResultObject list(ListParams input);

	public ResultObject delete(List<File> data);
	
	public ResultObject moveFiles(Integer folderIdDest, List<Integer> files);
	
	public ResultObject getDriveInfo();
	
}
