package com.yyc.oper.nobid.mat;

public enum MatplanBeanEnum {

	ONE1(1, "setSerialNum", "序号"), 
	ONE2(2, "setPackageNum", "包号"),
	ONE3(3, "setPackageName", "包名称"),
	ONE4(4, "setPurchaseWay", "采购方式"),
	ONE5(5, "setProjectNum", "项目编号"),
	ONE6(6, "setProjectName", "项目名称"),
	ONE7(7, "setPurchaseStrategy", "采购策略"),
	ONE8(8, "setBatchNum", "批次"),
	ONE9(9, "setDemandCompany", "需求单位"),
	ONE10(10, "setPurchaseNum", "采购申请号"),
	ONE11(11, "setFundSource", "资金来源（文号）"),
	ONE12(12, "setApprovalNum", "批准文号"),
	ONE13(13, "setSupplierId1", "推荐供应商1"),
	ONE14(14, "setSupplierPhone1", "联系方式1"),
	ONE15(15, "setSupplierId2", "推荐供应商2"),
	ONE16(16, "setSupplierPhone2", "联系方式2"),
	ONE17(17, "setSupplierId3", "推荐供应商3"),
	ONE18(18, "setSupplierPhone3", "联系方式3"),
	ONE19(19, "setMatId", "物料编码"),
	ONE25(20, "setMatNum", "数量"),
	ONE26(21, "setEstimateUnitPrice", "预计单价"),
	ONE28(22, "setDeliveryPlace", "交货地点"),
	ONE29(23, "setDeliveryTime", "交货时间");

	private int index;// id
	private String setMethod; //对应方法
	private String headerName; //表头

	private MatplanBeanEnum(int index, String setMethod, String headerName) {
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
