package com.jrdevel.aboutus.core.cloud;

import java.util.List;

import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

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
		
		
		FolderWrapper rootNode = new FolderWrapper(0,"/","");
		generateFolderTree(foldersDatabase,rootNode);
		
		return rootNode;
		
	}
	
	public void generateFolderTree(List<Folder> folders, FolderWrapper item){
		for (Folder folder : folders){
			if (folder.getParent()==item.getId()){
				FolderWrapper innerItem = new FolderWrapper();
				innerItem.setId(folder.getId());
				innerItem.setParent(item.getId());
				innerItem.setText(folder.getName());
				item.setLeaf(false);
				if (folder.getParent()==0){
					innerItem.setPath("/");
				}else{
					if (!item.getPath().equals("/")){
						innerItem.setPath(item.getPath()+"/"+item.getText());
					}else{
						innerItem.setPath("/"+item.getText());
					}
				}
				item.addChild(innerItem);
				generateFolderTree(folders,innerItem);
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
		
		FolderWrapper folder = new FolderWrapper();
		folder.setId(bean.getId());
		folder.setText(bean.getName());
		folder.setPath(bean.getName());
		folder.setParent(bean.getParent());
		
		result.setData(folder);
		
		FolderRole roleDefault = new FolderRole();
		roleDefault.setFolder(bean);
		try {
			folderRoleDAO.makePersistent(roleDefault);
		} catch (PlanExceededException e) {
			logger.error("PlanExceededException in insert role folder method");
		}
		
		return result;
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
