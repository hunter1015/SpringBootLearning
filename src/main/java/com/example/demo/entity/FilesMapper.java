package com.example.demo.entity;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * t_file 操作，继承 BaseMapper<T> 就可以了，是不是有点类似 JpaRepository
 *
 * @author Levin
 * @since 2018/5/10 0007
 */
public interface FilesMapper extends BaseMapper<Files> {

    /**
     * 根据文件名统计（TODO 假设它是一个很复杂的SQL）
     *
     * @param filename 文件名
     * @return 统计结果
     */
    int countByUsername(String filename);
    
	List<Files> findByFilename(String filename);
	
	int insertFile(String filename,String filecode);
}
