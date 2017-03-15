package com.common.vo;

import java.io.Serializable;
/**
 * 
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = -7064020898680071328L;
	
	private Integer id;
	private String uname;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
}
