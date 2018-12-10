package com.yyc.oper.nobid.mat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.yyc.oper.nobid.base.BasePageBean;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;
import com.yyc.oper.nobid.supplier.InvitationSupplierResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 非招标非物资计划表 参数   nonbid_non_matplan
 * */
@ApiModel(value="NonMatplanRequest",description="非招标非物资计划表 参数")
public class NonMatplanRequest extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("非物资计划id")
	private String nonMatplanId;//非物资计划id
	
	@ApiModelProperty("服务类型")
	@NotNull
    private String serviceType;//服务类型

	@ApiModelProperty("序号")
	@NotNull
    private String serialNum;//序号

	@ApiModelProperty("包号")
	@NotNull
    private String packageNum;//包号

	@ApiModelProperty("包名")
	@NotNull
    private String packageName;//包名

	@ApiModelProperty("业务主管部门")
	@NotNull
    private String businessDepartment;//业务主管部门

	@ApiModelProperty("需求单位")
	@NotNull
    private String demandCompany;//需求单位

	@ApiModelProperty("项目编号")
	@NotNull
    private String projectNum;//项目编号

	@ApiModelProperty("项目名称")
	@NotNull
    private String projectName;//项目名称

	@ApiModelProperty("采购方式")
	@NotNull
    private String purchaseWay;//采购方式

	@ApiModelProperty("批次号")
	@NotNull
    private String batchNum;//批次号

	@ApiModelProperty("采购编号")
	@NotNull
    private String purchaseNum;//采购编号

	@ApiModelProperty("采购申请编号")
	@NotNull
    private String purchaseApplyNum;//采购申请编号

	@ApiModelProperty("项目采购编号")
	@NotNull
    private String projectPurchaseNum;//项目采购编号

	@ApiModelProperty("批准文号")
	@NotNull
    private String approvalNum;//批准文号

	@ApiModelProperty("供应商来源")
	@NotNull
    private String supplierSource;//供应商来源

	@ApiModelProperty("项目管理专责")
	@NotNull
    private String projectManagementResponsibilities;//项目管理专责

	@ApiModelProperty("技术规范书")
	@NotNull
    private String technicalSpecification;//技术规范书

	@ApiModelProperty("资金来源")
	@NotNull
    private String fundSource;//资金来源

	@ApiModelProperty("项目类型")
	@NotNull
    private String projectType;//项目类型

	@ApiModelProperty("定价方式")
	@NotNull
    private String pricingWay;//定价方式

	@ApiModelProperty("计划投入总价")
	@NotNull
    private String planTotalPrice;//计划投入总价

	@ApiModelProperty("单项含税金额")
	@NotNull
    private String individualTaxPrice;//单项含税金额

	@ApiModelProperty("单项不含税金额")
	@NotNull
    private String individualNoTaxPrice;//单项不含税金额

	@ApiModelProperty("单项税率")
	@NotNull
    private String individualTaxRate;//单项税率

	@ApiModelProperty("采购方案税率")
	@NotNull
    private String purchaseTaxRate;//采购方案税率

	@ApiModelProperty("最高限价")
	@NotNull
    private String highestPrice;//最高限价

	@ApiModelProperty("工期")
	@NotNull
    private String workTimeLimit;//工期

	@ApiModelProperty("西星公司序号")
	@NotNull
    private String xixingCompanyNum;//西星公司序号
	
	@ApiModelProperty("非物资计划来源")
	private String nonMatplanSource;//非物资计划来源
	
	@ApiModelProperty("状态")
	private String state;//状态
	
	@ApiModelProperty("操作人")
	private String opeBy;//操作人

	@ApiModelProperty("操作时间")
    private Date opeTime;//操作时间

	@ApiModelProperty("返回邀请推荐供应商")
    private List<InvitationSupplierResponse> listInvitationSupplierResponse;
	
	@ApiModelProperty("邀请推荐供应商")
	@NotNull
    private List<InvitationSupplierBean> listInvitationSupplierBean;
	
	@ApiModelProperty("批次名称")
	private String batchName;// 批次名称
	
	
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
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

	public String getBusinessDepartment() {
		return businessDepartment;
	}

	public void setBusinessDepartment(String businessDepartment) {
		this.businessDepartment = businessDepartment;
	}

	public String getDemandCompany() {
		return demandCompany;
	}

	public void setDemandCompany(String demandCompany) {
		this.demandCompany = demandCompany;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
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

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public String getPurchaseApplyNum() {
		return purchaseApplyNum;
	}

	public void setPurchaseApplyNum(String purchaseApplyNum) {
		this.purchaseApplyNum = purchaseApplyNum;
	}

	public String getProjectPurchaseNum() {
		return projectPurchaseNum;
	}

	public void setProjectPurchaseNum(String projectPurchaseNum) {
		this.projectPurchaseNum = projectPurchaseNum;
	}

	public String getApprovalNum() {
		return approvalNum;
	}

	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}

	public String getSupplierSource() {
		return supplierSource;
	}

	public void setSupplierSource(String supplierSource) {
		this.supplierSource = supplierSource;
	}

	public String getProjectManagementResponsibilities() {
		return projectManagementResponsibilities;
	}

	public void setProjectManagementResponsibilities(String projectManagementResponsibilities) {
		this.projectManagementResponsibilities = projectManagementResponsibilities;
	}

	public String getTechnicalSpecification() {
		return technicalSpecification;
	}

	public void setTechnicalSpecification(String technicalSpecification) {
		this.technicalSpecification = technicalSpecification;
	}

	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getPricingWay() {
		return pricingWay;
	}

	public void setPricingWay(String pricingWay) {
		this.pricingWay = pricingWay;
	}

	public String getPlanTotalPrice() {
		return planTotalPrice;
	}

	public void setPlanTotalPrice(String planTotalPrice) {
		this.planTotalPrice = planTotalPrice;
	}

	public String getIndividualTaxPrice() {
		return individualTaxPrice;
	}

	public void setIndividualTaxPrice(String individualTaxPrice) {
		this.individualTaxPrice = individualTaxPrice;
	}

	public String getIndividualNoTaxPrice() {
		return individualNoTaxPrice;
	}

	public void setIndividualNoTaxPrice(String individualNoTaxPrice) {
		this.individualNoTaxPrice = individualNoTaxPrice;
	}

	public String getIndividualTaxRate() {
		return individualTaxRate;
	}

	public void setIndividualTaxRate(String individualTaxRate) {
		this.individualTaxRate = individualTaxRate;
	}

	public String getPurchaseTaxRate() {
		return purchaseTaxRate;
	}

	public void setPurchaseTaxRate(String purchaseTaxRate) {
		this.purchaseTaxRate = purchaseTaxRate;
	}

	public String getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(String highestPrice) {
		this.highestPrice = highestPrice;
	}

	public String getWorkTimeLimit() {
		return workTimeLimit;
	}

	public void setWorkTimeLimit(String workTimeLimit) {
		this.workTimeLimit = workTimeLimit;
	}

	public String getXixingCompanyNum() {
		return xixingCompanyNum;
	}

	public void setXixingCompanyNum(String xixingCompanyNum) {
		this.xixingCompanyNum = xixingCompanyNum;
	}

	public List<InvitationSupplierResponse> getListInvitationSupplierResponse() {
		return listInvitationSupplierResponse;
	}

	public void setListInvitationSupplierResponse(List<InvitationSupplierResponse> listInvitationSupplierResponse) {
		this.listInvitationSupplierResponse = listInvitationSupplierResponse;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public List<InvitationSupplierBean> getListInvitationSupplierBean() {
		return listInvitationSupplierBean;
	}

	public void setListInvitationSupplierBean(List<InvitationSupplierBean> listInvitationSupplierBean) {
		this.listInvitationSupplierBean = listInvitationSupplierBean;
	}

	public String getNonMatplanId() {
		return nonMatplanId;
	}

	public void setNonMatplanId(String nonMatplanId) {
		this.nonMatplanId = nonMatplanId;
	}

	public String getNonMatplanSource() {
		return nonMatplanSource;
	}

	public void setNonMatplanSource(String nonMatplanSource) {
		this.nonMatplanSource = nonMatplanSource;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

}