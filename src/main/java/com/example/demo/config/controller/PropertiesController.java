package com.example.demo.config.controller;

import static org.assertj.core.api.Assertions.linesOf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.MyProperties;

@RequestMapping("/properties")
@RestController
public class PropertiesController {

	private static final Logger LOGGER=LoggerFactory.getLogger(PropertiesController.class);
	
	private final MyProperties myProperties;
	
	@Autowired
	public PropertiesController(MyProperties myProperties) {
		// TODO 自动生成的构造函数存根
		this.myProperties=myProperties;
	}
	
	@GetMapping("/my")
	public MyProperties myPropertiesMethod() {
		LOGGER.info("=================================================================================================");
		LOGGER.info(myProperties.toString());
		LOGGER.info("=================================================================================================");
		return this.myProperties;
	}
}
