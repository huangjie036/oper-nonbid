package com.yyc.oper.nobid.mat;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;

/**
 * 非招标物资计划表查询 nonbid_matplan
 * */
@ApiModel(value="MatplanQueryResponse",description="非招标物资计划表查询")
public class MatplanQueryResponse extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String matplanId;//物资计划id
	
	private String nonMatplanId;//非物资计划id

    private String mergeId;//合包id
    
    private String resultId;//采购结果id

	private String projectName;//项目名称

    private String projectNum;//项目编号

    private String batchNum;//批次号

    private String purchaseWay;//采购方式

    private String demandCompany;//需求单位

    private String purchaseNum;//erp采购申请号

    private String matDes;//物料描述

    private String state;//状态 1待提交，2待审核，3待确认，4已确认，5已驳回

    private String purchaseSchmeId;//采购方案id

    private String purchaseSchmeName;//采购方案名称

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

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getPurchaseWay() {
		return purchaseWay;
	}

	public void setPurchaseWay(String purchaseWay) {
		this.purchaseWay = purchaseWay;
	}

	public String getDemandCompany() {
		return demandCompany;
	}

	public void setDemandCompany(String demandCompany) {
		this.demandCompany = demandCompany;
	}

	public String getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public String getMatDes() {
		return matDes;
	}

	public void setMatDes(String matDes) {
		this.matDes = matDes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPurchaseSchmeId() {
		return purchaseSchmeId;
	}

	public void setPurchaseSchmeId(String purchaseSchmeId) {
		this.purchaseSchmeId = purchaseSchmeId;
	}

	public String getPurchaseSchmeName() {
		return purchaseSchmeName;
	}

	public void setPurchaseSchmeName(String purchaseSchmeName) {
		this.purchaseSchmeName = purchaseSchmeName;
	}

	public String getNonMatplanId() {
		return nonMatplanId;
	}

	public void setNonMatplanId(String nonMatplanId) {
		this.nonMatplanId = nonMatplanId;
	}

}