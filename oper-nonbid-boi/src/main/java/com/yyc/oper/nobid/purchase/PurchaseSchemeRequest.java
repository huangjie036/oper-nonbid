package com.yyc.oper.nobid.purchase;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 采购方案列表查询参数
 */
@ApiModel(value="PurchaseSchemeRequest",description="采购方案列表查询")
public class PurchaseSchemeRequest extends BasePageBean implements Serializable {

	private static final long		serialVersionUID	= 5228291094378654114L;
	/** 合包编号 **/
	private String					mergeId;
	
	/** 批次编码 **/
	@ApiModelProperty("批次编码")
	private String                  batchId;
	
	/** 批次名称 **/
	@JsonIgnore
	private String                  batchNum;
	
	/** 方案编号 **/
	@ApiModelProperty("方案编号")
	private String					purchaseSchemeId;

	/** 方案名称 **/
	@ApiModelProperty("方案名称")
	private String					purchaseSchemeName;

	/** 采购方式 **/
	@ApiModelProperty("采购方式")
	private String					purchaseWay;

	/** 采购策略 **/
	@ApiModelProperty("采购策略")
	private String					purchaseStrategy;

	/** 采购方案资质要求 **/
	@ApiModelProperty("采购方案资质要求")
	private String					qualification;
	
	/** 需求供应商数量 **/
	@ApiModelProperty("需求供应商数量")
	private String					recommendSupplierNum;
	
	/** 采购方案流转状态 **/
	@JsonIgnore
	private String					state;
	
	/** 是否物资 0物资，1非物资 **/
	@JsonIgnore
	private String					isMat;
	
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getPurchaseSchemeId() {
		return purchaseSchemeId;
	}

	public void setPurchaseSchemeId(String purchaseSchemeId) {
		this.purchaseSchemeId = purchaseSchemeId;
	}

	public String getPurchaseSchemeName() {
		return purchaseSchemeName;
	}

	public void setPurchaseSchemeName(String purchaseSchemeName) {
		this.purchaseSchemeName = purchaseSchemeName;
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

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getRecommendSupplierNum() {
		return recommendSupplierNum;
	}

	public void setRecommendSupplierNum(String recommendSupplierNum) {
		this.recommendSupplierNum = recommendSupplierNum;
	}

	public String getIsMat() {
		return isMat;
	}

	public void setIsMat(String isMat) {
		this.isMat = isMat;
	}
    

}
