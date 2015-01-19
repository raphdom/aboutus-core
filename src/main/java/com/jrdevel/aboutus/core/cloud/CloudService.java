package com.jrdevel.aboutus.core.cloud;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ResultObject;

import com.jrdevel.aboutus.core.common.model.File;

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
