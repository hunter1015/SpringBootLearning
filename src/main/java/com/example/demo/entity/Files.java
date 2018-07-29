package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_file")
public class Files implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6541608868053074376L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String filename;
	private String filecode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilecode() {
		return filecode;
	}

	public void setFilecode(String filecode) {
		this.filecode = filecode;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return "文件" + id + " " + filename + " " + filecode;
	}
}
