package com.yyc.oper.nobid.expert;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  专家抽取请求参数  
 * */
@ApiModel(value="ExpertExtractRequest",description="专家抽取请求参数  ")
public class ExpertExtractRequest extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
    private String extractId;//抽取记录编号
	
	@ApiModelProperty("合包id")
    private String mergeId;//合包id
	
	@JsonIgnore
    private String expertWinId;//主数据专家id

	@ApiModelProperty("采购方案编号")
    private String purchaseSchmeId;//采购方案编号

	@ApiModelProperty("采购方案名称")
    private String purchaseSchmeName;//采购方案名称

	@ApiModelProperty("操作方式 1手动 2随机")
    private String opeWay;//操作方式 1手动 2随机
    
	@ApiModelProperty("专家集合")
    private List<ExpertBean> listExpertBean;//param 专家集合
    
	@ApiModelProperty("技术专家数量")
    private Integer technologyExpertNumber;//param 技术专家数量
    
	@ApiModelProperty("商务专家数量")
    private Integer businessExpertNumber;//param 商务专家数量

	@ApiModelProperty("专业类型数组")
    private String[] majorQualificationType;
	
	@ApiModelProperty("专家编码数组")
    private String[] expertCodeList;
	
	@ApiModelProperty("备注")
    private String remark;//备注
    
    public String getExtractId() {
        return extractId;
    }

    public void setExtractId(String extractId) {
        this.extractId = extractId == null ? null : extractId.trim();
    }

	public String getExpertWinId() {
		return expertWinId;
	}

	public void setExpertWinId(String expertWinId) {
		this.expertWinId = expertWinId;
	}

    public String getPurchaseSchmeId() {
        return purchaseSchmeId;
    }

    public void setPurchaseSchmeId(String purchaseSchmeId) {
        this.purchaseSchmeId = purchaseSchmeId == null ? null : purchaseSchmeId.trim();
    }

	public String getOpeWay() {
		return opeWay;
	}

	public void setOpeWay(String opeWay) {
		this.opeWay = opeWay;
	}

	public List<ExpertBean> getListExpertBean() {
		return listExpertBean;
	}

	public void setListExpertBean(List<ExpertBean> listExpertBean) {
		this.listExpertBean = listExpertBean;
	}

	public Integer getTechnologyExpertNumber() {
		return technologyExpertNumber;
	}

	public void setTechnologyExpertNumber(Integer technologyExpertNumber) {
		this.technologyExpertNumber = technologyExpertNumber;
	}

	public Integer getBusinessExpertNumber() {
		return businessExpertNumber;
	}

	public void setBusinessExpertNumber(Integer businessExpertNumber) {
		this.businessExpertNumber = businessExpertNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPurchaseSchmeName() {
		return purchaseSchmeName;
	}

	public void setPurchaseSchmeName(String purchaseSchmeName) {
		this.purchaseSchmeName = purchaseSchmeName;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String[] getMajorQualificationType() {
		return majorQualificationType;
	}

	public void setMajorQualificationType(String[] majorQualificationType) {
		this.majorQualificationType = majorQualificationType;
	}

	public String[] getExpertCodeList() {
		return expertCodeList;
	}

	public void setExpertCodeList(String[] expertCodeList) {
//		String[] array = new String[expertCodeList.length];
//		for(int i=0; i<expertCodeList.length; i++) {
//			array[i] = (String)expertCodeList[i];
//		}
		this.expertCodeList = expertCodeList;
	}


}