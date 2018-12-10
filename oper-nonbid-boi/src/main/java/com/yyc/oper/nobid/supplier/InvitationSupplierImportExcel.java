package com.yyc.oper.nobid.supplier;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;

/**
 * 邀请推荐供应商   nonbid_invitation_supplier
 *
 */
@ApiModel(value="InvitationSupplierImportExcel",description="邀请推荐供应商")
public class InvitationSupplierImportExcel extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String invitationId;
	
	private String matplanId;//计划编号
	
	private String supplierId1;//推荐供应商名称1

    private String supplierPhone1;//推荐供应商电话1
    
    private String supplierId2;//推荐供应商名称2

    private String supplierPhone2;//推荐供应商电话2

    private String supplierId3;//推荐供应商名称3

    private String supplierPhone3;//推荐供应商电话3

	public String getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}

	public String getMatplanId() {
		return matplanId;
	}

	public void setMatplanId(String matplanId) {
		this.matplanId = matplanId;
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
    
}