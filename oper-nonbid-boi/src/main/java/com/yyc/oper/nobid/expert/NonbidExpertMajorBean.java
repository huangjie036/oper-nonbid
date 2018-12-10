package com.yyc.oper.nobid.expert;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;
import com.yyc.oper.nobid.major.NonbidMajorBean;

/**
 * 非招标主数据专家专业中间表  mm_nonbid_expert_major
 * */
public class NonbidExpertMajorBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String id;//主键id

    private String expertId;//专家id

    private String majorId;//专业id
    
    private ExpertBean expertBean;//主数据-非招标-专家表
    
    private NonbidMajorBean nonbidMajorBean = new NonbidMajorBean();//非招标主数据-专业

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId == null ? null : expertId.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

	public ExpertBean getExpertBean() {
		return expertBean;
	}

	public void setExpertBean(ExpertBean expertBean) {
		this.expertBean = expertBean;
	}

	public NonbidMajorBean getNonbidMajorBean() {
		return nonbidMajorBean;
	}

	public void setNonbidMajorBean(NonbidMajorBean nonbidMajorBean) {
		this.nonbidMajorBean = nonbidMajorBean;
	}
    
}