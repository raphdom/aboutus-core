package com.jrdevel.aboutus.core.music.music;

import java.util.Date;
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

/**
 * @author Raphael Domingues
 *
 */
@Service
public class MusicServiceImpl implements MusicService{
	
	@Autowired
	private MusicDAO musicDAO;
	
	private static final Logger logger = Logger.getLogger(MusicServiceImpl.class);
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<MusicListView> listResult = musicDAO.findAllByView(params, MusicListView.class);
		
		List<MusicListDTO> dtos = MusicMappingHelper.listViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
		Music musicDB = musicDAO.findById(id, false);
		
		if (musicDB != null && musicDB.getId() != null){
			
			MusicDTO dto = MusicMappingHelper.beanToDTO(musicDB);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Musica não existe.");
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject save(MusicDTO dto) {
		
		if (dto.getId() != null && dto.getId() != 0){
			return update(dto);
		}else{
			return insert(dto);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_INSERT_MUSICS')")
	public ResultObject insert(MusicDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Music entity = MusicMappingHelper.DTOToBean(dto);
		
		//Insert data
		entity.setId(null);
		entity.setCreateDate(new Date());
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			musicDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_MUSICS')")
	public ResultObject update(MusicDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Music music = musicDAO.findById(dto.getId(), false);
		
		if (music != null && music.getId() != null){
			
			music = MusicMappingHelper.DTOToBean(dto, music);
			
			try {
				musicDAO.makePersistent(music);
			} catch (PlanExceededException e) {
				logger.error("PlanExceededException in update method");
			}
			
		}
		
		return result;
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_DEL_MUSICS')")
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
