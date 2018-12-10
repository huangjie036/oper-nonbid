package com.yyc.oper.nobid.supplier;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

public class SupplierRosterBean extends BasePageBean implements Serializable {

	private static final long	serialVersionUID	= -5522301167929362090L;

	private String				supplierRosterId;

	private String				supplierId;

	private String				supplierName;

	private String				accountGroup;

	private String				rosterType;

	private String				createBy;

	private String				createTime;

	private String				del;



	public SupplierRosterBean() {
	}



	public String getSupplierRosterId() {
		return supplierRosterId;
	}



	public void setSupplierRosterId(String supplierRosterId) {
		this.supplierRosterId = supplierRosterId;
	}



	public String getSupplierId() {
		return supplierId;
	}



	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}



	public String getSupplierName() {
		return supplierName;
	}



	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}



	public String getAccountGroup() {
		return accountGroup;
	}



	public void setAccountGroup(String accountGroup) {
		this.accountGroup = accountGroup;
	}



	public String getRosterType() {
		return rosterType;
	}



	public void setRosterType(String rosterType) {
		this.rosterType = rosterType;
	}



	public String getCreateBy() {
		return createBy;
	}



	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	public String getDel() {
		return del;
	}



	public void setDel(String del) {
		this.del = del;
	}

}
