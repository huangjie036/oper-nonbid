package com.yyc.oper.nobid.mat;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 非招标物资计划表查询 参数 nonbid_matplan
 * */
@ApiModel(value="MatplanQueryRequest",description="非招标物资计划表查询 参数")
public class MatplanQueryRequest extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("物资计划id")
	private String matplanId;//物资计划id

	@ApiModelProperty("非物资计划id")
	private String nonMatplanId;//物资计划id

	@ApiModelProperty("合包id")
	private String mergeId;//合包id
	
	@ApiModelProperty("采购结果id")
	private String resultId;//采购结果id

	@ApiModelProperty("开始时间")
	private String startTime;//开始时间

	@ApiModelProperty("结束时间")
	private String endTime;//结束时间

	@ApiModelProperty("物料名称")
	private String matDes;//物料描述
    
	@ApiModelProperty("物料分类id数组")
	private String[] matGroupIds;//物料分类id数组

	@ApiModelProperty("项目名称")
	private String projectName;//项目名称

	@ApiModelProperty("项目编号")
	private String projectNum;//项目编号

	@ApiModelProperty("需求单位")
	private String demandCompany;//需求单位

	@ApiModelProperty("采购方案id")
	private String purchaseSchmeId;//采购方案id

	@ApiModelProperty("采购申请号")
	private String purchaseNum;//erp采购申请号

	@ApiModelProperty("供应商name")
	private String supplierName;//供应商name
	
	@ApiModelProperty("状态")
	private String state;//状态 1待提交，2待审核，3待确认，4已确认，5已驳回

	@JsonIgnore
	private String planState;//状态 1待提交，2待审核，3待确认，4已确认，5已驳回

	@JsonIgnore
	private String schemeState;//状态 1待提交，2待审核，3待确认，4已确认，5已驳回

	@JsonIgnore
	private String resultState;//状态 1待提交，2待审核，3待确认，4已确认，5已驳回

	public String getMatplanId() {
		return matplanId;
	}

	public void setMatplanId(String matplanId) {
		this.matplanId = matplanId;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMatDes() {
		return matDes;
	}

	public void setMatDes(String matDes) {
		this.matDes = matDes;
	}

	public String[] getMatGroupIds() {
		return matGroupIds;
	}

	public void setMatGroupIds(String[] matGroupIds) {
		this.matGroupIds = matGroupIds;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	public String getDemandCompany() {
		return demandCompany;
	}

	public void setDemandCompany(String demandCompany) {
		this.demandCompany = demandCompany;
	}

	public String getPurchaseSchmeId() {
		return purchaseSchmeId;
	}

	public void setPurchaseSchmeId(String purchaseSchmeId) {
		this.purchaseSchmeId = purchaseSchmeId;
	}

	public String getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPlanState() {
		return planState;
	}

	public void setPlanState(String planState) {
		this.planState = planState;
	}

	public String getSchemeState() {
		return schemeState;
	}

	public void setSchemeState(String schemeState) {
		this.schemeState = schemeState;
	}

	public String getResultState() {
		return resultState;
	}

	public void setResultState(String resultState) {
		this.resultState = resultState;
	}

	public String getNonMatplanId() {
		return nonMatplanId;
	}

	public void setNonMatplanId(String nonMatplanId) {
		this.nonMatplanId = nonMatplanId;
	}

}