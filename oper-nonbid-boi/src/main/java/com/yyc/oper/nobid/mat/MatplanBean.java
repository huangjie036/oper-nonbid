package com.yyc.oper.nobid.mat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yyc.oper.nobid.base.BasePageBean;
import com.yyc.oper.nobid.batch.BatchmanageBean;
import com.yyc.oper.nobid.expert.ExpertBean;
import com.yyc.oper.nobid.merge.MergeRecordBean;
import com.yyc.oper.nobid.supplier.InvitationSupplierBean;
import com.yyc.oper.nobid.supplier.SupplierBean;

import io.swagger.annotations.ApiModel;

/**
 * 非招标物资计划表 nonbid_matplan
 * */
@ApiModel(value="MatplanBean",description="非招标物资计划表")
public class MatplanBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String matplanId;//物资计划id

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

    private String demandMat;//需求物料

    private String purchaseStrategy;//采购策略

    private String purchaseWay;//采购方式

    private String personLiable;//责任人

    private String phoneNum;//联系电话

    private String workTimeLimit;//工期

    private String state;//状态 1待提交，2待审核，3待确认，4已确认，5已驳回

    private String mergeState;//合包状态

    private String remark;//备注

    private String mergeId;//合包id

    private String isMerge;//是否合包

    private String createBy;//创建人

    private Date createTime;//创建时间

    private String opeBy;//操作人

    private Date opeTime;//操作时间

    private String del;//删除标记
    
    private String orgId;//组织机构编码
    
    private MergeRecordBean mergeRecordBean;//非招标合包记录

    private List<MatplanMatBean> listMatplanMatBean;//非招标-物资计划的物资表  集合
    
    private List<SupplierBean> listSupplierBean;//非招标供应商表  集合
    
    private List<ExpertBean> listExpertBean;//主数据-非招标-专家表  集合
    
    private BatchmanageBean batchmanageBean;//非招标批次管理
    
    private List<InvitationSupplierBean> listInvitationSupplierBean;//邀请推荐供应商  集合
    
    private String[] matplanIds;//物资计划id数组

	public String getMatplanId() {
		return matplanId;
	}

	public void setMatplanId(String matplanId) {
		this.matplanId = matplanId;
	}

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

	public String getDemandMat() {
		return demandMat;
	}

	public void setDemandMat(String demandMat) {
		this.demandMat = demandMat;
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

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getWorkTimeLimit() {
		return workTimeLimit;
	}

	public void setWorkTimeLimit(String workTimeLimit) {
		this.workTimeLimit = workTimeLimit;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMergeState() {
		return mergeState;
	}

	public void setMergeState(String mergeState) {
		this.mergeState = mergeState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getIsMerge() {
		return isMerge;
	}

	public void setIsMerge(String isMerge) {
		this.isMerge = isMerge;
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

	public MergeRecordBean getMergeRecordBean() {
		return mergeRecordBean;
	}

	public void setMergeRecordBean(MergeRecordBean mergeRecordBean) {
		this.mergeRecordBean = mergeRecordBean;
	}

	public List<MatplanMatBean> getListMatplanMatBean() {
		return listMatplanMatBean;
	}

	public void setListMatplanMatBean(List<MatplanMatBean> listMatplanMatBean) {
		this.listMatplanMatBean = listMatplanMatBean;
	}

	public List<SupplierBean> getListSupplierBean() {
		return listSupplierBean;
	}

	public void setListSupplierBean(List<SupplierBean> listSupplierBean) {
		this.listSupplierBean = listSupplierBean;
	}

	public List<ExpertBean> getListExpertBean() {
		return listExpertBean;
	}

	public void setListExpertBean(List<ExpertBean> listExpertBean) {
		this.listExpertBean = listExpertBean;
	}

	public BatchmanageBean getBatchmanageBean() {
		return batchmanageBean;
	}

	public void setBatchmanageBean(BatchmanageBean batchmanageBean) {
		this.batchmanageBean = batchmanageBean;
	}

	public List<InvitationSupplierBean> getListInvitationSupplierBean() {
		return listInvitationSupplierBean;
	}

	public void setListInvitationSupplierBean(List<InvitationSupplierBean> listInvitationSupplierBean) {
		this.listInvitationSupplierBean = listInvitationSupplierBean;
	}

	public String[] getMatplanIds() {
		return matplanIds;
	}

	public void setMatplanIds(String[] matplanIds) {
		this.matplanIds = matplanIds;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	
    
}