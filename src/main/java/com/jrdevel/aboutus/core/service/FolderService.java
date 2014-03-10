package com.jrdevel.aboutus.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.dao.FolderDAO;
import com.jrdevel.aboutus.core.model.Folder;
import com.jrdevel.aboutus.core.model.User;
import com.jrdevel.aboutus.core.util.FolderWrapper;
import com.jrdevel.aboutus.core.util.ListParams;
import com.jrdevel.aboutus.core.util.ListResult;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class FolderService {
	
	private FolderDAO folderDAO;
	
	/**
	 * Spring use - DI
	 * @param userDAO
	 */
	@Autowired
	public void setFolderDAO(FolderDAO folderDAO) {
		this.folderDAO = folderDAO;
	}
	
	
	/**
	 * Get all folders
	 * @return
	 */
	@Transactional
	public ListResult<Folder> getFolders(ListParams params){

		return folderDAO.findAllByCriteria(params);
	}
	
	@Transactional
	public FolderWrapper getFoldersPermited(User userSession){

		List<Folder> foldersDatabase = folderDAO.getFoldersPermited(userSession);
		
		
		List<FolderWrapper> foldersWrapper = new ArrayList<FolderWrapper>();
		
		for (Folder folderDB : foldersDatabase){
			FolderWrapper child = new FolderWrapper();
			child.setId(folderDB.getId());
			child.setText(folderDB.getName());
			child.setParent(folderDB.getParent());
			foldersWrapper.add(child); 
		}
		
		FolderWrapper rootNode = new FolderWrapper();
		rootNode.setPath("/");
		rootNode.setText("");
		generateFolderTree(foldersWrapper,rootNode);
		
		return rootNode;
		
	}
	
	public void generateFolderTree(List<FolderWrapper> folders, FolderWrapper item){
		for (FolderWrapper folder : folders){
			if (folder.getParent()==item.getId()){
				item.setLeaf(false);
				if (folder.getParent()==0){
					folder.setPath("/");
				}else{
					if (!item.getPath().equals("/")){
						folder.setPath(item.getPath()+"/"+item.getText());
					}else{
						folder.setPath("/"+item.getText());
					}
				}
				item.addChild(folder);
				generateFolderTree(folders,folder);
			}
		}
	}
	

}
