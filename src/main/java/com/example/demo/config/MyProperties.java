package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @author YYH
 *
 */
@Component
@ConfigurationProperties(prefix="web")
public class MyProperties {
	private String developerName;
	
	 public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getDeveloperName() {
		return developerName;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return developerName;
	}
}
