package com.yyc.oper.nobid.mat;

import java.io.Serializable;
import java.util.Date;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;

/**
 * 非招标物资计划表 导入新增 nonbid_matplan
 * */
@ApiModel(value="MatplanImportExcel",description="非招标物资计划表 导入新增")
public class MatplanImportExcel extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String serialNum;//序号

    private String batchNum;//批次号

    private String packageNum;//包号

    private String packageName;//包名

    private String demandCompany;//需求单位

    private String purchaseNum;//erp采购申请号

    private String fundSource;//资金来源

    private String matplanSource;//物资计划来源

    private String approvalNum;//批准文号

    private String projectName;//项目名称

    private String projectNum;//项目编号

    private String purchaseStrategy;//采购策略

    private String purchaseWay;//采购方式

    private String state;//状态 1待提交，2待审核，3待确认，4已确认，5已驳回

    private String remark;//备注

    private String createBy;//创建人

    private Date createTime;//创建时间

    private String opeBy;//操作人

    private Date opeTime;//操作时间

    private String del;//删除标记

    private String supplierId1;//推荐供应商编号1

    private String supplierPhone1;//推荐供应商电话1
    
    private String supplierId2;//推荐供应商编号2

    private String supplierPhone2;//推荐供应商电话2

    private String supplierId3;//推荐供应商编号3

    private String supplierPhone3;//推荐供应商电话3
    
    private String matId;//物料编码
    
    private String smallClassId;//小类id
    
    private String middleClassId;//中类id
    
    private String largeClassId;//大类id
    
    private String matNum;//物料数量

    private String matUnit;//物料单位

    private String estimateUnitPrice;//预估单价

    private String estimateTotalPrice;//预估总价

    private Date deliveryTime;//交货时间

    private String deliveryPlace;//交货地点

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
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

	public String getMatplanSource() {
		return matplanSource;
	}

	public void setMatplanSource(String matplanSource) {
		this.matplanSource = matplanSource;
	}

	public String getApprovalNum() {
		return approvalNum;
	}

	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	public String getPurchaseStrategy() {
		return purchaseStrategy;
	}

	public void setPurchaseStrategy(String purchaseStrategy) {
		this.purchaseStrategy = purchaseStrategy;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getSupplierId1() {
		return supplierId1;
	}

	public void setSupplierId1(String supplierId1) {
		this.supplierId1 = supplierId1;
	}

	public String getSupplierPhone1() {
		return supplierPhone1;
	}

	public void setSupplierPhone1(String supplierPhone1) {
		this.supplierPhone1 = supplierPhone1;
	}

	public String getSupplierId2() {
		return supplierId2;
	}

	public void setSupplierId2(String supplierId2) {
		this.supplierId2 = supplierId2;
	}

	public String getSupplierPhone2() {
		return supplierPhone2;
	}

	public void setSupplierPhone2(String supplierPhone2) {
		this.supplierPhone2 = supplierPhone2;
	}

	public String getSupplierId3() {
		return supplierId3;
	}

	public void setSupplierId3(String supplierId3) {
		this.supplierId3 = supplierId3;
	}

	public String getSupplierPhone3() {
		return supplierPhone3;
	}

	public void setSupplierPhone3(String supplierPhone3) {
		this.supplierPhone3 = supplierPhone3;
	}

	public String getMatId() {
		return matId;
	}

	public void setMatId(String matId) {
		this.matId = matId;
	}

	public String getSmallClassId() {
		return smallClassId;
	}

	public void setSmallClassId(String smallClassId) {
		this.smallClassId = smallClassId;
	}

	public String getMiddleClassId() {
		return middleClassId;
	}

	public void setMiddleClassId(String middleClassId) {
		this.middleClassId = middleClassId;
	}

	public String getLargeClassId() {
		return largeClassId;
	}

	public void setLargeClassId(String largeClassId) {
		this.largeClassId = largeClassId;
	}

	public String getMatNum() {
		return matNum;
	}

	public void setMatNum(String matNum) {
		this.matNum = matNum;
	}

	public String getMatUnit() {
		return matUnit;
	}

	public void setMatUnit(String matUnit) {
		this.matUnit = matUnit;
	}

	public String getEstimateUnitPrice() {
		return estimateUnitPrice;
	}

	public void setEstimateUnitPrice(String estimateUnitPrice) {
		this.estimateUnitPrice = estimateUnitPrice;
	}

	public String getEstimateTotalPrice() {
		return estimateTotalPrice;
	}

	public void setEstimateTotalPrice(String estimateTotalPrice) {
		this.estimateTotalPrice = estimateTotalPrice;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}

    
}