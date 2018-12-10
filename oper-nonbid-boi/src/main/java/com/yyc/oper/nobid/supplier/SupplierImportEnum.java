package com.yyc.oper.nobid.supplier;

public enum SupplierImportEnum {
	ONE(1, "setSupplierName", "供应商名称"),
    TWO(2, "setAccountGroup", "账户组"),
	THREE(3, "setBusinessScope", "经营范围"),
	FOUR(4, "setRecommendPlant", "推荐单位"),
	FIVE(5, "setContact", "联系人"),
	SIX(6, "setContactMail", "电子邮箱"),
	SEVEN(7, "setContactMbNum", "联系人手机"),
	EIGHT(8, "setContactTelNum", "固定电话"),
	NINE(9, "setStockHolder", "股东"),
	TEN(10, "setLegalRepresentative", "法定代表人"),
	ELEVEN(11, "setLegalRiskNum", "法律风险数量"),
	TWELVE(12, "setRegisteredCapital", "注册资金"),
	THIRTEEN(13, "setRemark", "备注");


	private int index;// 供应商id
	private String setMethod; //对应方法
	private String headerName; //表头

	private SupplierImportEnum(int index, String setMethod, String headerName) {
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
