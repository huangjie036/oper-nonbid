package com.yyc.oper.nobid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 从application.properties读取属性
 * 
 * @author jooer
 *
 */
@ConfigurationProperties(prefix = "oper")
public class OperProperties {
	private String fileExtension;

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
