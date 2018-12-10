package com.yyc.oper.nobid.batch;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 非招标批次管理 nonbid_batchmanage
 */
@ApiModel(value = "BatchmanageBean", description = "非招标批次管理")
public class BatchmanageBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("批次编码")
	private String batchId;// 批次编码

	@ApiModelProperty("批次编号")
	private String batchNum;// 批次编号

	@ApiModelProperty("批次名称")
	private String batchName;// 批次名称

	@ApiModelProperty("开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date beginTime;// 开始时间

	@ApiModelProperty("结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;// 结束时间

	@ApiModelProperty("批次来源")
	private String batchSource;// 批次来源

	@JsonIgnore
	private String createBy;// 创建人

	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;// 创建时间

	@JsonIgnore
	private String operBy;// 操作人

	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date operTime;// 操作时间

	@ApiModelProperty("状态")
	private String state;// 状态

	@JsonIgnore
	private String del;// 删除标记

	@ApiModelProperty("批次编码数组")
	private String[] batchIds;// 批次编码数组

	@ApiModelProperty("组织机构编码")
	private String orgId;// 组织机构编码

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId == null ? null : batchId.trim();
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum == null ? null : batchNum.trim();
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName == null ? null : batchName.trim();
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime == null ? null : beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime == null ? null : endTime;
	}

	public String getBatchSource() {
		return batchSource;
	}

	public void setBatchSource(String batchSource) {
		this.batchSource = batchSource == null ? null : batchSource.trim();
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

	public String getOperBy() {
		return operBy;
	}

	public void setOperBy(String operBy) {
		this.operBy = operBy == null ? null : operBy.trim();
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime == null ? null : operTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del == null ? null : del.trim();
	}

	public String[] getBatchIds() {
		return batchIds;
	}

	public void setBatchIds(String[] batchIds) {
		this.batchIds = batchIds;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}