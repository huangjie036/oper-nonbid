package com.yyc.oper.nobid.supplier;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 非招标供应商评价 nonbid_supplier_eval
 * */
@ApiModel(value="SupplierEvalBean",description="供应商评价")
public class SupplierEvalBean extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("供应商评价id")
    private String supplierEvalId;//供应商评价id
    
	@ApiModelProperty("供应商评价id数组")
    private String[] supplierEvalIds;//供应商评价id数组

	@ApiModelProperty("供应商id,页面查询参数")
    private String supplierId;//供应商id
	
	@ApiModelProperty("抽取记录")
    private String extractRecord;//抽取记录

	@ApiModelProperty("评价环节")
    private String evalPhase;//评价环节
	
	@ApiModelProperty("项目编码,页面查询参数")
    private String projectId;//项目编码

	@ApiModelProperty("评价项目名称")
    private String evalProjectName;//评价项目名称

	@ApiModelProperty("评价时间")
    private Date evalTime;//评价时间

	@ApiModelProperty("供应商名称,页面查询参数")
    private String supplierName;//供应商名称
    
	@ApiModelProperty("项目名称,页面查询参数")
    private String projectName;//项目名称

	@ApiModelProperty("备注")
    private String remark;//备注

	@ApiModelProperty("批次号")
    private String batchNum;//批次号

	@ApiModelProperty("序号")
    private String serialNum;//序号

	@ApiModelProperty("抽取项目")
    private String extractProject;//抽取项目

	@ApiModelProperty("创建人")
    private String createBy;//创建人

	@ApiModelProperty("创建时间")
    private Date createTime;//创建时间

	@ApiModelProperty("操作人")
    private String opeBy;//操作人

	@ApiModelProperty("操作时间")
    private Date opeTime;//操作时间

	@ApiModelProperty("删除标记")
    private String del;//删除标记
    
	@ApiModelProperty("问题环节id")
    private String questionLinkId;//问题环节id
    
	@ApiModelProperty("评价项id")
    private String supplierEvalTypeId;//评价项id
	
	@ApiModelProperty("联系人,页面查询参数")
    private String contact;//联系人
	
	@ApiModelProperty("合作范围编码,页面查询参数")
    private String colatitudeId;//合作范围编码
	
	@ApiModelProperty("问题发生时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date questionHappenTime;//问题发生时间

    public String getSupplierEvalId() {
        return supplierEvalId;
    }

    public void setSupplierEvalId(String supplierEvalId) {
        this.supplierEvalId = supplierEvalId == null ? null : supplierEvalId.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getExtractRecord() {
        return extractRecord;
    }

    public void setExtractRecord(String extractRecord) {
        this.extractRecord = extractRecord == null ? null : extractRecord.trim();
    }

    public String getEvalPhase() {
        return evalPhase;
    }

    public void setEvalPhase(String evalPhase) {
        this.evalPhase = evalPhase == null ? null : evalPhase.trim();
    }



    public Date getEvalTime() {
        return evalTime;
    }

    public void setEvalTime(Date evalTime) {
        this.evalTime = evalTime == null ? null : evalTime;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getExtractProject() {
        return extractProject;
    }

    public void setExtractProject(String extractProject) {
        this.extractProject = extractProject == null ? null : extractProject.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : createTime;
    }

    public String getOpeBy() {
        return opeBy;
    }

    public void setOpeBy(String opeBy) {
        this.opeBy = opeBy == null ? null : opeBy.trim();
    }

    public Date getOpeTime() {
        return opeTime;
    }

    public void setOpeTime(Date opeTime) {
        this.opeTime = opeTime == null ? null : opeTime;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getEvalProjectName() {
		return evalProjectName;
	}

	public void setEvalProjectName(String evalProjectName) {
		this.evalProjectName = evalProjectName;
	}

	public String getQuestionLinkId() {
		return questionLinkId;
	}

	public void setQuestionLinkId(String questionLinkId) {
		this.questionLinkId = questionLinkId;
	}

	public String getSupplierEvalTypeId() {
		return supplierEvalTypeId;
	}

	public void setSupplierEvalTypeId(String supplierEvalTypeId) {
		this.supplierEvalTypeId = supplierEvalTypeId;
	}

	public String[] getSupplierEvalIds() {
		return supplierEvalIds;
	}

	public void setSupplierEvalIds(String[] supplierEvalIds) {
		this.supplierEvalIds = supplierEvalIds;
	}

	public Date getQuestionHappenTime() {
		return questionHappenTime;
	}

	public void setQuestionHappenTime(Date questionHappenTime) {
		this.questionHappenTime = questionHappenTime;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getColatitudeId() {
		return colatitudeId;
	}

	public void setColatitudeId(String colatitudeId) {
		this.colatitudeId = colatitudeId;
	}
	
	
    
}