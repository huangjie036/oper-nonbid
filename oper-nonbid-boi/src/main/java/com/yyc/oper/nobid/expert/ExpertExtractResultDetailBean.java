package com.yyc.oper.nobid.expert;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * 专家抽取结果详情  nonbid_expert_extract_result_detail
 *
 */
public class ExpertExtractResultDetailBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String id;//专家抽取结果详情表id

    private String extractId;//关联专家抽取结果表id
    
    private String expertCode;//专家编码

    private String opeDate;//抽取时间

    private String createDate;//创建时间

    private String createBy;//创建人

    private String opeBy;//操作人/抽取人

    private String state;//状态

    private String expertName;//专家姓名

    private String majorName;//专业名称

    private String majorId;//专业id

    private ExpertBean expertBean;//专家表
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExpertCode() {
        return expertCode;
    }

    public void setExpertCode(String expertCode) {
        this.expertCode = expertCode == null ? null : expertCode.trim();
    }

    public String getOpeDate() {
        return opeDate;
    }

    public void setOpeDate(String opeDate) {
        this.opeDate = opeDate == null ? null : opeDate.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
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

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName == null ? null : expertName.trim();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

	public String getExtractId() {
		return extractId;
	}

	public void setExtractId(String extractId) {
		this.extractId = extractId;
	}

	public ExpertBean getExpertBean() {
		return expertBean;
	}

	public void setExpertBean(ExpertBean expertBean) {
		this.expertBean = expertBean;
	}

}