package com.yyc.oper.nobid.supplier;

import java.io.Serializable;
import java.util.List;

import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.mat.MatplanMatBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SupplierWinDetailBean",description="采购结果详情")
public class SupplierWinDetailBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("采购方案编号")
	private String purchaseSchmeId;//采购方案编号
	
	@ApiModelProperty("采购方案名称")
	private String purchaseSchmeName;//采购方案名称
	
	@ApiModelProperty("合包编号")
	private String bagNum;//合包编号
	
	@ApiModelProperty("合包编号")
	private String packageNum;//合包编号
	
	@ApiModelProperty("批次编码")
	private String batchNum;//批次编码
	
	@ApiModelProperty("采购方式")
	private String purchaseWay;//采购方式
	
	@ApiModelProperty("采购策略")
	private String purchaseStrategy;//采购策略
	
	@ApiModelProperty("资质要求")
	private String qualification;//资质要求
	
	@ApiModelProperty("需求供应商数量")
	private String demandSupplierNum;//需求供应商数量
	
	@ApiModelProperty("推荐供应商组编码")
	private String recommendSupplierIds;//推荐供应商组编码
	
	@ApiModelProperty("抽取供应商组编码")
	private String supplierWinIds;//抽取供应商组编码
	
	@ApiModelProperty("中标供应商编码")
	private String supplierWinId;//中标供应商编码
	
	@ApiModelProperty("抽取专家组编码")
	private String expertWinIds;//抽取专家组编码
	
	@ApiModelProperty("抽取专家组编码")
	private String expertCode;//抽取专家组编码
	
	@ApiModelProperty("中标供应商名称")
	private String supplierName;//中标供应商名称
	
	@ApiModelProperty("采购结果")
	private String purchaseResult;//采购结果
	
	@ApiModelProperty("中标金额")
	private String money;//中标金额
	
	@ApiModelProperty("备注")
	private String remark;//备注
	
	@ApiModelProperty("物料组id")
	private String matIds;//物料组id
	
	@ApiModelProperty("物料信息集合")
	private List<MatplanMatBean> matlist;//物料信息集合
	
	@ApiModelProperty("专家信息集合")
	private List<ExpertBean> expertlist;//专家信息集合
	
	@ApiModelProperty("供应商信息集合")
	private List<SupplierBean> supplierlist;//供应商信息集合

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

	public String getBagNum() {
		return bagNum;
	}

	public void setBagNum(String bagNum) {
		this.bagNum = bagNum;
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

	public String getDemandSupplierNum() {
		return demandSupplierNum;
	}

	public void setDemandSupplierNum(String demandSupplierNum) {
		this.demandSupplierNum = demandSupplierNum;
	}



	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPurchaseResult() {
		return purchaseResult;
	}

	public void setPurchaseResult(String purchaseResult) {
		this.purchaseResult = purchaseResult;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	public List<MatplanMatBean> getMatlist() {
		return matlist;
	}

	public void setMatlist(List<MatplanMatBean> matlist) {
		this.matlist = matlist;
	}

	public List<ExpertBean> getExpertlist() {
		return expertlist;
	}

	public void setExpertlist(List<ExpertBean> expertlist) {
		this.expertlist = expertlist;
	}

	public List<SupplierBean> getSupplierlist() {
		return supplierlist;
	}

	public void setSupplierlist(List<SupplierBean> supplierlist) {
		this.supplierlist = supplierlist;
	}

	public String getRecommendSupplierIds() {
		return recommendSupplierIds;
	}

	public void setRecommendSupplierIds(String recommendSupplierIds) {
		this.recommendSupplierIds = recommendSupplierIds;
	}

	public String getSupplierWinIds() {
		return supplierWinIds;
	}

	public void setSupplierWinIds(String supplierWinIds) {
		this.supplierWinIds = supplierWinIds;
	}

	public String getExpertWinIds() {
		return expertWinIds;
	}

	public void setExpertWinIds(String expertWinIds) {
		this.expertWinIds = expertWinIds;
	}

	public String getMatIds() {
		return matIds;
	}

	public void setMatIds(String matIds) {
		this.matIds = matIds;
	}

	public String getExpertCode() {
		return expertCode;
	}

	public void setExpertCode(String expertCode) {
		this.expertCode = expertCode;
	}

	public String getSupplierWinId() {
		return supplierWinId;
	}

	public void setSupplierWinId(String supplierWinId) {
		this.supplierWinId = supplierWinId;
	}

	public String getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}
	
	
	

	
	
}
