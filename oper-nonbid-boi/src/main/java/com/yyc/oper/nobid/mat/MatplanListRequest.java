package com.yyc.oper.nobid.mat;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 非招标物资计划列表查询条件 参数   nonbid_matplan
 * */
@ApiModel(value="MatplanListRequest",description="非招标物资计划列表查询条件 参数")
public class MatplanListRequest extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("批次号")
    private String batchNum;//批次号

	@ApiModelProperty("项目名称")
    private String projectName;//项目名称

    @ApiModelProperty("采购方式")
    private String purchaseWay;//采购方式 1竞争性谈判采购，2询价采购，3单一来源采购

	@ApiModelProperty("需求单位")
    private String demandCompany;//需求单位

	@ApiModelProperty("资金来源")
    private String fundSource;//资金来源

    @ApiModelProperty("单据来源")
    private String matplanSource;//物资计划来源

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPurchaseWay() {
		return purchaseWay;
	}

	public void setPurchaseWay(String purchaseWay) {
		this.purchaseWay = purchaseWay;
	}

	public String getDemandCompany() {
		return demandCompany;
	}

	public void setDemandCompany(String demandCompany) {
		this.demandCompany = demandCompany;
	}

	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}

	public String getMatplanSource() {
		return matplanSource;
	}

	public void setMatplanSource(String matplanSource) {
		this.matplanSource = matplanSource;
	}


}