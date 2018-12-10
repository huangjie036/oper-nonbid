package com.yyc.oper.nobid.mat;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 非招标非物资计划表列表  参数   nonbid_non_matplan
 * */
@ApiModel(value="NonMatplanIdRequest",description="非招标非物资计划表列表  参数")
public class NonMatplanIdRequest extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("非物资计划id")
	private String nonMatplanId;//非物资计划id
	
	@ApiModelProperty("状态")
	private String state;//状态
	
	@ApiModelProperty("非物资计划id数组")
	private String[] nonMatplanIds;//非物资计划id数组

	
	public String getNonMatplanId() {
		return nonMatplanId;
	}

	public void setNonMatplanId(String nonMatplanId) {
		this.nonMatplanId = nonMatplanId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String[] getNonMatplanIds() {
		return nonMatplanIds;
	}

	public void setNonMatplanIds(String[] nonMatplanIds) {
		this.nonMatplanIds = nonMatplanIds;
	}
	
	
}