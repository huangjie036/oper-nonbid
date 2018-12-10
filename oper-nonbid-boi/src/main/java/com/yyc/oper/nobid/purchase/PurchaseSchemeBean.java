package com.yyc.oper.nobid.purchase;

import java.io.Serializable;
import java.util.List;

import com.yyc.oper.nobid.base.BasePageBean;
import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.mat.MatplanBean;
import com.yyc.oper.nobid.mat.NonMatplanBean;
import com.yyc.oper.nobid.supplier.SupplierBean;

public class PurchaseSchemeBean extends BasePageBean implements Serializable {

	private static final long		serialVersionUID	= 5228291094378654114L;
	/** 方案编号 **/
	private String					purchaseSchemeId;

	/** 合包编号 **/
	private String					mergeId;

	/** 方案名称 **/
	private String					purchaseSchemeName;

	/** 项目编号 **/
	private String					projectId;

	/** 项目名称 **/
	private String					projectName;

	/** 批次号名称 **/
	private String					batchName;

	/** 供应商需求数量 **/
	private Integer					supplierRequiredNum;

	/** 专家需求数量 **/
	private Integer					exportRequiredNum;

	/** 供应商抽取方式 **/
	private String					supplierExtractMethod;

	/** 专家抽取方式 **/
	private String					exportExtractMethod;

	/** 采购方式 **/
	private String					purchaseWay;

	/** 采购策略 **/
	private String					purchaseStrategy;

	/** 采购方案流转状态 **/
	private String					state;

	/** 采购方案资质要求 **/
	private String					qualification;

	/** 采购方案供应商抽取状态 **/
	private String					supplierState;

	/** 采购方案专家抽取状态 **/
	private String					exportState;

	/** 采购方案抽取的供应商或者服务商状态 **/
	private List<SupplierBean>		extactedSupplierList;

	/** 采购方案抽取的专家状态 **/
	private List<ExpertBean>		extactedExpertBeanList;

	/** 采购方案物料信息 **/
	private List<MatplanBean>		matplanBeanList;

	/** 采购方案购买的服务信息 **/
	private List<NonMatplanBean>	nonMatplanBeanList;



	public PurchaseSchemeBean() {

	}



	public String getPurchaseSchemeId() {
		return purchaseSchemeId;
	}



	public void setPurchaseSchemeId(String purchaseSchemeId) {
		this.purchaseSchemeId = purchaseSchemeId;
	}



	public String getMergeId() {
		return mergeId;
	}



	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}



	public String getPurchaseSchemeName() {
		return purchaseSchemeName;
	}



	public void setPurchaseSchemeName(String purchaseSchemeName) {
		this.purchaseSchemeName = purchaseSchemeName;
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



	public String getBatchName() {
		return batchName;
	}



	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}



	public Integer getSupplierRequiredNum() {
		return supplierRequiredNum;
	}



	public void setSupplierRequiredNum(Integer supplierRequiredNum) {
		this.supplierRequiredNum = supplierRequiredNum;
	}



	public String getPurchaseWay() {
		return purchaseWay;
	}



	public void setPurchaseWay(String purchaseWay) {
		this.purchaseWay = purchaseWay;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getSupplierState() {
		return supplierState;
	}



	public void setSupplierState(String supplierState) {
		this.supplierState = supplierState;
	}



	public String getExportState() {
		return exportState;
	}



	public void setExportState(String exportState) {
		this.exportState = exportState;
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



	public Integer getExportRequiredNum() {
		return exportRequiredNum;
	}



	public void setExportRequiredNum(Integer exportRequiredNum) {
		this.exportRequiredNum = exportRequiredNum;
	}



	public String getSupplierExtractMethod() {
		return supplierExtractMethod;
	}



	public void setSupplierExtractMethod(String supplierExtractMethod) {
		this.supplierExtractMethod = supplierExtractMethod;
	}



	public String getExportExtractMethod() {
		return exportExtractMethod;
	}



	public void setExportExtractMethod(String exportExtractMethod) {
		this.exportExtractMethod = exportExtractMethod;
	}



	public List<SupplierBean> getExtactedSupplierList() {
		return extactedSupplierList;
	}



	public void setExtactedSupplierList(List<SupplierBean> extactedSupplierList) {
		this.extactedSupplierList = extactedSupplierList;
	}



	public List<ExpertBean> getExtactedExpertBeanList() {
		return extactedExpertBeanList;
	}



	public void setExtactedExpertBeanList(List<ExpertBean> extactedExpertBeanList) {
		this.extactedExpertBeanList = extactedExpertBeanList;
	}



	public List<MatplanBean> getMatplanBeanList() {
		return matplanBeanList;
	}



	public void setMatplanBeanList(List<MatplanBean> matplanBeanList) {
		this.matplanBeanList = matplanBeanList;
	}



	public List<NonMatplanBean> getNonMatplanBeanList() {
		return nonMatplanBeanList;
	}



	public void setNonMatplanBeanList(List<NonMatplanBean> nonMatplanBeanList) {
		this.nonMatplanBeanList = nonMatplanBeanList;
	}

}
