package com.yyc.oper.nobid.mat;

import java.io.Serializable;
import java.util.Date;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;

/**
 * 非招标物资计划列表 
 * */
@ApiModel(value="MatplanResponse",description="非招标物资计划列表")
public class MatplanResponse extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String matplanId;//物资计划id
	
	private String serialNum;//序号
	
	private String packageNum;//包号

    private String packageName;//包名
    
    private String batchNum;//批次号
    
    private String projectNum;//项目编号
    
    private String projectName;//项目名称
    
	private String purchaseWay;//采购方式
	
	private String purchaseStrategy;//采购策略

	private String demandCompany;//需求单位

    private String purchaseNum;//erp采购申请号

    private String fundSource;//资金来源

    private String approvalNum;//批准文号

    private String matplanSource;//物资计划来源

    private String state;//状态 1待提交，2待审核，3待确认，4已确认，5已驳回

    private String opeBy;//操作人

    private Date opeTime;//操作时间

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPurchaseWay() {
		return purchaseWay;
	}

	public void setPurchaseWay(String purchaseWay) {
		this.purchaseWay = purchaseWay;
	}

	public String getPurchaseStrategy() {
		return purchaseStrategy;
	}

	public void setPurchaseStrategy(String purchaseStrategy) {
		this.purchaseStrategy = purchaseStrategy;
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

	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getApprovalNum() {
		return approvalNum;
	}

	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}

	public String getMatplanSource() {
		return matplanSource;
	}

	public void setMatplanSource(String matplanSource) {
		this.matplanSource = matplanSource;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOpeBy() {
		return opeBy;
	}

	public void setOpeBy(String opeBy) {
		this.opeBy = opeBy;
	}

	public Date getOpeTime() {
		return opeTime;
	}

	public void setOpeTime(Date opeTime) {
		this.opeTime = opeTime;
	}

	public String getMatplanId() {
		return matplanId;
	}

	public void setMatplanId(String matplanId) {
		this.matplanId = matplanId;
	}

}