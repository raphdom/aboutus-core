package com.jrdevel.aboutus.core.cloud;

import com.jrdevel.aboutus.core.common.model.Folder;
import com.jrdevel.aboutus.core.common.model.User;
import com.jrdevel.aboutus.core.common.to.ResultObject;

public interface FolderService{
	
	public FolderWrapper getFoldersPermited(User userSession);

	public ResultObject update(Folder bean);

	public ResultObject insert(Folder bean);

}
