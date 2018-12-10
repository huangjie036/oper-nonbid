package com.yyc.oper.nobid.supplier;

import java.io.Serializable;
import java.util.List;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;

/**
 * 采购方案的推荐供应商和抽取供应商
 * */
@ApiModel(value="SupplierResponse",description="采购方案的推荐供应商和抽取供应商")
public class SupplierResponse extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private String invitationSupplierIds;//推荐供应商id字符串
	
    private String invitationSupplierNames;//推荐供应商name字符串
    
    private String extractSupplierIds;//抽取供应商id字符串
	
    private String extractSupplierNames;//抽取供应商name字符串

    private List<SupplierBean> listSupplierBean;//供应商集合

	public String getInvitationSupplierIds() {
		return invitationSupplierIds;
	}

	public void setInvitationSupplierIds(String invitationSupplierIds) {
		this.invitationSupplierIds = invitationSupplierIds;
	}

	public String getInvitationSupplierNames() {
		return invitationSupplierNames;
	}

	public void setInvitationSupplierNames(String invitationSupplierNames) {
		this.invitationSupplierNames = invitationSupplierNames;
	}

	public String getExtractSupplierIds() {
		return extractSupplierIds;
	}

	public void setExtractSupplierIds(String extractSupplierIds) {
		this.extractSupplierIds = extractSupplierIds;
	}

	public String getExtractSupplierNames() {
		return extractSupplierNames;
	}

	public void setExtractSupplierNames(String extractSupplierNames) {
		this.extractSupplierNames = extractSupplierNames;
	}

	public List<SupplierBean> getListSupplierBean() {
		return listSupplierBean;
	}

	public void setListSupplierBean(List<SupplierBean> listSupplierBean) {
		this.listSupplierBean = listSupplierBean;
	}

}