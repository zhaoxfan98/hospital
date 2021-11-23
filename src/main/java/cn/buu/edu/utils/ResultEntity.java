package cn.buu.edu.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultEntity {

	
	private Integer statusCode;
	
	private String message;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public static ResultEntity success(String message) {
		ResultEntity entity = new ResultEntity();
		entity.setStatusCode(200);
		entity.setMessage(message);
		return entity;
	}
	
	public static ResultEntity error(String message) {
		ResultEntity entity = new ResultEntity();
		entity.setStatusCode(500);
		entity.setMessage(message);
		return entity;
	}

	
	public ResultEntity put(String key, Object value) {
		this.map.put(key, value);
		return this;
	}
	
	
	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
}
