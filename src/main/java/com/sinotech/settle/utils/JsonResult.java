package com.sinotech.settle.utils;

import java.io.Serializable;

/**
 * 处理结果封装成Json的类
 * @author JAMES 2017-04-08
 */
public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int SUCCESS=0;
	public static final int ERROR=1;
	
	private int state;
	private T data;
	private String message;
	private int type;
	
	public JsonResult() {
	}
	
	public JsonResult(T t){
		state = SUCCESS;
		data = t;
		message="";
	}
	
	public JsonResult(int stat,String msg){
		this.state = stat;
		this.message =msg;
	}
	
	
	public JsonResult(Throwable e){
		state = ERROR;
		data = null;
		message = e.getMessage();
	}

	public JsonResult(int state, Throwable e) {
		this.state = state;
		this.message = e.getMessage();
		this.data = null;
	}
	

	public JsonResult(int state, T data, String message) {
		this.state = state;
		this.data = data;
		this.message = message;
	}
	
	public JsonResult(int state, T data, int type) {
		super();
		this.state = state;
		this.data = data;
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	

}
