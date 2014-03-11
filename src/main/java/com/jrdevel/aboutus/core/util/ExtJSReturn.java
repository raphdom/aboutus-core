package com.jrdevel.aboutus.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jrdevel.aboutus.core.common.to.Message;
import com.jrdevel.aboutus.core.common.to.ResultObject;

/**
 * @author Raphael Domingues
 *
 */
public class ExtJSReturn {
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param contacts
	 * @return
	 */
	public static Map<String,Object> mapOK(){

		Map<String,Object> modelMap = new HashMap<String,Object>(1);
		modelMap.put("success", true);

		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * @param beans
	 * @return
	 */
	public static Map<String,Object> mapOK(List<?> beans){

		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", beans.size());
		modelMap.put("data", beans);
		modelMap.put("success", true);

		return modelMap;
	}
	
	/**
	 * Generates modelMap to return in the modelAndView
	 * @param beans
	 * @return
	 */
	public static Map<String,Object> mapOK(Object bean){

		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("data", bean);
		modelMap.put("success", true);

		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView
	 * @param beans
	 * @return
	 */
	public static Map<String,Object> mapOK(List<?> beans, int total){

		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", total);
		modelMap.put("data", beans);
		modelMap.put("success", true);

		return modelMap;
	}

	/**
	 * Generates modelMap to return in the modelAndView in case
	 * of exception
	 * @param msg message
	 * @return
	 */
	public static Map<String,Object> mapError(String msg){

		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		List<Message> messages = new ArrayList<Message>();
		messages.add(new Message(ResultObject.ERROR,msg));
		modelMap.put("messages", messages);
		modelMap.put("success", false);

		return modelMap;
	} 

}
