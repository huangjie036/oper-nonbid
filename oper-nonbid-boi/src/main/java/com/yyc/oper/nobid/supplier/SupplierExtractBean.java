package com.yyc.oper.nobid.supplier;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * 供应商抽取结果表详情  nonbid_supplier_extract
 * */
public class SupplierExtractBean extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;//供应商抽取id

    private String extractId;//抽取id

    private String supplierId;//供应商id

    private String supplierWinId;

    private String extractRecordCode;//抽取记录编码

    private String createBy;//创建人

    private String createTime;//创建时间

    private String opeBy;//操作人

    private String opeTime;//操作时间

    private String orgId;//组织机构编码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExtractId() {
        return extractId;
    }

    public void setExtractId(String extractId) {
        this.extractId = extractId == null ? null : extractId.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getSupplierWinId() {
        return supplierWinId;
    }

    public void setSupplierWinId(String supplierWinId) {
        this.supplierWinId = supplierWinId == null ? null : supplierWinId.trim();
    }

    public String getExtractRecordCode() {
        return extractRecordCode;
    }

    public void setExtractRecordCode(String extractRecordCode) {
        this.extractRecordCode = extractRecordCode == null ? null : extractRecordCode.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getOpeBy() {
        return opeBy;
    }

    public void setOpeBy(String opeBy) {
        this.opeBy = opeBy == null ? null : opeBy.trim();
    }

    public String getOpeTime() {
        return opeTime;
    }

    public void setOpeTime(String opeTime) {
        this.opeTime = opeTime == null ? null : opeTime.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }
}