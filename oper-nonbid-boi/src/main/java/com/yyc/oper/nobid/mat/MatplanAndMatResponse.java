package com.yyc.oper.nobid.mat;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;

/**
 * 合包和汇总列表
 * */
@ApiModel(value="MatplanAndMatResponse",description="合包和汇总列表")
public class MatplanAndMatResponse extends MatplanResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<MatplanMatBean> listMatplanMatBean;//非招标-物资计划的物资表  集合
	
	//private List<InvitationSupplierBean> listInvitationSupplierBean;//邀请推荐供应商  集合
	
	private String InvitationSupplier1;//邀请推荐供应商1
	
	private String InvitationSupplier2;//邀请推荐供应商2
	
	private String InvitationSupplier3;//邀请推荐供应商3
	
	private String InvitationSupplier4;//邀请推荐供应商4
	
	private String InvitationSupplier5;//邀请推荐供应商5

	public List<MatplanMatBean> getListMatplanMatBean() {
		return listMatplanMatBean;
	}

	public void setListMatplanMatBean(List<MatplanMatBean> listMatplanMatBean) {
		this.listMatplanMatBean = listMatplanMatBean;
	}

	public String getInvitationSupplier1() {
		return InvitationSupplier1;
	}

	public void setInvitationSupplier1(String invitationSupplier1) {
		InvitationSupplier1 = invitationSupplier1;
	}

	public String getInvitationSupplier2() {
		return InvitationSupplier2;
	}

	public void setInvitationSupplier2(String invitationSupplier2) {
		InvitationSupplier2 = invitationSupplier2;
	}

	public String getInvitationSupplier3() {
		return InvitationSupplier3;
	}

	public void setInvitationSupplier3(String invitationSupplier3) {
		InvitationSupplier3 = invitationSupplier3;
	}

	public String getInvitationSupplier4() {
		return InvitationSupplier4;
	}

	public void setInvitationSupplier4(String invitationSupplier4) {
		InvitationSupplier4 = invitationSupplier4;
	}

	public String getInvitationSupplier5() {
		return InvitationSupplier5;
	}

	public void setInvitationSupplier5(String invitationSupplier5) {
		InvitationSupplier5 = invitationSupplier5;
	}
	
}