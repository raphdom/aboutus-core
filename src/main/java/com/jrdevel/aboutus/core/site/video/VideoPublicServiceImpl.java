package com.jrdevel.aboutus.core.site.video;

import java.util.List;

import net.aboutchurch.common.dto.VideoListDTO;
import net.aboutchurch.common.services.VideoPublicService;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class VideoPublicServiceImpl implements VideoPublicService{

	private static final long serialVersionUID = -955050662059392820L;
	
	@Autowired
	private VideoDAO videoDAO;
	

	@Transactional
	public ResultObject list(Integer limit) {
		
		ResultObject result = new ResultObject();
		
		ListResult<VideoListSiteView> listResult = videoDAO.getHomePageVideos(limit);
		
		List<VideoListDTO> dtos = VideoMappingHelper.listSiteViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject listByCategory(int categoryId) {
		
		ResultObject result = new ResultObject();
		
		ListResult<VideoListSiteView> listResult = videoDAO.getVideosByCategory(categoryId);
		
		List<VideoListDTO> dtos = VideoMappingHelper.listSiteViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}

	public ResultObject get(Integer id) {
		return null;
	}

}
