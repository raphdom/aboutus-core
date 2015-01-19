package com.jrdevel.aboutus.core.cloud;

import java.util.List;

import net.aboutchurch.common.to.ResultObject;

import com.jrdevel.aboutus.core.common.model.Folder;
import com.jrdevel.aboutus.core.common.model.User;

public interface FolderService{
	
	public FolderWrapper getFoldersPermited(User userSession);
	public ResultObject update(Folder bean);
	public ResultObject insert(Folder bean);
	public ResultObject delete(List<Integer> beans);

}
