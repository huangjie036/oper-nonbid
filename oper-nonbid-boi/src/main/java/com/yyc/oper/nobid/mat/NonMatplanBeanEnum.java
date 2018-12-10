package com.yyc.oper.nobid.mat;

public enum NonMatplanBeanEnum {

	ONE1(1, "setServiceType", "服务类型"), 
	ONE2(2, "setSerialNum", "序号"),
	ONE3(3, "setPackageNum", "包号"),
	ONE4(4, "setPackageName", "包名"),
	ONE5(5, "setBusinessDepartment", "业务主管部门"),
	ONE6(6, "setDemandCompany", "需求单位"),
	ONE7(7, "setProjectNum", "项目编号"),
	ONE8(8, "setProjectName", "项目名称"),
	ONE9(9, "setPurchaseStrategy", "采购策略"),
	ONE10(10, "setPurchaseWay", "采购方式"),
	ONE11(11, "setBatchNum", "批次号"),
	ONE12(12, "setPurchaseNum", "采购编号"),
	ONE13(13, "setPurchaseApplyNum", "采购申请编号"),
	ONE14(14, "setProjectPurchaseNum", "项目采购编号"),
	ONE15(15, "setApprovalNum", "批准文号"),
	ONE16(16, "setSupplierSource", "供应商来源"),
	ONE17(17, "setProjectManagementResponsibilities", "项目管理专责"),
	ONE18(18, "setTechnicalSpecification", "技术规范书"),
	ONE19(19, "setFundSource", "资金来源"),
	ONE20(20, "setProjectType", "项目类型"),
	ONE21(21, "setPricingWay", "定价方式"),
	ONE22(22, "setPlanTotalPrice", "计划总投资金额"),
	ONE23(23, "setIndividualTaxPrice", "单项含税金额"),
	ONE24(24, "setIndividualNoTaxPrice", "单项不含税金额"),
	ONE25(25, "setIndividualTaxRate", "单项税率"),
	ONE26(26, "setPurchaseTaxRate", "采购方案税率"),
	ONE27(27, "setHighestPrice", "最高限价"),
	ONE28(28, "setWorkTimeLimit", "工期"),
	ONE29(29, "setXixingCompanyNum", "西星公司序号"),
	ONE30(30, "setSupplierId1", "邀请供应商1"),
	ONE31(31, "setSupplierPhone1", "邀请供应商1电话"),
	ONE32(32, "setSupplierId2", "邀请供应商2"),
	ONE33(33, "setSupplierPhone2", "邀请供应商2电话"),
	ONE34(34, "setSupplierId3", "邀请供应商3"),
	ONE35(35, "setSupplierPhone3", "邀请供应商3电话");

	private int index;// id
	private String setMethod; //对应方法
	private String headerName; //表头

	private NonMatplanBeanEnum(int index, String setMethod, String headerName) {
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
