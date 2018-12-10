package com.yyc.oper.nobid.supplier;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierEvalShowListBean",description="供应商评价返回列表")
public class SupplierEvalShowListBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("供应商评价id")
	private String supplierEvalId;//供应商评价id
	
	@ApiModelProperty("供应商id,页面查询参数")
    private String supplierId;//供应商id
	
	@ApiModelProperty("供应商名称")
	private String supplierName;//供应商名称
	
	@ApiModelProperty("项目名称")
	private String projectName;//项目名称
	
	@ApiModelProperty("问题发生环节名称")
	private String questionLinkName;//问题发生环节名称
	
	@ApiModelProperty("抽取次数")
	private String extractTime;//抽取次数
	
	@ApiModelProperty("中标次数")
	private String winTime;//中标次数
	
	@ApiModelProperty("发生次数")
	private String occurTime;//发生次数
	
	@ApiModelProperty("备注")
	private String remark;//备注
	
	@ApiModelProperty("问题环节id")
    private String questionLinkId;//问题环节id
    
	@ApiModelProperty("评价项id")
    private String supplierEvalTypeId;//评价项id

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}





	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getQuestionLinkName() {
		return questionLinkName;
	}

	public void setQuestionLinkName(String questionLinkName) {
		this.questionLinkName = questionLinkName;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public String getWinTime() {
		return winTime;
	}

	public void setWinTime(String winTime) {
		this.winTime = winTime;
	}

	public String getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}

	public String getSupplierEvalId() {
		return supplierEvalId;
	}

	public void setSupplierEvalId(String supplierEvalId) {
		this.supplierEvalId = supplierEvalId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getQuestionLinkId() {
		return questionLinkId;
	}

	public void setQuestionLinkId(String questionLinkId) {
		this.questionLinkId = questionLinkId;
	}

	public String getSupplierEvalTypeId() {
		return supplierEvalTypeId;
	}

	public void setSupplierEvalTypeId(String supplierEvalTypeId) {
		this.supplierEvalTypeId = supplierEvalTypeId;
	}
	
	
	
	
	
	
}
