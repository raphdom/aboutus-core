package com.jrdevel.aboutus.core.audit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrdevel.aboutus.core.common.helper.MessageHelper;
import com.jrdevel.aboutus.core.common.helper.MessageKeyEnum;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
@Service
public class AuditServiceImpl implements AuditService{
	
	@Autowired
	private AuditDAO userDAO;
	
	@Transactional
	public ResultObject list(ListParams params) {
		
		ResultObject result = new ResultObject();
		
		ListResult<AuditListView> listResult = userDAO.findAllByView(params, AuditListView.class);
		
		List<AuditListDTO> dtos = new ArrayList<AuditListDTO>();
		
		for(AuditListView bean : listResult.getData()){
			AuditListDTO dto = new AuditListDTO();
			dto.setId(bean.getId());
			dto.setActionDate(bean.getActionDate());
			String action = bean.getUserName() + " ";
			if (bean.getActionId()==0){
				action += MessageHelper.getMessage(MessageKeyEnum.AUDIT_ACTION_ADD)+" ";
			}else if(bean.getActionId()==1){
				action += MessageHelper.getMessage(MessageKeyEnum.AUDIT_ACTION_UPDATE)+" ";
			}else{
				action += MessageHelper.getMessage(MessageKeyEnum.AUDIT_ACTION_DELETE)+ " ";
			}
			action += MessageHelper.getMessage(bean.getObjectName()) + " ";
			action += "\"" + bean.getObjectTitle() + "\"";
			dto.setAction(action);
			dtos.add(dto);
		}
		
		result.setData(dtos);
		result.setTotal(listResult.getTotal());
		
		return result;
	}
	
}
