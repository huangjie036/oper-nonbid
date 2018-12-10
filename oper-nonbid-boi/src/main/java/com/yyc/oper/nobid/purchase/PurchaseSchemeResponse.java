package com.yyc.oper.nobid.purchase;

import java.io.Serializable;
import java.util.List;

import com.yyc.oper.nobid.base.BasePageBean;
import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.mat.MatplanMatBean;
import com.yyc.oper.nobid.supplier.SupplierBean;


public class PurchaseSchemeResponse extends BasePageBean implements Serializable {

	private static final long		serialVersionUID	= 5228291094378654114L;
	/** 方案编号 **/
	private String					purchaseSchmeId;

	/** 方案名称 **/
	private String					purchaseSchmeName;

	/** 合包编号 **/
	private String					mergeId;

	/** 批次编码 **/
	private String                  batchNum;
	
	/** 包号 **/
	private String                  packageNum;

	/** 供应商需求数量 **/
	private Integer					demandSupplierNum;

	/** 采购方式 **/
	private String					purchaseWay;

	/** 采购策略 **/
	private String					purchaseStrategy;

	/** 采购方案资质要求 **/
	private String					qualification;

	/** 操作人 **/
    private String                  opeBy;

    /** 操作时间 **/
    private String                  opeTime;
    
    /** 抽取供应商数量 **/
    private String                  supplierExtractNum;
    
    /** 推荐供应商数量 **/
    private Integer                  recommendSupplierNum;

    /** 非招标-物资计划的物资表  集合 **/
    private List<MatplanMatBean>    matplanMatBeanList;
    
    /** 采购方案抽取供应商集合 **/
	private List<SupplierBean>		extactedSupplierList;
	
	/** 采购方案抽取专家集合 **/
	private List<ExpertBean>		extactedExpertBeanList;
	
	/** 邀请推荐供应商id数组 **/
	private String[]                invitationSupplierIds;

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

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public Integer getDemandSupplierNum() {
		return demandSupplierNum;
	}

	public void setDemandSupplierNum(Integer demandSupplierNum) {
		this.demandSupplierNum = demandSupplierNum;
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getOpeBy() {
		return opeBy;
	}

	public void setOpeBy(String opeBy) {
		this.opeBy = opeBy;
	}

	public String getOpeTime() {
		return opeTime;
	}

	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}

	public String getSupplierExtractNum() {
		return supplierExtractNum;
	}

	public void setSupplierExtractNum(String supplierExtractNum) {
		this.supplierExtractNum = supplierExtractNum;
	}

	public List<MatplanMatBean> getMatplanMatBeanList() {
		return matplanMatBeanList;
	}

	public void setMatplanMatBeanList(List<MatplanMatBean> matplanMatBeanList) {
		this.matplanMatBeanList = matplanMatBeanList;
	}

	public List<SupplierBean> getExtactedSupplierList() {
		return extactedSupplierList;
	}

	public void setExtactedSupplierList(List<SupplierBean> extactedSupplierList) {
		this.extactedSupplierList = extactedSupplierList;
	}

	public String getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}

	public Integer getRecommendSupplierNum() {
		return recommendSupplierNum;
	}

	public void setRecommendSupplierNum(Integer recommendSupplierNum) {
		this.recommendSupplierNum = recommendSupplierNum;
	}

	public String[] getInvitationSupplierIds() {
		return invitationSupplierIds;
	}

	public void setInvitationSupplierIds(String[] invitationSupplierIds) {
//		String[] array = new String[invitationSupplierIds.length];
//		for(int i=0; i<invitationSupplierIds.length; i++) {
//			array[i] = (String)invitationSupplierIds[i];
//		}
		this.invitationSupplierIds = invitationSupplierIds;
	}

	public List<ExpertBean> getExtactedExpertBeanList() {
		return extactedExpertBeanList;
	}

	public void setExtactedExpertBeanList(List<ExpertBean> extactedExpertBeanList) {
		this.extactedExpertBeanList = extactedExpertBeanList;
	}

	

	
}
