package com.yyc.oper.nobid.supplier;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * 供应商抽取结果表 nonbid_supplier_extract_result
 * */
public class SupplierExtractResultBean  extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String extractId;//抽取记录编号

    private String supplierWinId;

    private String mergeId;//合包编号

    private String opeDate;//抽取时间

    private String createDate;//创建时间

    private String createBy;//创建人

    private String opeBy;//操作人/抽取人

    private String states;//状态

    private String purchaseSchmeId;//采购方案编号

    private String purchaseSchmeName;//采购方案名称

    private Date purchaseSchmeCreateTime;

    private String del;//删除标记

    private String extractMethod;//001-导入抽取  002-随机收取  003-手工抽取

    private Integer extractNum;//供应商抽取数量

    private String orgId;//组织机构编码
    
    private List<SupplierBean> listSupplierBean;//供应商 集合

    public String getExtractId() {
        return extractId;
    }

    public void setExtractId(String extractId) {
        this.extractId = extractId == null ? null : extractId.trim();
    }

    public String getSupplierWinId() {
        return supplierWinId;
    }

    public void setSupplierWinId(String supplierWinId) {
        this.supplierWinId = supplierWinId == null ? null : supplierWinId.trim();
    }

    public String getMergeId() {
        return mergeId;
    }

    public void setMergeId(String mergeId) {
        this.mergeId = mergeId == null ? null : mergeId.trim();
    }

    public String getOpeDate() {
        return opeDate;
    }

    public void setOpeDate(String opeDate) {
        this.opeDate = opeDate == null ? null : opeDate.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getOpeBy() {
        return opeBy;
    }

    public void setOpeBy(String opeBy) {
        this.opeBy = opeBy == null ? null : opeBy.trim();
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states == null ? null : states.trim();
    }

    public String getPurchaseSchmeId() {
        return purchaseSchmeId;
    }

    public void setPurchaseSchmeId(String purchaseSchmeId) {
        this.purchaseSchmeId = purchaseSchmeId == null ? null : purchaseSchmeId.trim();
    }

    public String getPurchaseSchmeName() {
        return purchaseSchmeName;
    }

    public void setPurchaseSchmeName(String purchaseSchmeName) {
        this.purchaseSchmeName = purchaseSchmeName == null ? null : purchaseSchmeName.trim();
    }

    public Date getPurchaseSchmeCreateTime() {
        return purchaseSchmeCreateTime;
    }

    public void setPurchaseSchmeCreateTime(Date purchaseSchmeCreateTime) {
        this.purchaseSchmeCreateTime = purchaseSchmeCreateTime;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

    public String getExtractMethod() {
        return extractMethod;
    }

    public void setExtractMethod(String extractMethod) {
        this.extractMethod = extractMethod == null ? null : extractMethod.trim();
    }

    public Integer getExtractNum() {
        return extractNum;
    }

    public void setExtractNum(Integer extractNum) {
        this.extractNum = extractNum;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

	public List<SupplierBean> getListSupplierBean() {
		return listSupplierBean;
	}

	public void setListSupplierBean(List<SupplierBean> listSupplierBean) {
		this.listSupplierBean = listSupplierBean;
	}

    
}