package com.jrdevel.aboutus.core.cloud;

import java.util.List;

import com.jrdevel.aboutus.core.common.GenericDAO;
import com.jrdevel.aboutus.core.common.model.Folder;
import com.jrdevel.aboutus.core.common.model.User;

public interface FolderDAO extends GenericDAO<Folder, Integer>{
	
	public List<Folder> getFoldersPermited(User user);

}
