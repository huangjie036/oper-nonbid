
package com.yyc.oper.nobid.enumbean;

public enum ImportExpertExtractBeanEnum {
	
	ONE(1, "setSupplierId", "专家编码"), 
	TWO(2, "setSupplierName", "专家名称");

	
	private int index;// id
	private String setMethod; //对应方法
	private String headerName; //表头
	
	private ImportExpertExtractBeanEnum(int index, String setMethod, String headerName) {
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