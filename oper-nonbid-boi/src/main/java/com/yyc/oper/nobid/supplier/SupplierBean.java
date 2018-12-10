package com.yyc.oper.nobid.supplier;

import java.io.Serializable;
import java.util.List;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * 主数据管理-非招标-供应商 mm_nonbid_supplier
 * */
public class SupplierBean extends BasePageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;//供应商工厂关系id
	
    private String supplierId;//供应商id

    private String supplierName;//供应商名称

    private String accountGroup;//账户组
    
    private String colatitudeName;//合作范围名称
    
    private String colatitudeId;//合作范围id
    
    private String qualificationName;//资质名称
    
    private String qualificationId;//资质id
    
    private String regionId;//地域id
    
    private String cityName;//地域市名称
    
    private String provinceName;//地域省名称
    
    private String taxType;//税率类型
    
    private String[] taxes;//税率
    
    private String tax;//税率

    private String depositeBankName;//开户行名称

    private String depositeBankAccount;//开户行账号

    private String addr;//地址

    private String phoneNum;//电话号码

    private String fax;//传真

    private String businessLicenseNum;//营业执照号码

    private String institutionCode;//机构代码

    private String taxNum;//传真号

    private String contact;//联系人

    private String contactMbNum;//联系人手机号码

    private String contactTelNum;//联系人固定号码

    private String contactMail;//联系人邮箱

    private String orderNum;//排序号

    private String state;//状态

    private String mainAchievement;//主要业绩

    private String del;//删除标记

    private String plantId;//工厂id

    private String createBy;//创建人

    private String createTime;//创建时间

    private String opeBy;//操作人

    private String opeTime;//操作时间
    
    private String[] colatitudeIds;//合作范围数组
    
    private String[] supplierIds;//推荐供应商数组
    
    private String[] colatitudeNames;//合作范围名称数组
    
    private String[] qualificationIds;//资质数组
    
    private String[] qualificationNames;//资质名称数组
    
    private String[] regionIds;//地域数组
    
    private String businessScope;//经营范围
    
    private String regionProvinceId;//服务地域省级id
    
    private String regionCityId;//服务地域省级id
    
    private String recommendPlant;//推荐单位
    
    private String stockHolder;//股东
    
    private String registeredCapital;//注册资金
    
    private String legalRepresentative;//法定代表人
    
    private String legalRiskNum;//法律风险数量
    
    private String remark;//备注
    
    private List<ErpSupplierBean> supplierlist; //入库详情
    
    
    
    
    

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }


    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup == null ? null : accountGroup.trim();
    }

    public String getDepositeBankName() {
        return depositeBankName;
    }

    public void setDepositeBankName(String depositeBankName) {
        this.depositeBankName = depositeBankName == null ? null : depositeBankName.trim();
    }

    public String getDepositeBankAccount() {
        return depositeBankAccount;
    }

    public void setDepositeBankAccount(String depositeBankAccount) {
        this.depositeBankAccount = depositeBankAccount == null ? null : depositeBankAccount.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getBusinessLicenseNum() {
        return businessLicenseNum;
    }

    public void setBusinessLicenseNum(String businessLicenseNum) {
        this.businessLicenseNum = businessLicenseNum == null ? null : businessLicenseNum.trim();
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum == null ? null : taxNum.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getContactMbNum() {
        return contactMbNum;
    }

    public void setContactMbNum(String contactMbNum) {
        this.contactMbNum = contactMbNum == null ? null : contactMbNum.trim();
    }

    public String getContactTelNum() {
        return contactTelNum;
    }

    public void setContactTelNum(String contactTelNum) {
        this.contactTelNum = contactTelNum == null ? null : contactTelNum.trim();
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail == null ? null : contactMail.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getMainAchievement() {
        return mainAchievement;
    }

    public void setMainAchievement(String mainAchievement) {
        this.mainAchievement = mainAchievement == null ? null : mainAchievement.trim();
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId == null ? null : plantId.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getOpeBy() {
        return opeBy;
    }

    public void setOpeBy(String opeBy) {
        this.opeBy = opeBy == null ? null : opeBy.trim();
    }

    public String getOpeTime() {
        return opeTime;
    }

    public void setOpeTime(String opeTime) {
        this.opeTime = opeTime == null ? null : opeTime.trim();
    }

	public String[] getColatitudeIds() {
		return colatitudeIds;
	}

	public void setColatitudeIds(String[] colatitudeIds) {
		this.colatitudeIds = colatitudeIds;
	}

	public String[] getQualificationIds() {
		return qualificationIds;
	}

	public void setQualificationIds(String[] qualificationIds) {
		this.qualificationIds = qualificationIds;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}



	public String getRecommendPlant() {
		return recommendPlant;
	}

	public void setRecommendPlant(String recommendPlant) {
		this.recommendPlant = recommendPlant;
	}

	public String getStockHolder() {
		return stockHolder;
	}

	public void setStockHolder(String stockHolder) {
		this.stockHolder = stockHolder;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getLegalRiskNum() {
		return legalRiskNum;
	}

	public void setLegalRiskNum(String legalRiskNum) {
		this.legalRiskNum = legalRiskNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getColatitudeName() {
		return colatitudeName;
	}

	public void setColatitudeName(String colatitudeName) {
		this.colatitudeName = colatitudeName;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}





	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getRegionProvinceId() {
		return regionProvinceId;
	}

	public void setRegionProvinceId(String regionProvinceId) {
		this.regionProvinceId = regionProvinceId;
	}

	public String getRegionCityId() {
		return regionCityId;
	}

	public void setRegionCityId(String regionCityId) {
		this.regionCityId = regionCityId;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}





	public String[] getTaxes() {
		return taxes;
	}

	public void setTaxes(String[] taxes) {
		this.taxes = taxes;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public List<ErpSupplierBean> getSupplierlist() {
		return supplierlist;
	}

	public void setSupplierlist(List<ErpSupplierBean> supplierlist) {
		this.supplierlist = supplierlist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getRegionIds() {
		return regionIds;
	}

	public void setRegionIds(String[] regionIds) {
		this.regionIds = regionIds;
	}

	public String[] getColatitudeNames() {
		return colatitudeNames;
	}

	public void setColatitudeNames(String[] colatitudeNames) {
		this.colatitudeNames = colatitudeNames;
	}

	public String[] getQualificationNames() {
		return qualificationNames;
	}

	public void setQualificationNames(String[] qualificationNames) {
		this.qualificationNames = qualificationNames;
	}

	public String getColatitudeId() {
		return colatitudeId;
	}

	public void setColatitudeId(String colatitudeId) {
		this.colatitudeId = colatitudeId;
	}

	public String getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(String qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String[] getSupplierIds() {
		return supplierIds;
	}

	public void setSupplierIds(String[] supplierIds) {
		this.supplierIds = supplierIds;
	}
	
	

	
	
	

    
    
}