package com.yyc.oper.nobid.supplier;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * 非招标供应商表  mm_erp_supplier
 * */
public class ErpSupplierBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id; //供应商工厂关系id
	
	private String plantId; //工厂id
	
    private String supplierId;

    private String supplierName;

    private String supplierCity;//所在城市

    private String supplierZipCode;//供应商邮编

    private String supplierEmailCode;//供应商邮箱编码

    private String supplierStreet;//供应商街道

    private String supplierTelNum;//供应商电话

    private String supplierFaxNum;//供应商传真号
    
    private String del;//删除标记

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity == null ? null : supplierCity.trim();
    }

    public String getSupplierZipCode() {
        return supplierZipCode;
    }

    public void setSupplierZipCode(String supplierZipCode) {
        this.supplierZipCode = supplierZipCode == null ? null : supplierZipCode.trim();
    }

    public String getSupplierEmailCode() {
        return supplierEmailCode;
    }

    public void setSupplierEmailCode(String supplierEmailCode) {
        this.supplierEmailCode = supplierEmailCode == null ? null : supplierEmailCode.trim();
    }

    public String getSupplierStreet() {
        return supplierStreet;
    }

    public void setSupplierStreet(String supplierStreet) {
        this.supplierStreet = supplierStreet == null ? null : supplierStreet.trim();
    }

    public String getSupplierTelNum() {
        return supplierTelNum;
    }

    public void setSupplierTelNum(String supplierTelNum) {
        this.supplierTelNum = supplierTelNum == null ? null : supplierTelNum.trim();
    }

    public String getSupplierFaxNum() {
        return supplierFaxNum;
    }

    public void setSupplierFaxNum(String supplierFaxNum) {
        this.supplierFaxNum = supplierFaxNum == null ? null : supplierFaxNum.trim();
    }
    
    public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	
	
}