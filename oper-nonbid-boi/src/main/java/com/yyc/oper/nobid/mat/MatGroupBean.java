package com.yyc.oper.nobid.mat;

import java.io.Serializable;

import com.yyc.oper.nobid.base.BasePageBean;

/**
 * 主数据-物料组  mm_erp_mat_group
 * */
public class MatGroupBean extends BasePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String matGroupId;//物料组编码

    private String matGroupDes;//物料组描述

    private String matGroupDes2;//物料组描述2

    private String del;//删除标记
    
    private String parentId;//上级id

    public String getMatGroupId() {
        return matGroupId;
    }

    public void setMatGroupId(String matGroupId) {
        this.matGroupId = matGroupId == null ? null : matGroupId.trim();
    }

    public String getMatGroupDes() {
        return matGroupDes;
    }

    public void setMatGroupDes(String matGroupDes) {
        this.matGroupDes = matGroupDes == null ? null : matGroupDes.trim();
    }

    public String getMatGroupDes2() {
        return matGroupDes2;
    }

    public void setMatGroupDes2(String matGroupDes2) {
        this.matGroupDes2 = matGroupDes2 == null ? null : matGroupDes2.trim();
    }

    public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}