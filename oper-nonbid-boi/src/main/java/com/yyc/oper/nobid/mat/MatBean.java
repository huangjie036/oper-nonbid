package com.yyc.oper.nobid.mat;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 主数据-物料主数据  mm_erp_mat
 * */
@ApiModel(value="MatBean",description="非招标-erp主数据物料")
public class MatBean extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("物料编码")
    private String matId;//物料编码
	
	@ApiModelProperty("大类")
    private String bigCatagory;//大类
	
	@ApiModelProperty("中类")
    private String mediumCatagory;//中类
	
	@ApiModelProperty("小类")
    private String smallCatagory;//小类
	
	@ApiModelProperty("物料描述")
    private String matDes;//物料描述
	
	@ApiModelProperty("单位名称")
    private String unitName;//单位名称
	
	@ApiModelProperty("规格")
    private String specification;//规格
	
	@ApiModelProperty("型号")
    private String model;//型号
	
	@ApiModelProperty("备注")
    private String remark;//备注
	
	@ApiModelProperty("状态")
    private String state;//状态
	
	@ApiModelProperty("工厂id")
    private String plantId;//工厂id
	
	@ApiModelProperty("创建人")
    private String createBy;//创建人

	@ApiModelProperty("创建时间")
    private String createTime;//创建时间

	@ApiModelProperty("操作人")
    private String opeBy;//操作人

	@ApiModelProperty("操作时间")
    private String opeTime;//操作时间
	
	@ApiModelProperty("删除标记")
    private String del;//删除标记
	
	@ApiModelProperty("物料编码数组")
    private String[] matIds;//物料编码数组
	

    public String getMatId() {
        return matId;
    }

    public void setMatId(String matId) {
        this.matId = matId == null ? null : matId.trim();
    }


    public String getMatDes() {
        return matDes;
    }

    public void setMatDes(String matDes) {
        this.matDes = matDes == null ? null : matDes.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }
    


	public String getBigCatagory() {
		return bigCatagory;
	}

	public void setBigCatagory(String bigCatagory) {
		this.bigCatagory = bigCatagory;
	}

	public String getMediumCatagory() {
		return mediumCatagory;
	}

	public void setMediumCatagory(String mediumCatagory) {
		this.mediumCatagory = mediumCatagory;
	}

	public String getSmallCatagory() {
		return smallCatagory;
	}

	public void setSmallCatagory(String smallCatagory) {
		this.smallCatagory = smallCatagory;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOpeBy() {
		return opeBy;
	}

	public void setOpeBy(String opeBy) {
		this.opeBy = opeBy;
	}

	public String getOpeTime() {
		return opeTime;
	}

	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String[] getMatIds() {
		return matIds;
	}

	public void setMatIds(String[] matIds) {
		this.matIds = matIds;
	}
	
	
	
}