package com.jrdevel.aboutus.core.church;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.model.Church;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class ChurchServiceImpl implements ChurchService{
	
	@Autowired
	private ChurchDAO churchDAO;
	
	@Transactional
	public ResultObject list(ListParams params) {
		ListResult<ChurchView> listResult = churchDAO.findAllByView(params, ChurchView.class);
		ResultObject resultObject = new ResultObject();
		resultObject.setData(listResult.getData());
		resultObject.setTotal(listResult.getTotal());
		return resultObject;
	}

	@Transactional
	public ResultObject insert(Church bean) {
		return null;
	}

	@Transactional
	public ResultObject get(Church bean) {
		return null;
	}

	@Transactional
	public ResultObject delete(List<Church> beans) {
		return null;
	}

	@Transactional
	public ResultObject update(Church bean) {
		return null;
	}


}
