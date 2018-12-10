package com.yyc.oper.nobid.mat;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 非招标-物资计划的物资表  nonbid_matplan_mat
 * */
@ApiModel(value="MatplanMatBean",description="非招标-物资计划的物资")
public class MatplanMatBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;//主键id

    private String matplanId;//物资计划id

    @JsonIgnore
    private String matplanType;//物资计划类型

    @ApiModelProperty("物料编码")
    @NotNull
    private String matId;//物料编码
    
    private String matDes;//物料描述

    @ApiModelProperty("小类id")
    @NotNull
    private String smallClassId;//小类id
    
    private String smallClass;//小类

    @ApiModelProperty("中类id")
    @NotNull
    private String middleClassId;//中类id
    
    private String middleClass;//中类

    @ApiModelProperty("大类id")
    @NotNull
    private String largeClassId;//大类id
    
    private String largeClass;//大类
    
    @ApiModelProperty("物料数量")
    @Min(0)
    @NotNull
    private String matNum;//物料数量

    @ApiModelProperty("物料单位")
    @NotNull
    private String matUnit;//物料单位

    @ApiModelProperty("预估单价")
    @Min(0)
    @NotNull
    private String estimateUnitPrice;//预估单价

    @ApiModelProperty("预估总价")
    @Min(0)
    @NotNull
    private String estimateTotalPrice;//预估总价

    @ApiModelProperty("交货时间")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryTime;//交货时间

    @ApiModelProperty("交货地点")
    @NotNull
    private String deliveryPlace;//交货地点
    
    @ApiModelProperty("组织机构编码")
	private String orgId;//组织机构编码
    
    @JsonIgnore
    private String recommendedSupplier;//推荐供应商

    @JsonIgnore
    private String recommendedSupplierPhone;//推荐供应商电话

    @JsonIgnore
    private String del;//删除标记
	
    @JsonIgnore
    private String[] matplanIds;//物资计划id数组
	
    @ApiModelProperty("大类")
    private String bigCatagory;//大类
    
    @ApiModelProperty("大类名称")
    private String bigCatagoryName;//大类名称
    
    @ApiModelProperty("中类名称")
    private String middleCatagoryName;//中类名称
    
    @ApiModelProperty("小类名称")
    private String smallCatagoryName;//小类名称
	
	@ApiModelProperty("中类")
    private String mediumCatagory;//中类
	
	@ApiModelProperty("小类")
    private String smallCatagory;//小类
	
	/**
	@ApiModelProperty("主键id")
    private String id;//主键id

	@JsonIgnore
    private String matplanId;//物资计划id
	
	@ApiModelProperty("物资计划类型  举例（1  代表什么类型  2 代表什么类型 .....）")
    private String matplanType;//物资计划类型

	@ApiModelProperty("物料编码")
    private String matId;//物料编码

	@ApiModelProperty("小类")
    private String smallClass;//小类

	@ApiModelProperty("中类")
    private String middleClass;//中类

	@ApiModelProperty("大类")
    private String largeClass;//大类

	@ApiModelProperty("物料数量")
    private String matNum;//物料数量

	@ApiModelProperty("物料单位")
    private String matUnit;//物料单位

	@ApiModelProperty("预估单价")
    private String estimateUnitPrice;//预估单价

	@ApiModelProperty("预估总价")
    private String estimateTotalPrice;//预估总价
	
	@ApiModelProperty("交货时间")
    private Date deliveryTime;//交货时间
	
	@ApiModelProperty("交货地点")
    private String deliveryPlace;//交货地点
	
	@JsonIgnore
    private String recommendedSupplier;//推荐供应商

	@JsonIgnore
    private String recommendedSupplierPhone;//推荐供应商电话

	@JsonIgnore
    private String del;//
**/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMatplanId() {
        return matplanId;
    }

    public void setMatplanId(String matplanId) {
        this.matplanId = matplanId == null ? null : matplanId.trim();
    }

    public String getMatplanType() {
		return matplanType;
	}

	public void setMatplanType(String matplanType) {
		this.matplanType = matplanType;
	}

    public String getMatId() {
        return matId;
    }

    public void setMatId(String matId) {
        this.matId = matId == null ? null : matId.trim();
    }

    public String getSmallClass() {
        return smallClass;
    }

    public void setSmallClass(String smallClass) {
        this.smallClass = smallClass == null ? null : smallClass.trim();
    }

    public String getMiddleClass() {
        return middleClass;
    }

    public void setMiddleClass(String middleClass) {
        this.middleClass = middleClass == null ? null : middleClass.trim();
    }

    public String getLargeClass() {
        return largeClass;
    }

    public void setLargeClass(String largeClass) {
        this.largeClass = largeClass == null ? null : largeClass.trim();
    }

    public String getMatNum() {
        return matNum;
    }

    public void setMatNum(String matNum) {
        this.matNum = matNum == null ? null : matNum.trim();
    }

    public String getMatUnit() {
        return matUnit;
    }

    public void setMatUnit(String matUnit) {
        this.matUnit = matUnit == null ? null : matUnit.trim();
    }

    public String getEstimateUnitPrice() {
        return estimateUnitPrice;
    }

    public void setEstimateUnitPrice(String estimateUnitPrice) {
        this.estimateUnitPrice = estimateUnitPrice == null ? null : estimateUnitPrice.trim();
    }

    public String getEstimateTotalPrice() {
        return estimateTotalPrice;
    }

    public void setEstimateTotalPrice(String estimateTotalPrice) {
        this.estimateTotalPrice = estimateTotalPrice == null ? null : estimateTotalPrice.trim();
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace == null ? null : deliveryPlace.trim();
    }

    public String getRecommendedSupplier() {
        return recommendedSupplier;
    }

    public void setRecommendedSupplier(String recommendedSupplier) {
        this.recommendedSupplier = recommendedSupplier == null ? null : recommendedSupplier.trim();
    }

    public String getRecommendedSupplierPhone() {
        return recommendedSupplierPhone;
    }

    public void setRecommendedSupplierPhone(String recommendedSupplierPhone) {
        this.recommendedSupplierPhone = recommendedSupplierPhone == null ? null : recommendedSupplierPhone.trim();
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

	public String[] getMatplanIds() {
		return matplanIds;
	}

	public void setMatplanIds(String[] matplanIds) {
		this.matplanIds = matplanIds;
	}

	public String getMatDes() {
		return matDes;
	}

	public void setMatDes(String matDes) {
		this.matDes = matDes;
	}

	public String getSmallClassId() {
		return smallClassId;
	}

	public void setSmallClassId(String smallClassId) {
		this.smallClassId = smallClassId;
	}

	public String getMiddleClassId() {
		return middleClassId;
	}

	public void setMiddleClassId(String middleClassId) {
		this.middleClassId = middleClassId;
	}

	public String getLargeClassId() {
		return largeClassId;
	}

	public void setLargeClassId(String largeClassId) {
		this.largeClassId = largeClassId;
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

	public String getBigCatagoryName() {
		return bigCatagoryName;
	}

	public void setBigCatagoryName(String bigCatagoryName) {
		this.bigCatagoryName = bigCatagoryName;
	}

	public String getMiddleCatagoryName() {
		return middleCatagoryName;
	}

	public void setMiddleCatagoryName(String middleCatagoryName) {
		this.middleCatagoryName = middleCatagoryName;
	}

	public String getSmallCatagoryName() {
		return smallCatagoryName;
	}

	public void setSmallCatagoryName(String smallCatagoryName) {
		this.smallCatagoryName = smallCatagoryName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
    
}