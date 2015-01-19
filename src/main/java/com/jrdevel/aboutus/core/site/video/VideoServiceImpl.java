package com.jrdevel.aboutus.core.site.video;

import java.util.List;

import net.aboutchurch.common.dto.VideoDTO;
import net.aboutchurch.common.dto.VideoListDTO;
import net.aboutchurch.common.to.ListParams;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.helper.MessageHelper;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.model.Video;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class VideoServiceImpl implements VideoService{
	
	@Autowired
	private VideoDAO videoDAO;
	
	private static final Logger logger = Logger.getLogger(VideoServiceImpl.class);
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<VideoListView> listResult = videoDAO.findAllByView(params, VideoListView.class);
		
		List<VideoListDTO> dtos = VideoMappingHelper.listViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
		Video videoDB = videoDAO.findById(id, false);
		
		if (videoDB != null && videoDB.getId() != null){
			
			VideoDTO dto = VideoMappingHelper.beanToDTO(videoDB);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Categoria não existe.");
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject save(VideoDTO videoDTO) {
		
		if (videoDTO.getId() != null && videoDTO.getId() != 0){
			return update(videoDTO);
		}else{
			return insert(videoDTO);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_INSERT_CATEGORY')")
	public ResultObject insert(VideoDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Video entity = VideoMappingHelper.DTOToBean(dto);
		
		//Insert data
		entity.setId(null);
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			videoDAO.makePersistent(entity);
			
			result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.VIDEO_INSERTED));
			
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CATEGORY')")
	public ResultObject update(VideoDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Video video = videoDAO.findById(dto.getId(), false);
		
		if (video != null && video.getId() != null){
			
			video = VideoMappingHelper.DTOToBean(dto, video);
			
			try {
				videoDAO.makePersistent(video);
				
				result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.VIDEO_UPDATED));
			} catch (PlanExceededException e) {
				logger.error("PlanExceededException in update method");
			}
			
		}
		
		return result;
	}

	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_DELETE_CATEGORY')")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();
		
		for (Integer id: beans){
			Video video = videoDAO.findById(id, false);
			videoDAO.makeTransient(video);
		}
		
		result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.VIDEO_DELETED));
		
		return result;
		
	}
	
}
