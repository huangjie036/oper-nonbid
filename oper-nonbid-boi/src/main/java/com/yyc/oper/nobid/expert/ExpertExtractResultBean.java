package com.yyc.oper.nobid.expert;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yyc.oper.nobid.batch.BatchmanageBean;
import com.yyc.oper.nobid.mat.MatplanBean;

import io.swagger.annotations.ApiModel;

/**
 *  专家抽取结果表  nonbid_expert_extract_result
 * */
@ApiModel(value="ExpertExtractResultBean",description=" 专家抽取结果表 ")
public class ExpertExtractResultBean extends ExpertExtractRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
    private Date opeDate;//抽取时间

	@JsonIgnore
    private Date createDate;//创建时间

	@JsonIgnore
    private String createBy;//创建人

	@JsonIgnore
    private String opeBy;//操作人/抽取人

	@JsonIgnore
    private String state;//状态

	@JsonIgnore
    private String purchaseSchmeName;//采购方案名称

	@JsonIgnore
    private Date purchaseSchmeCreateTime;//采购方案创建时间

	@JsonIgnore
    private String del;//删除标记

    @JsonIgnore
    private String remark;//备注
    
    private MatplanBean matplanBean;//物资计划表
    
    private BatchmanageBean batchmanageBean;//批次管理
    
    private List<ExpertExtractResultDetailBean> listExpertExtractResultDetailBean;//专家抽取结果明细

    public Date getOpeDate() {
        return opeDate;
    }

    public void setOpeDate(Date opeDate) {
        this.opeDate = opeDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getOpeBy() {
        return opeBy;
    }

    public void setOpeBy(String opeBy) {
        this.opeBy = opeBy == null ? null : opeBy.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPurchaseSchmeName() {
        return purchaseSchmeName;
    }

    public void setPurchaseSchmeName(String purchaseSchmeName) {
        this.purchaseSchmeName = purchaseSchmeName == null ? null : purchaseSchmeName.trim();
    }

    public Date getPurchaseSchmeCreateTime() {
        return purchaseSchmeCreateTime;
    }

    public void setPurchaseSchmeCreateTime(Date purchaseSchmeCreateTime) {
        this.purchaseSchmeCreateTime = purchaseSchmeCreateTime;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

	public MatplanBean getMatplanBean() {
		return matplanBean;
	}

	public void setMatplanBean(MatplanBean matplanBean) {
		this.matplanBean = matplanBean;
	}

	public BatchmanageBean getBatchmanageBean() {
		return batchmanageBean;
	}

	public void setBatchmanageBean(BatchmanageBean batchmanageBean) {
		this.batchmanageBean = batchmanageBean;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ExpertExtractResultDetailBean> getListExpertExtractResultDetailBean() {
		return listExpertExtractResultDetailBean;
	}

	public void setListExpertExtractResultDetailBean(
			List<ExpertExtractResultDetailBean> listExpertExtractResultDetailBean) {
		this.listExpertExtractResultDetailBean = listExpertExtractResultDetailBean;
	}

}