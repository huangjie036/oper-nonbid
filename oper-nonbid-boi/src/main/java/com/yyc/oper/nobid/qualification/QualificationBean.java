package com.yyc.oper.nobid.qualification;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

public class QualificationBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String qualificationId;//资质编码

    private String qualificationName;//资质名称

    private String orderNo;//序号
    
    private String expiryDate;//证书有效期

    private String del;//删除标记
    
    

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId == null ? null : qualificationId.trim();
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName == null ? null : qualificationName.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
    
    
}