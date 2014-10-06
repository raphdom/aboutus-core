package com.jrdevel.aboutus.core.cloud;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.PlanExceededException;
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
	
	private static final Logger logger = Logger.getLogger(FolderServiceImpl.class);
	
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
		
		ResultObject result = new ResultObject();
		
		bean.setId(null);
		bean.setCustomer(UserAuthenticatedManager.getCurrentCustomer());
		try {
			folderDAO.makePersistent(bean);
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		FolderRole roleDefault = new FolderRole();
		roleDefault.setFolder(bean);
		try {
			folderRoleDAO.makePersistent(roleDefault);
		} catch (PlanExceededException e) {
			logger.error("PlanExceededException in insert role folder method");
		}
		
		return new ResultObject();
	}


	@Transactional
	public ResultObject update(Folder bean) {
		
		bean.setCustomer(UserAuthenticatedManager.getCurrentCustomer());
		
		try {
			folderDAO.makePersistent(bean);
		} catch (PlanExceededException e) {
			logger.error("PlanExceededException in update method");
		}
		
		
		return new ResultObject();
	}


	public ResultObject get(Folder bean) {
		return null;
	}

	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_DEL_FOLDERS')")
	public ResultObject delete(List<Integer> beans) {

		ResultObject result = new ResultObject();

		for (Integer id: beans){
			Folder folder = folderDAO.findById(id, false);
			
			if (CollectionUtils.isNotEmpty(folderDAO.getFoldersByParent(folder.getId()))){
				result.setSuccess(false);
				result.addErrorMessage("Impossível eliminar pasta com sub-pastas");
				break;
			}else{
				folderDAO.makeTransient(folder);
				result.addInfoMessage("Pasta(s) eliminadas com sucesso");
			}
			
		}

		return result;

	}
	

}
