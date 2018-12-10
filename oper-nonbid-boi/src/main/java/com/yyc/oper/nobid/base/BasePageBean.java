package com.yyc.oper.nobid.base;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="BasePageBean",description="基础bean")
public class BasePageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// 页码
	@ApiModelProperty("页码")
	private Integer pageNum;
	// 页面记录数量
	@ApiModelProperty("页面记录数量")
	private Integer pageSize;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
