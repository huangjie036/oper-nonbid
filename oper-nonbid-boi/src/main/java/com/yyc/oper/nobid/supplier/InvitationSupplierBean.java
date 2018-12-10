package com.yyc.oper.nobid.supplier;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 邀请推荐供应商   nonbid_invitation_supplier
 *
 */
@ApiModel(value="InvitationSupplierBean",description="邀请推荐供应商")
public class InvitationSupplierBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("邀请推荐供应商id")
    private String invitationId;
	
	@ApiModelProperty("供应商名称")
	private String supplierName;//供应商名称

	@ApiModelProperty("供应商编号")
	@NotNull
    private String supplierId;//供应商编号

	@ApiModelProperty("计划编号")
    private String matplanId;//计划编号

	@ApiModelProperty("供应商电话")
	@NotNull
    private String supplierPhone;//供应商电话
	
	@ApiModelProperty("组织机构编码")
	private String orgId;//组织机构编码
	
	private String isMat;//0物资，1非物资
	
	private String[] matplanIds;//计划编号数组
	
	private SupplierBean supplierBean;//主数据管理-非招标-供应商 

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId == null ? null : invitationId.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getMatplanId() {
        return matplanId;
    }

    public void setMatplanId(String matplanId) {
        this.matplanId = matplanId == null ? null : matplanId.trim();
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone == null ? null : supplierPhone.trim();
    }

	public String[] getMatplanIds() {
		return matplanIds;
	}

	public void setMatplanIds(String[] matplanIds) {
		this.matplanIds = matplanIds;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public SupplierBean getSupplierBean() {
		return supplierBean;
	}

	public void setSupplierBean(SupplierBean supplierBean) {
		this.supplierBean = supplierBean;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getIsMat() {
		return isMat;
	}

	public void setIsMat(String isMat) {
		this.isMat = isMat;
	}

    
}