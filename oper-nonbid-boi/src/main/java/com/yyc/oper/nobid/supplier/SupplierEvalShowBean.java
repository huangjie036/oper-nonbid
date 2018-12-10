package com.yyc.oper.nobid.supplier;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="SupplierEvalShowBean",description="根据供应商获取项目列表")
public class SupplierEvalShowBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("项目名称")
	private String projectName;//项目名称
	
	@ApiModelProperty("项目编码")
	private String projectId;//项目编码

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
}
