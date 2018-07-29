package com.example.demo.entity;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1369148652554782331L;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private Long id;
    private String username;
    private String password;
	
    
    @Override
    public String toString() {
    	// TODO 自动生成的方法存根
    	return "用户"+id+" "+username+" "+password;
    }
}
