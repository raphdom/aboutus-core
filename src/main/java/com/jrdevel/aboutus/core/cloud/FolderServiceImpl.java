package com.jrdevel.aboutus.core.cloud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.model.Folder;
import com.jrdevel.aboutus.core.common.model.FolderRole;
import com.jrdevel.aboutus.core.common.model.User;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class FolderServiceImpl implements FolderService{
	
	@Autowired
	private FolderDAO folderDAO;
	@Autowired
	private FolderRoleDAO folderRoleDAO;
	
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
		rootNode.setId(0);
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


	public ResultObject list(ListParams params) {
		return null;
	}


	@Transactional
	public ResultObject insert(Folder bean) {
		
		bean.setId(null);
		bean.setCustomer(UserAuthenticatedManager.getCurrentCustomer());
		folderDAO.makePersistent(bean);
		
		FolderRole roleDefault = new FolderRole();
		roleDefault.setFolder(bean);
		folderRoleDAO.makePersistent(roleDefault);
		
		return new ResultObject();
	}


	@Transactional
	public ResultObject update(Folder bean) {
		
		bean.setCustomer(UserAuthenticatedManager.getCurrentCustomer());
		folderDAO.makePersistent(bean);
		
		return new ResultObject();
	}


	public ResultObject get(Folder bean) {
		return null;
	}


	public ResultObject delete(List<Folder> beans) {
		return null;
	}
	

}
