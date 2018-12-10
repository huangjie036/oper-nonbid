package com.yyc.oper.nobid.supplier;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * 非招标中标供应商表  nonbid_supplier_win
 * */
@ApiModel(value="SupplierWinRequest",description="非招标中标供应商表 参数")
public class SupplierWinRequest extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("中标结果id")
	private String id;//中标结果id
	
	@ApiModelProperty("合包记录id,用于采购结果详情的查询条件")
	private String mergeId;//合包记录id
	
	@ApiModelProperty("中标供应商id")
    private String supplierWinId;//中标供应商id

	@ApiModelProperty("采购结果")
    private String purchaseResult;//采购结果

	@ApiModelProperty("中标备注")
    private String remark;//中标备注

	@ApiModelProperty("中标金额")
    private String money;//中标金额
	
	@ApiParam("文件路径ID")
	private String fileId;//文件路径ID,hlg 10-18 添加

	public String getSupplierWinId() {
		return supplierWinId;
	}

	public void setSupplierWinId(String supplierWinId) {
		this.supplierWinId = supplierWinId;
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
	
	
    
}