package com.jrdevel.aboutus.core.site.album;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.PlanExceededException;
import com.jrdevel.aboutus.core.common.model.Album;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;
import com.jrdevel.aboutus.core.site.article.ArticleListDTO;
import com.jrdevel.aboutus.core.site.article.ArticleListSiteView;
import com.jrdevel.aboutus.core.site.article.ArticleMappingHelper;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class AlbumServiceImpl implements AlbumService{
	
	@Autowired
	private AlbumDAO albumDAO;
	
	private static final Logger logger = Logger.getLogger(AlbumServiceImpl.class);
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<AlbumListView> listResult = albumDAO.findAllByView(params, AlbumListView.class);
		
		List<AlbumListDTO> dtos = AlbumMappingHelper.listViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject listHomePage() {
		
		ResultObject result = new ResultObject();
		
		ListResult<AlbumListSiteView> listResult = albumDAO.getHomePageAlbuns();
		
		List<AlbumListDTO> dtos = AlbumMappingHelper.listSiteViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject listByCategory(int categoryId) {
		
		ResultObject result = new ResultObject();
		
		ListResult<AlbumListSiteView> listResult = albumDAO.getAlbunsByCategory(categoryId);
		
		List<AlbumListDTO> dtos = AlbumMappingHelper.listSiteViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}
	
	@Transactional
	public ResultObject get(Integer id) {
		
		ResultObject result = new ResultObject();
		
		Album albumDB = albumDAO.findById(id, false);
		
		if (albumDB != null && albumDB.getId() != null){
			
			AlbumDTO dto = AlbumMappingHelper.beanToDTO(albumDB);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Categoria não existe.");
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject save(AlbumDTO categoryDTO) {
		
		if (categoryDTO.getId() != null && categoryDTO.getId() != 0){
			return update(categoryDTO);
		}else{
			return insert(categoryDTO);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_INSERT_CATEGORY')")
	public ResultObject insert(AlbumDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Album entity = AlbumMappingHelper.DTOToBean(dto);
		
		//Insert data
		entity.setId(null);
		entity.setCreated(new Date());
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			albumDAO.makePersistent(entity);
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CATEGORY')")
	public ResultObject update(AlbumDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Album album = albumDAO.findById(dto.getId(), false);
		
		if (album != null && album.getId() != null){
			
			album = AlbumMappingHelper.DTOToBean(dto, album);
			
			try {
				albumDAO.makePersistent(album);
			} catch (PlanExceededException e) {
				logger.error("PlanExceededException in update method");
			}
			
			result.addInfoMessage("Album atualizado.");
			
		}
		
		return result;
	}

	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_DELETE_CATEGORY')")
	public ResultObject delete(List<Integer> beans) {
		
		ResultObject result = new ResultObject();
		
		for (Integer id: beans){
			Album album = albumDAO.findById(id, false);
			albumDAO.makeTransient(album);
		}
		
		result.addInfoMessage("Artigo(s) eliminados com sucesso");
		
		return result;
		
	}
	

}
