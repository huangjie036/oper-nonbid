package com.yyc.oper.nobid.supplier;

import java.io.Serializable;
import java.util.Date;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;

/**
 * 非招标中标供应商表 返回  nonbid_supplier_win
 * */
@ApiModel(value="SupplierWinResponse",description="非招标中标供应商表 返回")
public class SupplierWinResponse extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;//中标结果id
	
    private String opeBy;//操作人

    private Date opeTime;//操作时间

    private String state;//状态

    private String purchaseResult;//采购结果

    private String remark;//中标备注

    private String money;//中标金额
    
    private String purchaseSchmeId;//采购方案id

    private String purchaseSchmeName;//采购方案名称
    
    private String purchaseWay;//采购方式
    
    private String batchName;//批次名称
    
    private String packageNum;//包号
    
    private String mergeId;//合包id
    
    private String supplierWinId;//中标供应商id
    
    private String supplierWinName;//中标供应商名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPurchaseResult() {
		return purchaseResult;
	}

	public void setPurchaseResult(String purchaseResult) {
		this.purchaseResult = purchaseResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
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

	public String getPurchaseWay() {
		return purchaseWay;
	}

	public void setPurchaseWay(String purchaseWay) {
		this.purchaseWay = purchaseWay;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getSupplierWinId() {
		return supplierWinId;
	}

	public void setSupplierWinId(String supplierWinId) {
		this.supplierWinId = supplierWinId;
	}

	public String getSupplierWinName() {
		return supplierWinName;
	}

	public void setSupplierWinName(String supplierWinName) {
		this.supplierWinName = supplierWinName;
	}
    
}