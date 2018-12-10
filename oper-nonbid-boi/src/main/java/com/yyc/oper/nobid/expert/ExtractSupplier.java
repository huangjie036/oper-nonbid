package com.yyc.oper.nobid.expert;

import java.io.Serializable;
import java.sql.Timestamp;

import com.yyc.oper.nobid.base.BasePageBean;

public class ExtractSupplier extends BasePageBean implements Serializable {

	private static final long serialVersionUID = 7300888493379689017L;

	/** 抽取记录编号 **/
	private String extractId;

	/** 多个计划方案形成的合包编号 **/
	private String mergeId;

	/** 抽取的采购方案编号 **/
	private String purchaseSchmeId;

	/** 抽取的采购方案名称 **/
	private String purchaseSchmeName;

	/** 抽取的采购方案时间 **/
	private Timestamp purchaseSchmeCreateTime;

	/** 抽取的供应商编号 **/
	private String supplierId;

	/** 主数据供应商编号（已经中标的供应商） **/
	private String supplierWinId;

	/** 抽取方式 **/
	private String extractMethod;

	/** 抽取数量 **/
	private Integer extractNum;

	/** 抽取日期 **/
	private Timestamp extractDate;

	/** 抽取记录的创建时间 **/
	private Timestamp createDate;

	/** 抽取记录的创建人 **/
	private String createBy;

	/** 抽取记录的操作人VS抽取人 **/
	private String extractedBy;

	/** 抽取记录状态 **/

	private String				state;
	
	/** 供应商抽取状态 **/
	private String				supplierExtractState;



	
	private String extractRecordCode;


	
	
	/**
	 * 删除标识
	 */
	private String del;

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public ExtractSupplier() {
	}

	public String getExtractId() {
		return extractId;
	}

	public void setExtractId(String extractId) {
		this.extractId = extractId;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
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

	public Timestamp getPurchaseSchmeCreateTime() {
		return purchaseSchmeCreateTime;
	}

	public void setPurchaseSchmeCreateTime(Timestamp purchaseSchmeCreateTime) {
		this.purchaseSchmeCreateTime = purchaseSchmeCreateTime;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierWinId() {
		return supplierWinId;
	}

	public void setSupplierWinId(String supplierWinId) {
		this.supplierWinId = supplierWinId;
	}

	public Timestamp getExtractDate() {
		return extractDate;
	}

	public void setExtractDate(Timestamp extractDate) {
		this.extractDate = extractDate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getExtractedBy() {
		return extractedBy;
	}

	public void setExtractedBy(String extractedBy) {
		this.extractedBy = extractedBy;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExtractMethod() {
		return extractMethod;
	}

	public void setExtractMethod(String extractMethod) {
		this.extractMethod = extractMethod;
	}

	public Integer getExtractNum() {
		return extractNum;
	}

	public void setExtractNum(Integer extractNum) {
		this.extractNum = extractNum;
	}




	public String getSupplierExtractState() {
		return supplierExtractState;
	}



	public void setSupplierExtractState(String supplierExtractState) {
		this.supplierExtractState = supplierExtractState;
	}
	

	public String getExtractRecordCode() {
		return extractRecordCode;
	}

	public void setExtractRecordCode(String extractRecordCode) {
		this.extractRecordCode = extractRecordCode;
	}


}
