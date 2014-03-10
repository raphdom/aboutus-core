package com.jrdevel.aboutus.core.dao;

import java.util.List;

import com.jrdevel.aboutus.core.model.Folder;
import com.jrdevel.aboutus.core.model.User;

public interface FolderDAO extends GenericDAO<Folder, Integer>{
	
	public List<Folder> getFoldersPermited(User user);

}
