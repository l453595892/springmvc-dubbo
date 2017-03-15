package com.common.util.Return;

import java.io.Serializable;

/**
 * 作为服务器返回JSON格式的结果对象
 * @author lwj
 *
 */
public class ReturnModel implements Serializable{
	
	private int status;//处理状态
	private String message;//返回的消息
	private Object data;//返回的数据
	
	public ReturnModel() {
		super();
	}
	public ReturnModel(Object data, String message,Boolean status) {
		super();
		this.data = data;
		if(status){
			this.status = 1;
		}else{
			this.status = 0;
		}
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
