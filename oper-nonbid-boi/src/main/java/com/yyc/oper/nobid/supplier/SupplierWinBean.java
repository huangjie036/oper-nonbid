package com.yyc.oper.nobid.supplier;

import java.io.Serializable;
import java.util.Date;

import com.yyc.oper.nobid.base.BasePageBean;
import com.yyc.oper.nobid.batch.BatchmanageBean;
import com.yyc.oper.nobid.merge.MergeRecordBean;

import io.swagger.annotations.ApiParam;

/**
 * 非招标中标供应商表  nonbid_supplier_win
 * */
public class SupplierWinBean extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;//中标结果id

    private String supplierWinId;//中标供应商id

    private String createBy;//创建人

    private Date createTime;//创建时间

    private String opeBy;//操作人

    private Date opeTime;//操作时间

    private String state;//状态 0未维护，1已维护

    private String purchaseResult;//采购结果

    private String remark;//中标备注

    private String money;//中标金额
    
    private MergeRecordBean mergeRecordBean;//采购方案表
    
    private BatchmanageBean batchmanageBean;//物资计划表 -> 批次管理
    
    @ApiParam("文件路径ID")
	private String fileId;//文件路径ID,hlg 10-18 添加

    private String mergeId;//合包id
    
    private String mergeState;//采购方案状态 1维护，2提交，3审核，4通过，5驳回
    
    private String isMat;//0物资，1非物资
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupplierWinId() {
		return supplierWinId;
	}

	public void setSupplierWinId(String supplierWinId) {
		this.supplierWinId = supplierWinId;
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

	public MergeRecordBean getMergeRecordBean() {
		return mergeRecordBean;
	}

	public void setMergeRecordBean(MergeRecordBean mergeRecordBean) {
		this.mergeRecordBean = mergeRecordBean;
	}

	public BatchmanageBean getBatchmanageBean() {
		return batchmanageBean;
	}

	public void setBatchmanageBean(BatchmanageBean batchmanageBean) {
		this.batchmanageBean = batchmanageBean;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getMergeState() {
		return mergeState;
	}

	public void setMergeState(String mergeState) {
		this.mergeState = mergeState;
	}

	public String getIsMat() {
		return isMat;
	}

	public void setIsMat(String isMat) {
		this.isMat = isMat;
	}

	
	
}