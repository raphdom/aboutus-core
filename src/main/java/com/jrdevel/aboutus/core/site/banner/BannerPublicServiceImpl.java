package com.jrdevel.aboutus.core.site.banner;

import java.util.List;

import net.aboutchurch.common.dto.BannerDTO;
import net.aboutchurch.common.dto.BannerListDTO;
import net.aboutchurch.common.services.BannerPublicService;
import net.aboutchurch.common.to.ListResult;
import net.aboutchurch.common.to.ResultObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Banner;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class BannerPublicServiceImpl implements BannerPublicService{

	private static final long serialVersionUID = 8313063981308247747L;
	
	@Autowired
	private BannerDAO bannerDAO;
	
	@Transactional
	public ResultObject list(Integer limit) {
		ResultObject result = new ResultObject();
		
		ListResult<BannerListSiteView> listResult = bannerDAO.getHomePageBanners();
		
		List<BannerListDTO> dtos = BannerMappingHelper.listSiteViewTolistDTO(listResult.getData());
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
	}

	@Transactional
	public ResultObject get(Integer id) {
		ResultObject result = new ResultObject();
		
		Banner bannerDB = bannerDAO.findById(id, false);
		
		if (bannerDB != null && bannerDB.getId() != null){
			
			BannerDTO dto = BannerMappingHelper.beanToDTO(bannerDB);
			
			result.setData(dto);
			
		}else{
			result.setSuccess(false);
			result.addErrorMessage("Banner não existe.");
		}
		
		return result;
	}

}
