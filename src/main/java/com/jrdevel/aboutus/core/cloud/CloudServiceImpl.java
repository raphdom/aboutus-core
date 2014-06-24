package com.jrdevel.aboutus.core.cloud;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.configuration.AboutUsConfiguration;
import com.jrdevel.aboutus.core.common.model.File;
import com.jrdevel.aboutus.core.common.model.FileData;
import com.jrdevel.aboutus.core.common.model.Folder;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class CloudServiceImpl implements CloudService{
	
	private FileDAO fileDAO;
	private FileDataDAO fileDataDAO;
	private FolderDAO folderDAO;
	
	private static final Logger logger = Logger.getLogger(CloudServiceImpl.class);
	
	@Autowired
	private AboutUsConfiguration configuration;
	
	/**
	 * Spring use - DI
	 * @param fileDAO
	 */
	@Autowired
	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	/**
	 * Spring use - DI
	 * @param folderDAO
	 */
	@Autowired
	public void setFolderDAO(FolderDAO folderDAO) {
		this.folderDAO = folderDAO;
	}
	
	/**
	 * Spring use - DI
	 * @param folderDAO
	 */
	@Autowired
	public void setFileDataDAO(FileDataDAO fileDataDAO) {
		this.fileDataDAO = fileDataDAO;
	}
	
	
	/**
	 * Get all files
	 * @return
	 */
	@Transactional
	public ListResult<File> getFilesList(ListParams params){

		return fileDAO.findAllByCriteria(params);
		
	}
	
	/**
	 * Get all folders
	 * @return
	 */
	@Transactional
	public ListResult<Folder> getFoldersList(ListParams params){

		return folderDAO.findAllByCriteria(params);
		
	}
	
	@Transactional
	public ResultObject processFile(InputStream inputStream, String name, Long size, 
			String filePath, String fileType, Integer folderId){
		
		ResultObject result = new ResultObject();
		
		File fileBean = new File();
		fileBean.setFilename(name);
		fileBean.setFileType(fileType);
		fileBean.setFilesize(size);
		fileBean.setTitle(name);
		if (folderId != null){
			Folder folder = new Folder();
			folder.setId(folderId);
			fileBean.setFolder(folder);
		}
		fileBean.setPath(filePath);
		fileBean.setCreatedDate(new Date());
		fileBean.setModifiedDate(new Date());
		fileBean.setCustomer(UserAuthenticatedManager.getCurrentCustomer());
		
		try {
			fileDAO.makePersistent(fileBean);
			
			result.addInfoMessage(fileBean.getId() + "");
		} catch (PlanExceededException e) {
			// TODO for the file send a Result object
		}
		
		if (AboutUsFileHelper.imageResizeSupported(fileType)){
			
			ImageTransformHelper imageTransform = new ImageTransformHelper();
			HashMap<ImageSizeEnum,byte[]> resultImages = imageTransform.transformImages(inputStream,
					ImageSizeEnum.DATA_TYPE_1,
					ImageSizeEnum.DATA_TYPE_2,
					ImageSizeEnum.DATA_TYPE_4,
					ImageSizeEnum.DATA_TYPE_10);
			
			for (ImageSizeEnum imgSize : resultImages.keySet()){
				if (resultImages.get(imgSize)!= null && resultImages.get(imgSize).length > 0){
					FileData fileData = new FileData();
					fileData.setDataType(imgSize.getDatatype());
					fileData.setFile(fileBean);
					fileData.setData(resultImages.get(imgSize));
					try {
						fileDataDAO.makePersistent(fileData);
					} catch (PlanExceededException e) {
						// TODO
					}
				}
			}
			
		}
		
		return result;
		
	}

	@Transactional
	public ResultObject list(ListParams params) {
		
		ListResult<FileView> listResult = fileDAO.findAllByView(params, FileView.class);
		ResultObject result = new ResultObject();
		result.setData(listResult.getData());
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}

	public ResultObject update(File bean) {
		return null;
	}

	public ResultObject get(File bean) {
		return null;
	}
	
	@Transactional
	public byte[] getThumb(Integer fileId, Integer width, Integer height, boolean exactlySize) {
		
		File file = fileDAO.findById(fileId, false);
		
		java.io.File fileDisk = new java.io.File(file.getPath());
		
		byte[] resultImage = null;
		
		try {
			FileInputStream inputStream = new FileInputStream(fileDisk);
			
			ImageTransformHelper imageTransform = new ImageTransformHelper();
			
			resultImage = imageTransform.transformImage(inputStream, width, height, exactlySize);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return resultImage;
		
	}
	
	@Transactional
	public byte[] getThumb(Integer fileId, Integer dataType) {
		
		FileData data = fileDataDAO.getFileDataByFileAndDataType(fileId,dataType);
		
		if (data != null && data.getData().length > 0){
			return data.getData();
		}else{
			
			if (dataType == 10){
			
				File file = fileDAO.findById(fileId, false);
				
				java.io.File fileDisk = new java.io.File(file.getPath());
				
				try {
					return AboutUsFileHelper.getBytesFromFile(fileDisk);
					
				} catch (IOException e) {
					logger.error("erro reading bytes from file");
				}
			
			}else{
				
				File file = fileDAO.findById(fileId, false);
				
				java.io.File fileDisk = new java.io.File(file.getPath());
				
				byte[] resultImage = null;
				
				try {
					FileInputStream inputStream = new FileInputStream(fileDisk);
					
					ImageTransformHelper imageTransform = new ImageTransformHelper();
					
					resultImage = imageTransform.transformImage(inputStream, 
							ImageSizeEnum.getImageSizeByDatatype(dataType), true);
					
					FileData fileData = new FileData();
					fileData.setDataType(dataType);
					fileData.setFile(file);
					fileData.setData(resultImage);
					try {
						fileDataDAO.makePersistent(fileData, false, true);
					} catch (PlanExceededException e) {
						// TODO
					}
					
					return resultImage;
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return null;
		
	}
	
	@Transactional
	public Map<String,Object> download(Integer fileId) {
		
		HashMap<String, Object> result = new HashMap<String,Object>();
		
		File file = fileDAO.findById(fileId, false);
		
		java.io.File fileDisk = new java.io.File(file.getPath());
		
		try {
			
			result.put("file_byte", AboutUsFileHelper.getBytesFromFile(fileDisk));
			
			result.put("file_name", file.getFilename());
			
			result.put("file_type", file.getFileType());
			
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Transactional
	public ResultObject delete(List<File> beans) {
		
		ResultObject result = new ResultObject();
		
		for (File file : beans){
			File beanDB = fileDAO.findById(file.getId(), false);
			java.io.File fileHD = new java.io.File(beanDB.getPath());
			fileDAO.makeTransient(beanDB);
			//Remove original FileSystem
			fileHD.delete();
		}
		
		result.addInfoMessage(beans.size() + " ficheiro(s) eliminados.");
		
		return result;
	}

	public ResultObject insert(File bean) {
		return null;
	}


}
