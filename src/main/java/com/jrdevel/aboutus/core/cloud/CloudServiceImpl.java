package com.jrdevel.aboutus.core.cloud;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

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
	
	@Autowired
	private GoogleDriveService googleDriveService;

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
		
		
		byte[] originalBytes = AboutUsFileHelper.getBytesFromInputStream(inputStream);
		
		ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(originalBytes);
		
		ResultObject result = new ResultObject();
		
		String googlefileId = googleDriveService.insert(name, fileType, byteArrayStream);
		
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
		fileBean.setPath("googleDrive-fileId:" + googlefileId);
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
			//Data type 1 to thumbnail of view list
			//Data type 2 to thumbnail of thumb list
			//Data type 4 to thumbnail of detail panel
			//Data type 5 to thumbnail of site album page
			//Data type 10 to preview image when the user double clicke over image, even use of website
			byteArrayStream.reset();
			HashMap<ImageSizeEnum,byte[]> resultImages = imageTransform.transformImages(byteArrayStream,
					fileType,
					ImageSizeEnum.DATA_TYPE_1,
					ImageSizeEnum.DATA_TYPE_2,
					ImageSizeEnum.DATA_TYPE_4,
					ImageSizeEnum.DATA_TYPE_5);

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
			
			try {
				byteArrayStream.close();
			} catch (IOException e) {
				e.printStackTrace();
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

		if (fileId == null || fileId == 0){
			return null;
		}
		
		File file = fileDAO.findById(fileId, false);

		byte[] resultImage = null;

		InputStream inputStream = getOriginalFileFromGoogleDrive(file);

		ImageTransformHelper imageTransform = new ImageTransformHelper();

		resultImage = imageTransform.transformImage(inputStream, width, height, 
				exactlySize, file.getFileType());
		
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultImage;

	}

	@Transactional
	public byte[] getThumb(Integer fileId, Integer dataType) {
		
		if (fileId == null || fileId == 0){
			return null;
		}

		FileData data = fileDataDAO.getFileDataByFileAndDataType(fileId,dataType);

		if (data != null && data.getData().length > 0){
			return data.getData();
		}else{

			File file = fileDAO.findById(fileId, false);

			InputStream inputStream = getOriginalFileFromGoogleDrive(file);
			
			byte[] originalBytes = AboutUsFileHelper.getBytesFromInputStream(inputStream);
			
			byte[] resultImage = null;
			
			ImageTransformHelper imageTransform = new ImageTransformHelper();

			resultImage = imageTransform.transformImage(new ByteArrayInputStream(originalBytes), 
					ImageSizeEnum.getImageSizeByDatatype(dataType), true,
					file.getFileType());

			if (resultImage != null && resultImage.length > 0){

				FileData fileData = new FileData();
				fileData.setDataType(dataType);
				fileData.setFile(file);
				fileData.setData(resultImage);
				try {
					fileDataDAO.makePersistent(fileData, false, true);
				} catch (PlanExceededException e) {
					// TODO
				}

			}else{
				resultImage = originalBytes;
			}
			
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return resultImage;

		}

	}

	@Transactional
	public Map<String,Object> download(Integer fileId) {

		HashMap<String, Object> result = new HashMap<String,Object>();

		File file = fileDAO.findById(fileId, false);

		//java.io.File fileDisk = new java.io.File(file.getPath());
		String googleFileId = file.getPath().split(":")[1];
		InputStream fileGoogleDrive = googleDriveService.get(googleFileId);

		result.put("file_byte", AboutUsFileHelper.getBytesFromInputStream(fileGoogleDrive));

		result.put("file_name", file.getFilename());

		result.put("file_type", file.getFileType());

		return result;

	}

	@Transactional
	public ResultObject delete(List<File> beans) {

		ResultObject result = new ResultObject();

		for (File file : beans){
			File beanDB = fileDAO.findById(file.getId(), false);
			String googleFileId = beanDB.getPath().split(":")[1];
			fileDAO.makeTransient(beanDB);
			//Remove original file in google drive
			googleDriveService.delete(googleFileId);
		}

		result.addInfoMessage(beans.size() + " ficheiro(s) eliminados.");

		return result;
	}

	public ResultObject insert(File bean) {
		return null;
	}

	@Transactional
	public ResultObject moveFiles(Integer folderIdDest, List<Integer> files) {

		ResultObject result = new ResultObject();

		Folder folderDest = folderDAO.findById(folderIdDest, false);
		if (folderDest != null){

			for (Integer fileId : files){
				File beanDB = fileDAO.findById(fileId, false);

				beanDB.setFolder(folderDest);

				try {
					fileDAO.makePersistent(beanDB);
				} catch (PlanExceededException e) {
					e.printStackTrace();
				}
			}
		}else{
			result.addErrorMessage("Pasta de destino não existe.");
		}

		return result;
	}
	
	@Transactional
	private InputStream getOriginalFileFromGoogleDrive(File bean){
		String googleFileId = bean.getPath().split(":")[1];
		return getOriginalFileFromGoogleDrive(googleFileId);
	}
	@Transactional
	private InputStream getOriginalFileFromGoogleDrive(String googleFileId){
		InputStream fileGoogleDrive = googleDriveService.get(googleFileId);
		return fileGoogleDrive;
	}
	
	public ResultObject getDriveInfo(){
		
		ResultObject result = new ResultObject();
		
		result.setData(googleDriveService.getDriveInfo());
		
		return result;
		
	}


}
