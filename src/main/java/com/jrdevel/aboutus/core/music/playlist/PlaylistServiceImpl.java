package com.jrdevel.aboutus.core.music.playlist;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.model.Music;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.person.PersonServiceImpl;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class PlaylistServiceImpl implements PlaylistService{
	
	@Autowired
	private PlaylistDAO musicDAO;
	
	private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<PlaylistListView> listResult = musicDAO.findAllByView(params, PlaylistListView.class);
		
		List<PlaylistListDTO> dtos = PlaylistMappingHelper.listViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
		Music musicDB = musicDAO.findById(id, false);
		
		if (musicDB != null && musicDB.getId() != null){
			
			PlaylistDTO dto = PlaylistMappingHelper.beanToDTO(musicDB);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Musica não existe.");
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject save(PlaylistDTO videoDTO) {
		
		if (videoDTO.getId() != null && videoDTO.getId() != 0){
			return update(videoDTO);
		}else{
			return insert(videoDTO);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_INSERT_CATEGORY')")
	public ResultObject insert(PlaylistDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Music entity = PlaylistMappingHelper.DTOToBean(dto);
		
		//Insert data
		entity.setId(null);
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			musicDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CATEGORY')")
	public ResultObject update(PlaylistDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Music music = musicDAO.findById(dto.getId(), false);
		
		if (music != null && music.getId() != null){
			
			music = PlaylistMappingHelper.DTOToBean(dto, music);
			
			try {
				musicDAO.makePersistent(music);
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
			Music music = musicDAO.findById(id, false);
			musicDAO.makeTransient(music);
		}
		
		result.addInfoMessage("Musica(s) eliminadas com sucesso");
		
		return result;
		
	}
	

}
