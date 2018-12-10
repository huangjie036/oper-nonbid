package com.yyc.oper.nobid.base;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 参数类  物资计划id数组
 * */
@ApiModel(value="ArrayMatplanId",description="物资计划id数组 参数")
public class ArrayMatplan extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("物资计划id数组") 
    private String[] matplanIds;//物资计划id数组

	public String[] getMatplanIds() {
		return matplanIds;
	}

	public void setMatplanIds(String[] matplanIds) {
		this.matplanIds = matplanIds;
	}
	
}
