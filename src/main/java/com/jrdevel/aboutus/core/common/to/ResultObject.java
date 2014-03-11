package com.jrdevel.aboutus.core.common.to;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Raphael Domingues
 *
 */
public class ResultObject {
	
	private List<Message> messages = new ArrayList<Message>();
	private boolean success = true;
	private List<Object> data;
	private int total = 0;
	
	public static final int ERROR = 1;
	public static final int WARNING = 2;
	public static final int INFO = 3;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public void setData(Object data) {
		if (this.data == null){
			this.data = new ArrayList<Object>();
		}
		this.data.add(data);
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void addErrorMessage(String message){
		messages.add(new Message(ERROR,message));
	}
	public Map<String,? extends Object> toMap(){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("success", success);
		map.put("messages", messages);
		map.put("total", total);
		if (data != null && data.size()==1){
			map.put("data", data.get(0));
		}else{
			map.put("data", data);
		}
		return map;
	}

}
