package com.jrdevel.aboutus.core.cloud;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.aboutchurch.common.services.CloudPublicService;
import net.aboutchurch.common.to.ResultObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.model.File;
import com.jrdevel.aboutus.core.common.model.FileData;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class CloudPublicServiceImpl implements CloudPublicService{

	private static final long serialVersionUID = 6396693387410115432L;
	
	@Autowired
	private FileDAO fileDAO;
	
	@Autowired
	private FileDataDAO fileDataDAO;
	
	@Autowired
	private GoogleDriveService googleDriveService;

	public ResultObject list(Integer limit) {
		return null;
	}

	public ResultObject get(Integer id) {
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
	private InputStream getOriginalFileFromGoogleDrive(File bean){
		String googleFileId = bean.getPath().split(":")[1];
		return getOriginalFileFromGoogleDrive(googleFileId);
	}
	@Transactional
	private InputStream getOriginalFileFromGoogleDrive(String googleFileId){
		InputStream fileGoogleDrive = googleDriveService.get(googleFileId);
		return fileGoogleDrive;
	}

}
