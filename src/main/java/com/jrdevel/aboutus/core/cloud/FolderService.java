package com.jrdevel.aboutus.core.cloud;

import com.jrdevel.aboutus.core.common.GenericService;
import com.jrdevel.aboutus.core.common.model.Folder;
import com.jrdevel.aboutus.core.common.model.User;

public interface FolderService extends GenericService<Folder>{
	
	public FolderWrapper getFoldersPermited(User userSession);

}
