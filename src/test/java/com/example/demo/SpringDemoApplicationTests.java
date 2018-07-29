package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Files;
import com.example.demo.entity.FilesMapper;
import com.example.demo.entity.User;
import com.example.demo.entity.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDemoApplicationTests {
	private final static Logger testlog=LoggerFactory.getLogger(SpringDemoApplicationTests.class);
	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private FilesMapper filesMapper;
	
	public void testUserMapper() {
		int count=0;
		while (count<20) {
			userMapper.insertUser("u"+count, "p"+count);
			count++;
		}
        final int row1 = userMapper.insertUser("u1", "p1");
        testlog.info("[添加结果] - [{}]", row1);
        final int row2 = userMapper.insertUser("u2", "p2");
        testlog.info("[添加结果] - [{}]", row2);
        final int row3 = userMapper.insertUser("u1", "p3");
        testlog.info("[添加结果] - [{}]", row3);
        final List<User> u1 = userMapper.findByUsername("u1");
        testlog.info("[根据用户名查询] - [{}]", u1);
	}
	
	@Test
	public void testFileMapper() {
		int count=0;
		while (count<20) {
			filesMapper.insertFile("File"+count, System.currentTimeMillis()+"-"+count);
			count++;
		}
        final int filecount = filesMapper.countByUsername("File10");
        testlog.info("[查询指定文件名文件数量] - [{}]", filecount);
        final List<Files> u1 = filesMapper.findByFilename("File11");
        testlog.info("[根据文件名查询文件信息] - [{}]", u1);
	}

}
