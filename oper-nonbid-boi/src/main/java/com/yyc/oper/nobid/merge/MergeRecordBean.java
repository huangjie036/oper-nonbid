package com.yyc.oper.nobid.merge;

import java.io.Serializable;
import java.util.Date;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * 非招标合包记录 nonbid_merge_record
 * */
public class MergeRecordBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mergeId;//合包id

    private String bagNum;

    private String qualification;//资质要求

    private String purchaseSchmeId;//采购方案id

    private String purchaseSchmeName;//采购方案名称

    private String batchNum;//批次编号

    private String projectId;//项目编码

    private String projectName;//项目名称

    private String purchaseWay;//采购方式

    private String purchaseStrategy;//采购策略

    private String demandSupplierNum;//需求供应商数量

    private String createBy;//创建人

    private Date createTime;//创建时间

    private String opeBy;//操作人

    private Date opeTime;//操作时间

    private String del;//删除标记

    private String state;//001-待提交 002-待审核 003-审核中 004-一已通过审核 005-已驳回

    private String expertExtractState;//专家抽取状态
    
    private String supplierExtractState;//供应商抽取状态
    
    private String packageNum;//包号
    
   	private String orgId;//组织机构编码
   	
   	private String isMat;//0物资，1非物资

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getBagNum() {
		return bagNum;
	}

	public void setBagNum(String bagNum) {
		this.bagNum = bagNum;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
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

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public String getDemandSupplierNum() {
		return demandSupplierNum;
	}

	public void setDemandSupplierNum(String demandSupplierNum) {
		this.demandSupplierNum = demandSupplierNum;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExpertExtractState() {
		return expertExtractState;
	}

	public void setExpertExtractState(String expertExtractState) {
		this.expertExtractState = expertExtractState;
	}

	public String getSupplierExtractState() {
		return supplierExtractState;
	}

	public void setSupplierExtractState(String supplierExtractState) {
		this.supplierExtractState = supplierExtractState;
	}

	public String getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getIsMat() {
		return isMat;
	}

	public void setIsMat(String isMat) {
		this.isMat = isMat;
	}
    

}