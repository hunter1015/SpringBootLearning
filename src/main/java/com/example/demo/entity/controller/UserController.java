package com.example.demo.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.UserMapper;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	
	@GetMapping("")
	public String entryPage() {
		return "欢迎来到【用户】模块根页面";
	}
	
	@GetMapping("/userlist")
	public List<User> name() {
		return userMapper.findByUsername("u1");
		
	}
}
