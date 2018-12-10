package com.yyc.oper.nobid.mat;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 非招标物资计划id 参数   nonbid_matplan
 * */
@ApiModel(value="MatplanIdRequest",description="非招标物资计划id 参数")
public class MatplanIdRequest extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("物资计划id")
	private String matplanId;//物资计划id
	
	@ApiModelProperty("状态")
	private String state;//状态
	
	public String getMatplanId() {
		return matplanId;
	}

	public void setMatplanId(String matplanId) {
		this.matplanId = matplanId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
    
}