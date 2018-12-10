package com.yyc.oper.nobid.supplier;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;

/**
 * 邀请推荐供应商返回类
 *
 */
@ApiModel(value="InvitationSupplierResponse",description="邀请推荐供应商返回类")
public class InvitationSupplierResponse extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

    private String invitationId;

    private String supplierId;//供应商编号

    private String matplanId;//计划编号

    private String supplierPhone;//供应商电话
	
	private String supplierName;//供应商名字

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId == null ? null : invitationId.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getMatplanId() {
        return matplanId;
    }

    public void setMatplanId(String matplanId) {
        this.matplanId = matplanId == null ? null : matplanId.trim();
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone == null ? null : supplierPhone.trim();
    }

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

    
}