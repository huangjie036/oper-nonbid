package com.yyc.oper.nobid.mat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yyc.oper.nobid.base.BasePageBean;
import com.yyc.oper.nobid.batch.BatchmanageBean;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;

/**
 *非招标非物资计划表 nonbid_non_matplan
 */
public class NonMatplanBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String nonMatplanId;//非物资计划id

    private String serviceType;//服务类型

    private String serialNum;//序号

    private String packageNum;//包号

    private String packageName;//包名

    private String businessDepartment;//业务主管部门

    private String demandCompany;//需求单位

    private String projectNum;//项目编号

    private String projectName;//项目名称
    
    private String purchaseStrategy;//采购策略

    private String purchaseWay;//采购方式

    private String batchNum;//批次号

    private String purchaseNum;//采购编号

    private String purchaseApplyNum;//采购申请编号

    private String projectPurchaseNum;//项目采购编号

    private String approvalNum;//批准文号

    private String nonMatplanSource;//非物资计划来源

    private String supplierSource;//供应商来源

    private String projectManagementResponsibilities;//项目管理专责

    private String technicalSpecification;//技术规范书

    private String fundSource;//资金来源

    private String projectType;//项目类型

    private String pricingWay;//定价方式

    private String planTotalPrice;//计划投入总价

    private String individualTaxPrice;//单项含税金额

    private String individualNoTaxPrice;//单项不含税金额

    private String individualTaxRate;//单项税率

    private String purchaseTaxRate;//采购方案税率

    private String highestPrice;//最高限价

    private String workTimeLimit;//工期

    private String xixingCompanyNum;//西星公司序号

    private String mergeId;//合包id

    private String isMerge;//是否合包

    private String mergeState;//合包状态

    private String remark;//备注

    private String createBy;//创建人

    private Date createTime;//创建时间

    private String opeBy;//操作人

    private Date opeTime;//操作时间

    private String state;//状态

    private String del;//删除标记

    private String orgId;//组织机构编码
    
    private BatchmanageBean batchmanageBean;
    
    private List<InvitationSupplierBean> listInvitationSupplierBean;
    
    private String[] nonMatplanIds;//非物资计划id数组

    public String getNonMatplanId() {
        return nonMatplanId;
    }

    public void setNonMatplanId(String nonMatplanId) {
        this.nonMatplanId = nonMatplanId == null ? null : nonMatplanId.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(String packageNum) {
        this.packageNum = packageNum == null ? null : packageNum.trim();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public String getBusinessDepartment() {
        return businessDepartment;
    }

    public void setBusinessDepartment(String businessDepartment) {
        this.businessDepartment = businessDepartment == null ? null : businessDepartment.trim();
    }

    public String getDemandCompany() {
        return demandCompany;
    }

    public void setDemandCompany(String demandCompany) {
        this.demandCompany = demandCompany == null ? null : demandCompany.trim();
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum == null ? null : projectNum.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getPurchaseWay() {
        return purchaseWay;
    }

    public void setPurchaseWay(String purchaseWay) {
        this.purchaseWay = purchaseWay == null ? null : purchaseWay.trim();
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    public String getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(String purchaseNum) {
        this.purchaseNum = purchaseNum == null ? null : purchaseNum.trim();
    }

    public String getPurchaseApplyNum() {
        return purchaseApplyNum;
    }

    public void setPurchaseApplyNum(String purchaseApplyNum) {
        this.purchaseApplyNum = purchaseApplyNum == null ? null : purchaseApplyNum.trim();
    }

    public String getProjectPurchaseNum() {
        return projectPurchaseNum;
    }

    public void setProjectPurchaseNum(String projectPurchaseNum) {
        this.projectPurchaseNum = projectPurchaseNum == null ? null : projectPurchaseNum.trim();
    }

    public String getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(String approvalNum) {
        this.approvalNum = approvalNum == null ? null : approvalNum.trim();
    }

    public String getNonMatplanSource() {
        return nonMatplanSource;
    }

    public void setNonMatplanSource(String nonMatplanSource) {
        this.nonMatplanSource = nonMatplanSource == null ? null : nonMatplanSource.trim();
    }

    public String getSupplierSource() {
        return supplierSource;
    }

    public void setSupplierSource(String supplierSource) {
        this.supplierSource = supplierSource == null ? null : supplierSource.trim();
    }

    public String getProjectManagementResponsibilities() {
        return projectManagementResponsibilities;
    }

    public void setProjectManagementResponsibilities(String projectManagementResponsibilities) {
        this.projectManagementResponsibilities = projectManagementResponsibilities == null ? null : projectManagementResponsibilities.trim();
    }

    public String getTechnicalSpecification() {
        return technicalSpecification;
    }

    public void setTechnicalSpecification(String technicalSpecification) {
        this.technicalSpecification = technicalSpecification == null ? null : technicalSpecification.trim();
    }

    public String getFundSource() {
        return fundSource;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource == null ? null : fundSource.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public String getPricingWay() {
        return pricingWay;
    }

    public void setPricingWay(String pricingWay) {
        this.pricingWay = pricingWay == null ? null : pricingWay.trim();
    }

    public String getPlanTotalPrice() {
        return planTotalPrice;
    }

    public void setPlanTotalPrice(String planTotalPrice) {
        this.planTotalPrice = planTotalPrice == null ? null : planTotalPrice.trim();
    }

    public String getIndividualTaxPrice() {
        return individualTaxPrice;
    }

    public void setIndividualTaxPrice(String individualTaxPrice) {
        this.individualTaxPrice = individualTaxPrice == null ? null : individualTaxPrice.trim();
    }

    public String getIndividualNoTaxPrice() {
        return individualNoTaxPrice;
    }

    public void setIndividualNoTaxPrice(String individualNoTaxPrice) {
        this.individualNoTaxPrice = individualNoTaxPrice == null ? null : individualNoTaxPrice.trim();
    }

    public String getIndividualTaxRate() {
        return individualTaxRate;
    }

    public void setIndividualTaxRate(String individualTaxRate) {
        this.individualTaxRate = individualTaxRate == null ? null : individualTaxRate.trim();
    }

    public String getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    public void setPurchaseTaxRate(String purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate == null ? null : purchaseTaxRate.trim();
    }

    public String getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(String highestPrice) {
        this.highestPrice = highestPrice == null ? null : highestPrice.trim();
    }

    public String getWorkTimeLimit() {
        return workTimeLimit;
    }

    public void setWorkTimeLimit(String workTimeLimit) {
        this.workTimeLimit = workTimeLimit == null ? null : workTimeLimit.trim();
    }

    public String getXixingCompanyNum() {
        return xixingCompanyNum;
    }

    public void setXixingCompanyNum(String xixingCompanyNum) {
        this.xixingCompanyNum = xixingCompanyNum == null ? null : xixingCompanyNum.trim();
    }

    public String getMergeId() {
        return mergeId;
    }

    public void setMergeId(String mergeId) {
        this.mergeId = mergeId == null ? null : mergeId.trim();
    }

    public String getIsMerge() {
        return isMerge;
    }

    public void setIsMerge(String isMerge) {
        this.isMerge = isMerge == null ? null : isMerge.trim();
    }

    public String getMergeState() {
        return mergeState;
    }

    public void setMergeState(String mergeState) {
        this.mergeState = mergeState == null ? null : mergeState.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
        this.opeBy = opeBy == null ? null : opeBy.trim();
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
        this.state = state == null ? null : state.trim();
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

	public List<InvitationSupplierBean> getListInvitationSupplierBean() {
		return listInvitationSupplierBean;
	}

	public void setListInvitationSupplierBean(List<InvitationSupplierBean> listInvitationSupplierBean) {
		this.listInvitationSupplierBean = listInvitationSupplierBean;
	}

	public BatchmanageBean getBatchmanageBean() {
		return batchmanageBean;
	}

	public void setBatchmanageBean(BatchmanageBean batchmanageBean) {
		this.batchmanageBean = batchmanageBean;
	}

	public String[] getNonMatplanIds() {
		return nonMatplanIds;
	}

	public void setNonMatplanIds(String[] nonMatplanIds) {
		this.nonMatplanIds = nonMatplanIds;
	}

	public String getPurchaseStrategy() {
		return purchaseStrategy;
	}

	public void setPurchaseStrategy(String purchaseStrategy) {
		this.purchaseStrategy = purchaseStrategy;
	}
    
}