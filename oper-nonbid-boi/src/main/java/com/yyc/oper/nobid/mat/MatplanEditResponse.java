package com.yyc.oper.nobid.mat;

import java.io.Serializable;
import java.util.List;

import com.yyc.oper.nobid.supplier.InvitationSupplierResponse;

import io.swagger.annotations.ApiModel;

/**
 * 采购计划维护 编辑
 * */
@ApiModel(value="MatplanEditResponse",description="采购计划维护 编辑")
public class MatplanEditResponse extends MatplanResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String projectNum;//项目编号
	
	private String batchName;//批次名称
	
	private List<MatplanMatBean> listMatplanMatBean;//非招标-物资计划的物资表  集合
	
	private List<InvitationSupplierResponse> listInvitationSupplierResponse;//邀请推荐供应商  集合
	
	//private List<ErpSupplierBean> listErpSupplierBean;//非招标供应商表  集合
	

	public List<MatplanMatBean> getListMatplanMatBean() {
		return listMatplanMatBean;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public void setListMatplanMatBean(List<MatplanMatBean> listMatplanMatBean) {
		this.listMatplanMatBean = listMatplanMatBean;
	}

	public List<InvitationSupplierResponse> getListInvitationSupplierResponse() {
		return listInvitationSupplierResponse;
	}

	public void setListInvitationSupplierResponse(List<InvitationSupplierResponse> listInvitationSupplierResponse) {
		this.listInvitationSupplierResponse = listInvitationSupplierResponse;
	}


	
}