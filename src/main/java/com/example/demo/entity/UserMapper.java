package com.example.demo.entity;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM t_user where username=#{username}")
	List<User> findByUsername(@Param("username") String userName);
	
	@Insert("INSERT INTO t_user (`username`,`password`) VALUES (#{username},#{password})")
	int insertUser(@Param("username") String userName,@Param("password") String password);
}
