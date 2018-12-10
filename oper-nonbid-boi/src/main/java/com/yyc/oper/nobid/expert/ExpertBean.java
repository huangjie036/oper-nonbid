package com.yyc.oper.nobid.expert;

import java.io.Serializable;
import java.util.List;

import com.yyc.oper.nobid.base.BasePageBean;
import com.yyc.oper.nobid.major.MajorBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 主数据-非招标-专家表  mm_nonbid_expert
 * */
@ApiModel(value="ExpertBean",description="主数据-非招标-专家表")
public class ExpertBean  extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("专家编码")
    private String expertCode;//专家编码

	@ApiModelProperty("专家名称")
    private String expertName;//专家名称

	@ApiModelProperty("专家身份证")
    private String expertId;//专家身份证

	@ApiModelProperty("专家性别")
    private String expertSex;//专家性别

	@ApiModelProperty("专家移动电话")
    private String expertMbNum;//专家移动电话

	@ApiModelProperty("专家固定电话")
    private String expertTelNum;//专家固定电话

	@ApiModelProperty("专家邮箱")
    private String expertMail;//专家邮箱

	@ApiModelProperty("申报部门")
    private String declarationDep;//申报部门

	@ApiModelProperty("毕业院校")
    private String graduateFrom;//毕业院校

	@ApiModelProperty("工作部门")
    private String workDep;//工作部门

	@ApiModelProperty("工作岗位")
    private String workPosition;//工作岗位

	@ApiModelProperty("岗位类别")
    private String positionKind;//岗位类别

	@ApiModelProperty("参加工作时间")
    private String startWorkTime;//参加工作时间

	@ApiModelProperty("删除标记")
    private String del;//删除标记

	@ApiModelProperty("专业名称")
    private String majorQualificationName;//专业名称
	
	@ApiModelProperty("专业分类")
    private String majorQualificationType;//专业分类
	
	@ApiModelProperty("工作单位")
    private String workUnit;//工作单位

	@ApiModelProperty("备注")
    private String remark;//备注

	@ApiModelProperty("专业信息类 集合")
    private List<MajorBean> listMajorBean;//专业信息类 集合
	
	@ApiModelProperty("状态")
    private String state;//状态
    
	@ApiModelProperty("抽取专家数量")
    private Integer number;//抽取专家数量
	
	@ApiModelProperty("专业类型数组")
    private List<String> majorIds;

    public String getExpertCode() {
        return expertCode;
    }

    public void setExpertCode(String expertCode) {
        this.expertCode = expertCode == null ? null : expertCode.trim();
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName == null ? null : expertName.trim();
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId == null ? null : expertId.trim();
    }

    public String getExpertSex() {
        return expertSex;
    }

    public void setExpertSex(String expertSex) {
        this.expertSex = expertSex == null ? null : expertSex.trim();
    }

    public String getExpertMbNum() {
        return expertMbNum;
    }

    public void setExpertMbNum(String expertMbNum) {
        this.expertMbNum = expertMbNum == null ? null : expertMbNum.trim();
    }

    public String getExpertTelNum() {
        return expertTelNum;
    }

    public void setExpertTelNum(String expertTelNum) {
        this.expertTelNum = expertTelNum == null ? null : expertTelNum.trim();
    }

    public String getExpertMail() {
        return expertMail;
    }

    public void setExpertMail(String expertMail) {
        this.expertMail = expertMail == null ? null : expertMail.trim();
    }

    public String getDeclarationDep() {
        return declarationDep;
    }

    public void setDeclarationDep(String declarationDep) {
        this.declarationDep = declarationDep == null ? null : declarationDep.trim();
    }

    public String getGraduateFrom() {
        return graduateFrom;
    }

    public void setGraduateFrom(String graduateFrom) {
        this.graduateFrom = graduateFrom == null ? null : graduateFrom.trim();
    }

    public String getWorkDep() {
        return workDep;
    }

    public void setWorkDep(String workDep) {
        this.workDep = workDep == null ? null : workDep.trim();
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition == null ? null : workPosition.trim();
    }

    public String getPositionKind() {
        return positionKind;
    }

    public void setPositionKind(String positionKind) {
        this.positionKind = positionKind == null ? null : positionKind.trim();
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime == null ? null : startWorkTime.trim();
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

	public String getMajorQualificationName() {
        return majorQualificationName;
    }

    public void setMajorQualificationName(String majorQualificationName) {
        this.majorQualificationName = majorQualificationName == null ? null : majorQualificationName.trim();
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getMajorQualificationType() {
		return majorQualificationType;
	}

	public void setMajorQualificationType(String majorQualificationType) {
		this.majorQualificationType = majorQualificationType;
	}

	public List<MajorBean> getListMajorBean() {
		return listMajorBean;
	}

	public void setListMajorBean(List<MajorBean> listMajorBean) {
		this.listMajorBean = listMajorBean;
	}

	public List<String> getMajorIds() {
		return majorIds;
	}

	public void setMajorIds(List<String> majorIds) {
		this.majorIds = majorIds;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    
}