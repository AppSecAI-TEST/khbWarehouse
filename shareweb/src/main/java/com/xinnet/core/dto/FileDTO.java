package com.xinnet.core.dto;

import java.io.InputStream;

public class FileDTO {
    private InputStream in;
    private String fileName;
    private Long size;
    
   
	public FileDTO(InputStream in, String fileName, Long size) {
		super();
		this.in = in;
		this.fileName = fileName;
		this.size = size;
	}
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
}
