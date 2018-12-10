package com.yyc.oper.nobid.mat;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.yyc.oper.nobid.base.BasePageBean;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 非招标物资计划合包 参数   nonbid_matplan
 * */
@ApiModel(value="MergeRecorRequest",description="非招标物资计划合包 参数")
public class MergeRecorRequest extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("批次号")
	@NotNull
    private String batchNum;//批次号

    @ApiModelProperty("批次名称")
    private String batchName;//批次名称
    
	@ApiModelProperty("包号")
	@NotNull
    private String packageNum;//包号

    @ApiModelProperty("采购方案编号")
    @NotNull
    private String purchaseSchmeId;//采购方案编号

    @ApiModelProperty("采购方案名称")
    @NotNull
    private String purchaseSchmeName;//采购方案名称

    @ApiModelProperty("采购策略")
    @NotNull
    private String purchaseStrategy;//采购策略

    @ApiModelProperty("采购方式")
    @NotNull
    private String purchaseWay;//采购方式 1竞争性谈判采购，2询价采购，3单一来源采购

    @ApiModelProperty("资质要求")
    @NotNull
    private String qualification;//资质要求
    
    @ApiModelProperty("需求供应商数量")
    @NotNull
    private String demandSupplierNum;//需求供应商数量
    
    @ApiModelProperty("需求单位")
    private String demandCompany;//需求单位
    
    @ApiModelProperty("项目名称")
    @NotNull
    private String projectName;//项目名称

    @ApiModelProperty("项目编号")
    @NotNull
    private String projectNum;//项目编号

    private List<InvitationSupplierBean> listInvitationSupplierBean;//邀请推荐供应商
	
	private List<MatplanMatBean> listMatplanMatBean;//非招标-物资计划的物资表  集合

    @ApiModelProperty("物资计划id数组")
    @NotNull
    private String[] matplanIds;//物资计划id数组

    private String purchaseNum;//erp采购申请号
    
    private String fundSource;//资金来源
    
    private String approvalNum;//批准文号
    
    
	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
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

	public List<InvitationSupplierBean> getListInvitationSupplierBean() {
		return listInvitationSupplierBean;
	}

	public void setListInvitationSupplierBean(List<InvitationSupplierBean> listInvitationSupplierBean) {
		this.listInvitationSupplierBean = listInvitationSupplierBean;
	}

	public List<MatplanMatBean> getListMatplanMatBean() {
		return listMatplanMatBean;
	}

	public void setListMatplanMatBean(List<MatplanMatBean> listMatplanMatBean) {
		this.listMatplanMatBean = listMatplanMatBean;
	}

	public String[] getMatplanIds() {
		return matplanIds;
	}

	public void setMatplanIds(String[] matplanIds) {
		this.matplanIds = matplanIds;
	}

	public String getDemandCompany() {
		return demandCompany;
	}

	public void setDemandCompany(String demandCompany) {
		this.demandCompany = demandCompany;
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

	public String getApprovalNum() {
		return approvalNum;
	}

	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}
    
}