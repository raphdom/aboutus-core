package com.jrdevel.aboutus.core.site.album;

import java.util.List;

import net.aboutchurch.common.dto.AlbumDTO;
import net.aboutchurch.common.dto.AlbumListDTO;
import net.aboutchurch.common.services.AlbumPublicService;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Album;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class AlbumPublicServiceImpl implements AlbumPublicService{
	
	@Autowired
	private AlbumDAO albumDAO;

	private static final long serialVersionUID = -36086702337935309L;

	@Transactional
	public ResultObject list(Integer limit) {
		ResultObject result = new ResultObject();
		
		ListResult<AlbumListSiteView> listResult = albumDAO.getHomePageAlbuns(limit);
		
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
	public ResultObject listByCategory(int categoryId) {
		
		ResultObject result = new ResultObject();
		
		ListResult<AlbumListSiteView> listResult = albumDAO.getAlbunsByCategory(categoryId);
		
		List<AlbumListDTO> dtos = AlbumMappingHelper.listSiteViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
		
	}

}
