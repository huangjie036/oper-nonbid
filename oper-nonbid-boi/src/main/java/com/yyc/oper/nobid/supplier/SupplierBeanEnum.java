package com.yyc.oper.nobid.supplier;

public enum SupplierBeanEnum {

	ONE(1, "setSupplierName", "供应商名称");


	private int index;// 供应商id
	private String setMethod; //对应方法
	private String headerName; //表头

	private SupplierBeanEnum(int index, String setMethod, String headerName) {
		this.index = index;
		this.setMethod = setMethod;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

}
