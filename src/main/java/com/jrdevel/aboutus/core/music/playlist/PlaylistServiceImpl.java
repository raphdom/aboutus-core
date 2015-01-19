package com.jrdevel.aboutus.core.music.playlist;

import java.util.List;

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
import com.jrdevel.aboutus.core.common.model.Playlist;
import com.jrdevel.aboutus.core.person.PersonServiceImpl;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class PlaylistServiceImpl implements PlaylistService{
	
	@Autowired
	private PlaylistDAO playlistDAO;
	
	private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_LIST_PLAYLISTS')")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<PlaylistListView> listResult = playlistDAO.findAllByView(params, PlaylistListView.class);
		
		List<PlaylistListDTO> dtos = PlaylistMappingHelper.listViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
		Playlist bean = playlistDAO.findById(id, false);
		
		if (bean != null && bean.getId() != null){
			
			PlaylistDTO dto = PlaylistMappingHelper.beanToDTO(bean);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Musica não existe.");
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject save(PlaylistDTO dto) {
		
		if (dto.getId() != null && dto.getId() != 0){
			return update(dto);
		}else{
			return insert(dto);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_ADD_PLAYLISTS')")
	public ResultObject insert(PlaylistDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Playlist entity = PlaylistMappingHelper.DTOToBean(dto);
		
		//Insert data
		entity.setId(null);
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			playlistDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_PLAYLISTS')")
	public ResultObject update(PlaylistDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Playlist bean = playlistDAO.findById(dto.getId(), false);
		
		if (bean != null && bean.getId() != null){
			
			bean = PlaylistMappingHelper.DTOToBean(dto, bean);
			
			try {
				playlistDAO.makePersistent(bean);
			} catch (PlanExceededException e) {
				logger.error("PlanExceededException in update method");
			}
			
		}
		
		return result;
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_DEL_PLAYLISTS')")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();
		
		for (Integer id: beans){
			Playlist playlist = playlistDAO.findById(id, false);
			playlistDAO.makeTransient(playlist);
		}
		
		result.addInfoMessage("Playlist(s) eliminadas com sucesso");
		
		return result;
		
	}
	

}
