package com.jrdevel.aboutus.core.site.banner;

import java.util.List;

import net.aboutchurch.common.dto.BannerDTO;
import net.aboutchurch.common.dto.BannerListDTO;
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
import com.jrdevel.aboutus.core.common.model.Banner;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class BannerServiceImpl implements BannerService{
	
	@Autowired
	private BannerDAO bannerDAO;
	
	private static final Logger logger = Logger.getLogger(BannerServiceImpl.class);
	
	@Transactional
	//@PreAuthorize("hasAuthority('ROLE_LIST_CATEGORY')")
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<BannerListView> listResult = bannerDAO.findAllByView(params, BannerListView.class);
		
		List<BannerListDTO> dtos = BannerMappingHelper.listViewTolistDTO(listResult.getData());
		
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
			result.addErrorMessage("Categoria não existe.");
		}
		
		return result;
	}
	
	@Transactional
	public ResultObject save(BannerDTO bannerDTO) {
		
		if (bannerDTO.getId() != null && bannerDTO.getId() != 0){
			return update(bannerDTO);
		}else{
			return insert(bannerDTO);
		}
	}

	@Transactional
	@PreAuthorize("hasAuthority('ROLE_INSERT_CATEGORY')")
	public ResultObject insert(BannerDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Banner entity = BannerMappingHelper.DTOToBean(dto);
		
		//Insert data
		entity.setId(null);
		entity.setCustomer(UserAuthenticatedManager.getCurrentCustomer());

		try {
			bannerDAO.makePersistent(entity);
			
			result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.BANNER_INSERTED));
			
		} catch (PlanExceededException e) {
			result.setSuccess(false);
		}
		
		return result;
		
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CATEGORY')")
	public ResultObject update(BannerDTO dto) {
		
		ResultObject result = new ResultObject();
		
		Banner banner = bannerDAO.findById(dto.getId(), false);
		
		if (banner != null && banner.getId() != null){
			
			banner = BannerMappingHelper.DTOToBean(dto, banner);
			
			try {
				bannerDAO.makePersistent(banner);
				
				result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.BANNER_UPDATED));
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
			Banner banner = bannerDAO.findById(id, false);
			bannerDAO.makeTransient(banner);
		}
		
		result.addInfoMessage(MessageHelper.getMessage(MessageKeyEnum.BANNER_DELETED));
		
		return result;
		
	}
	

}
